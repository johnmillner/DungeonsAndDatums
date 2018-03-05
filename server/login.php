<?php
	/*
 	 *   Created by: 	John Millner
 	 *   Created: for:	Dungeons and Datums
	 *   Created on:	2018-02-22
	 *
	 *	Process: Logs in a user
	 * 		Receive JSON message
	 *		check that its properly formatted
	 *		Connect to MySQL DB
	 * 		Check that the email is already in the DB
	 * 		pull Salt and Hash from email
	 *		generate user's hash from password and Salt
	 *		Compare hash's
	 *		On success: generate new token and insert into DB
	 * 		Reformat data for JSON sendback
	 *		Send JSON message back to client.
	 *
	 *	Expected Input:
	 *		Expects JSON Message of email, password
	 *	Other Input:
	 *		if Null JSON message is given, program will use an empty Input object
	 *		if improperly formatted JSON message given, program will use an empty Input object
	 *
	 *	Normal Result:
	 *		Return JSON Object of name, email, phone, token, and tokenTime
	 * 	Other Results:
	 *		if email is not in DB - will return empty Output Object with "No User" in email
	 *		if hash's do not match - will return empty Output Object with "Bad Password" in email
	 *		if improperly formatted JSON message or null - will return an empty Output Object
	 *		if there is a server error - will return a raw string describing the problem
	 */

	/* TODO:
	 *
	 */

	// provides mySQL server credentials
	include '/var/www/secret.php';

	class testInput //also the expected JSON input
	{
		var $email 		= "test@dungeonsanddatums.com";
		var $password 	= "test";
	}

	class Input //also the expected JSON input
	{
		var $email;
		var $password;
	}

    class Output
    {
        var $name;
        var $email;
        var $phone;
        var $token;
        var $tokenTime;
    }

	// receive JSON message from client and store
	$input = json_decode( file_get_contents( 'php://input' ) );

	// test section: if no JSON message received, use test message
	if( $input == null)
	{
		$input = new testInput();
	}

	// check that input is properly formatted - if not, use the testInput as punishment
	if( $input->email 		== null ||
		$input->password 	== null  )
	{
		$input = new Input();
	}

	//create the user's output object which we'll fill out later
	$output = new Output();

	// connect to mySQL db - variables set in secret.php file
	$c = new mysqli($db_host, $db_userName, $db_password, $db_name);

	// handle connection failure
	if ( $c->connect_error )
	{
		echo ( $c->connect_error );
		exit();
	}

	// clean the users input
	$email 	= $c->real_escape_string( $input->email );

	// check if user's email is already in db - if not, return null object
	$q = "select * from users where email like '" . $email . "'";
	// set result and ensure query to check email is valid
	if ( ( $result = $c->query( $q ) ) == false )
	{
		echo ( $c->error );
		exit();
	}
	// if the result returns no rows we know that the email isnt in use - let client know.
	if( $result->num_rows <= 0 )
	{
		$output->email = "No User";
		echo json_encode( $output );
		exit();
	}

	// retrive user info for comparison
	$tmp = $result->fetch_object();

	// create a salt and hash for the user's password
	$p = $tmp->salt . $c->real_escape_string( $input->password ) ;
	$input->password = "";
	$hash = hash("sha256", $p, false);

	// confirm that the user's hash's match
	if( $hash != $tmp->hash )
	{
		$output->email = "Bad Password";
		echo json_encode( $output );
		exit();
	}

	// create randomly generated token string
	$token = bin2hex( random_bytes( 127 ) );

	// create query string for updating the token of the user into users table
	$q = "update users set token = '" . $token . "' where email = '" . $email . "'";

	// ensure query for updating user's new token into table is valid
	if ( $c->query( $q ) == false )
	{
		echo ( $c->error );
		exit();
	}

	// retrive updated user data
	$q = "select * from users where email like '" . $email . "'";
	// set result and ensure query to check email is valid
	if ( ( $result = $c->query( $q ) ) == false )
	{
		echo ( $c->error );
		exit();
	}

	// retrive updated user info
	$tmp = $result->fetch_object();

	// if the result is valid, fill out the users data
	$output->name 		= $tmp->name;
	$output->email 		= $tmp->email;
	$output->phone 		= $tmp->phone;
	$output->token 		= $tmp->token;
	$output->tokenTime 	= $tmp->tokenTime;

	//clean up
	$result->free();
	$c->close();

	// send updated user obj back to client
	echo json_encode( $output );

?>

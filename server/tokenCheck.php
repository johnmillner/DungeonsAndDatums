<?php
	/*
 	 *   Created by: 	John Millner
 	 *   Created: for:	Dungeons and Datums
	 *   Created on:	2018-02-22
	 *
	 *	Process: receives a token from the client, authenticates and renews
	 * 		Receive JSON message
	 *		check that its properly formatted
	 *		Connect to MySQL DB
	 * 		Check that the email is already in the DB
	 *		pull token from user and compare to given token
	 *		On success: generate new token and insert into DB
	 * 		Reformat data for JSON sendback
	 *		Send JSON message back to client.
	 *
	 *	Expected Input:
	 *		Expects JSON Message of email, token
	 *	Other Input:
	 *		if Null JSON message is given, program will use an empty Input object
	 *		if improperly formatted JSON message given, program will use an empty Input object
	 *
	 *	Normal Result:
	 *		Return JSON Object of email, token, and tokenTime
	 * 	Other Results:
	 *		if email is not in DB - will return empty Output Object with "No User" in email
	 *		if hash's do not match - will return empty Output Object with "Bad Token" in email
	 *		if improperly formatted JSON message or null - will return an empty Output Object
	 *		if there is a server error - will return a raw string describing the problem
	 */

	/* TODO:
	 *
	 */

	// provides mySQL server credentials
	include '/var/www/secret.php';

	class Input //also the expected JSON input
	{
		var $email;
		var $token;
	}

    class Output
    {
        var $email;
        var $token;
        var $tokenTime;
    }

	// receive JSON message from client and store
	$input = json_decode( file_get_contents( 'php://input' ) );

	// check that input is properly formatted - if not, use the testInput as punishment
	if( $input 			== null ||
		$input->email 	== null ||
		$input->token 	== null  )
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
		echo ($c->connect_error);
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

	// confirm that the user's token's match
	if( real_escape_string( $input->token ) != $tmp->token )
	{
		$output->email = "Bad token";
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
		echo ($c->error);
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
	$output->email 		= $tmp->email;
	$output->token 		= $tmp->token;
	$output->tokenTime 	= $tmp->tokenTime;

	//clean up
	$result->free();
	$c->close();

	// send updated user obj back to client
	echo json_encode( $output );

?>

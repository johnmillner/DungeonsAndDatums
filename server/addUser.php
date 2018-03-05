<?php
	/*
 	 *   Created by: 	John Millner
 	 *   Created: for:	Dungeons and Datums
	 *   Created on:	2018-02-22
	 *
	 *	Process: Creates a new user:
	 * 		Receive JSON message
	 *		check that its properly formatted
	 *		Connect to MySQL DB
	 * 		Check that the email is not already in the DB
	 * 		Create Salt, Hash, and token
	 *		Insert new user into users table
	 * 		Confirm that the user is in the data and reformat data for JSON sendback
	 *		Send JSON message back to client.
	 *
	 *	Expected Input:
	 *		Expects JSON Message of email, password, name, $phone
	 *	Other Input:
	 *		if Null JSON message is given, program will assume in test mode and use testInput()
	 *		if improperly formatted JSON message given, program will *assume* in test mode and use testInput()
	 *
	 *	Normal Result:
	 *		Return JSON Object of name, email, phone, token, and $tokenTime
	 * 	Other Results:
	 *		if null input, will return the empty Output Object
	 *		if user already exsist, will return the empty Output Object
	 *		if improperly formatted JSON message will return an empty Output Object
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
		var $name 		= "Testy Tester";
		var $phone 		= "+1 (352) 867-5309";
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
		$input->password 	== null ||
		$input->name 		== null ||
		$input->phone 		== null  )
	{
		$input = new testInput();
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
	$name 	= $c->real_escape_string( $input->name  );
	$email 	= $c->real_escape_string( $input->email );
	$phone 	= $c->real_escape_string( $input->phone );

	// check if user's email is already in db - if so, return null object
	$q = "select * from users where email like '" . $email . "'";
	// set result and ensure query to check email is valid
	if ( ( $result = $c->query( $q ) ) == false )
	{
		echo ( $c->error );
		exit();
	}
	// if the result returns any rows we know that the email is already in use - return empty.
	if( $result->num_rows > 0 )
	{
		$output->email = "User Already Exsists!";
		echo json_encode( $output );
		exit();
	}

	// create a salt and hash for the user's password
	$salt = random_int( 0, 2147483647  ); // 2147483647 since PHP_MAX_INT is larger than MySQL's
	$p = $salt . $c->real_escape_string( $input->password ) ;
	$input->password = "";
	$hash = hash("sha256", $p, false);

	// create randomly generated token string
	$token = bin2hex( random_bytes( 127 ) );

	// create query string for inserting user into users table
	$q = 	"insert into users ( name, email, hash, salt, phone, token )
			values ( '" . $name . "', '" . $email . "', '" . $hash .
			"', '" . $salt . "', '" . $phone . "', '" . $token . "')";

	// set result and ensure query for adding user to table is valid
	if ( ( $result = $c->query( $q ) ) == false )
	{
		echo ($c->error);
		exit();
	}

	// we can confirm success by pulling the users data from the users table to confirm
	$q = "select * from users where email like '" . $email . "'";

	// set result and ensure query for receiving user data is valid
	if ( ( $result = $c->query( $q ) ) == false )
	{
		echo ($c->error);
		exit();
	}

	// if the result is valid, fill out the users data
	$tmp = $result->fetch_object();
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

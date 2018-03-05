<?php
	/*
 	 *   Created by: 	John Millner
 	 *   Created: for:	Dungeons and Datums
	 *   Created on:	2018-02-22
	 *
	 *	Process: returns a list of campaigns info (just name, logo file, background file)
	 *      receive JSON message of email and token
     *      authenticate users
     *      query DB for all campaigns that the user is apart of
     *          query campaign table for DM'd
     *          query character group table
     *		         for each character group pull that campaign table
     *      with all data, sort by last played
     *      send back that data as JSON
     *
	 *	Expected Input:
	 *     JSON object of {email, token}
	 *	Other Input:
	 *	    null
	 *      improper
	 *	Normal Result:
	 *		list of campaign objects
	 * 	Other Results:
	 *		error object
	 */

	/* TODO:
	 * add time out feature to token authentication
     * return new token
	 */

     class Input
     {
         var $email;
         var $token;
     }

     class testInput
     {
         var $email = "t@t.com";
         var $token = "c966bc32f6a8200925bd4514ae7602ef53517e8a93b53fbf0306c67f0548adf917d7b35b08cbff9fc612e109a18f75a32a72f4993eaedeaf1afe49c0800305a43a573f9a86e183918455761eabb3a6abb73b2e06ea271ecc9a989a25beca0c193c94450e4ef616dcac4b2667de26ee6d5d7616b9bf80a95c1a9105d977107f";
     }

     class Output
     {
        var $campaigns;
        var $token;

        function __construct( )
        {
            $this->campaigns = array();
        }

        function add( $campaign )
        {
            $this->campaigns[] = $campaign;
        }

        function remove( $campagin )
        {
            // find campaign index
            if ( $index = array_search( $campaign, $campaigns ) )
            {
                // remove the campaign from the list
                $this->campsign->unset( $campaign[ $index ] );
            }
        }
     }

     class Campaign
     {
         var $name;
         var $logo;
         var $background;

         function __construct( $name, $logo, $background)
         {
             $this->name        = $name;
             $this->logo        = $logo;
             $this->background  = $background;
         }
     }

	// provides mySQL server credentials
	include '/var/www/secret.php';

    // receive JSON message from client and store
	$input = json_decode( file_get_contents( 'php://input' ) );

	// test section: if no JSON message received, use test message
	if( $input == null)
	{
		$input = new Input();
	}

	// check that input is properly formatted - if not, use the testInput as punishment
	if( $input->email 		== null ||
		$input->token 	== null )
	{
		$input = new testInput();
	}

	// connect to mySQL db - variables set in secret.php file
	$c = new mysqli($db_host, $db_userName, $db_password, $db_name);

    //create the user's output object which we'll fill out later
	$output = new Output();

	// handle connection failure
	if ( $c->connect_error )
	{
		echo ( $c->connect_error );
		exit();
	}

	// clean the users input
	$email 	= $c->real_escape_string( $input->email );
    $token  = $c->real_escape_string( $input->token );



    // authenticate user's email + token
    // we can confirm success by pulling the users data from the users table to confirm
	$q = "select token, id from users where email like '" . $email . "'";
    
	// set result and ensure query for receiving user data is valid
	if ( ( $result = $c->query( $q ) ) == false )
	{
		echo ($c->error);
		exit();
	}

	// if the result is valid, authenticate token
	$tmp = $result->fetch_object();
    $id = $tmp->id;
    if( $token != $tmp->token )
    {
        $output->token="invalid token";
        echo json_encode( $output );
        exit();
    }
    // TODO: replace token with new token
    $output->token = $token;

    // query for DM'd campaigns
    $q = "select name, logo, background from campaign where dmID like '" . $id . "'";

    // set result and ensure query for receiving campaign data is valid
    if ( ( $result = $c->query( $q ) ) == false )
    {
        echo ($c->error);
        exit();
    }

    // add campaigns to list
    while( $row = $result->fetch_object() )
    {
        $output->add( new campaign( $row->name, $row->logo, $row->background ) );
    }

    // send updated user obj back to client
	echo json_encode( $output );

?>

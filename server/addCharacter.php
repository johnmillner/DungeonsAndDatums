<?php
	/*
 	 *   Created by: 	Matthew Kurtz
 	 *   Created: for:	Dungeons and Datums
	 *   Created on:	2018-03-31
	 *
	 *	Process: Creates a new character:
	 * 		Receive JSON message
	 *		check that its properly formatted
	 *		Connect to MySQL DB
	 * 		Create Salt, Hash, and token
	 *		Insert new character into character table
	 * 		Confirm that the character is in the data and reformat data for JSON sendback
	 *		Send JSON message back to client.
	 *
	 *	Expected Input:
	 *		Expects JSON Message of: 
	 * email
	 * token 	
	 * name 		
     * isNPC
     * title
	 * Class
	 * level
	 * background
	 * alignment
	 * xp
	 * inspiration
	 * profBonus
	 * strength
	 * dexterity
	 * constitution
	 * wisdom
	 * intelligence
	 * charisma
	 * ac
	 * speed
	 * maxHP
	 * currentHP
	 * tmpHP
	 * saving_str
	 * saving_dex
	 * saving_con
	 * saving_int
	 * saving_wis
	 * saving_cha
	 * skills_acr
	 * skills_arc
	 * skills_an
	 * skills_ath
	 * skills_dec
	 * skills_his
	 * skills_ins
	 * skills_int
	 * skills_inv
	 * skills_med
	 * skills_nat
	 * skills_perc
	 * skills_perf
	 * skills_pers
	 * skills_rel
	 * skills_sle
	 * skills_ste
	 * skills_sur
	 * CP
	 * SP
	 * EP
	 * GP
	 * PP
	 * ideals
	 * flaws
	 * features
	 * allies
	 * organizations
	 * additionalFeatures
	 * backstory
	 * age
	 * hair
	 * 
	 *	Other Input:
	 *		if Null JSON message is given, program will assume in test mode and use testInput()
	 *		if improperly formatted JSON message given, program will *assume* in test mode and use testInput()
	 *
	 *	Normal Result:
	 *		Return JSON Object of name, email, phone, token, and $tokenTime
	 * 	Other Results:
	 *		if null input, will return the empty Output Object
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
		 var $email 	= "t@t.com";
		 var $token 	= "YEET";
		 var $user_id	= 0;
		 
		 var $name 		= "testArcher";
         var $isNPC		= 0;
         var $title		= "title";
		 var $race		= "RACE";
		 var $Class		= "Class";
		 var $level		= 0;
		 var $background = "background";
		 var $alignment = "alignment";
		 var $xp = 0;
		 var $inspiration = 0;
		 var $profBonus = 0;
		 
		 var $strength = 0;
		 var $dexterity = 0;
		 var $constitution = 0;
		 var $wisdom = 0;
		 var $intelligence = 0;
		 var $charisma = 0;
		 
		 var $ac = 0;
		 var $speed = 0;
		 var $maxHP = 0;
		 var $currentHP = 0;
		 var $tmpHP = 0;
		 
		 var $saving_str = 0;
		 var $saving_dex = 0;
		 var $saving_con = 0;
		 var $saving_int = 0;
		 var $saving_wis = 0;
		 var $saving_cha = 0;
		 
		 var $skills_acr = 0;
		 var $skills_arc = 0;
		 var $skills_an  = 0;
		 var $skills_ath = 0;
		 var $skills_dec = 0;
		 var $skills_his = 0;
		 var $skills_ins = 0;
		 var $skills_int = 0;
		 var $skills_inv = 0;
		 var $skills_med = 0;
		 var $skills_nat = 0;
		 var $skills_perc = 0;
		 var $skills_perf = 0;
		 var $skills_pers = 0;
		 var $skills_rel = 0;
		 var $skills_sle = 0;
		 var $skills_ste = 0;
		 var $skills_sur = 0;
		 
		 var $CP = 0;
		 var $SP = 0;
		 var $EP = 0;
		 var $GP = 0;
		 var $PP = 0;
		 
		 var $ideals = "ideals";
		 var $flaws = "flaws";
		 var $features = "features";
		 var $allies = "allies";
		 var $organizations = "organizations";
		 var $additionalFeatures = "additionalFeatures";
		 var $backstory = "backstory";
		 var $age = 0;
		 var $hair = "hair";
	}

    class Output
    {
        var $name;
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
	if( 	 $input->email 				 == null ||
			// $input->token 				 == null ||
			 $input->name 				 == null ||
         	 $input->isNPC				 == null ||
         	 $input->title				 == null ||
			 $input->race 				 == null ||
		 	 $input->Class		         == null ||
		 	 $input->level				 == null ||
		 	 $input->background			 == null ||
		 	 $input->alignment			 == null ||
		 	 $input->xp					 == null ||
		 	 $input->inspiration		 == null ||
		 	 $input->profBonus			 == null ||
			 
		 	 $input->strength			 == null ||
		     $input->dexterity			 == null ||
		 	 $input->constitution		 == null ||
		 	 $input->wisdom				 == null ||
		 	 $input->intelligence		 == null ||
		 	 $input->charisma			 == null ||
		  	 $input->ac 				 == null ||
		 	 $input->speed				 == null ||
		 	 $input->maxHP				 == null ||
		 	 $input->currentHP			 == null ||
		 	 $input->tmpHP				 == null ||
		 	 $input->saving_str			 == null ||
		 	 $input->saving_dex			 == null ||
		 	 $input->saving_con			 == null ||
		 	 $input->saving_int			 == null ||
		 	 $input->saving_wis			 == null ||
		 	 $input->saving_cha			 == null ||
		 	 $input->skills_acr			 == null ||
		 	 $input->skills_arc			 == null ||
		 	 $input->skills_an			 == null ||
		 	 $input->skills_ath			 == null ||
		 	 $input->skills_dec			 == null ||
		 	 $input->skills_his			 == null ||
		  	 $input->skills_ins			 == null ||
		 	 $input->skills_int			 == null ||
		 	 $input->skills_inv			 == null ||
		 	 $input->skills_med			 == null ||
		 	 $input->skills_nat			 == null ||
		 	 $input->skills_perc		 == null ||
		 	 $input->skills_perf		 == null ||
		 	 $input->skills_pers		 == null ||
		 	 $input->skills_rel			 == null ||
		 	 $input->skills_sle			 == null ||
		 	 $input->skills_ste			 == null ||
		 	 $input->skills_sur			 == null ||
		 	 $input->CP 		 		 == null ||
		 	 $input->SP					 == null ||
		 	 $input->EP		 			 == null ||
		 	 $input->GP		 			 == null ||
		 	 $input->PP					 == null ||
		 	 $input->ideals	 			 == null ||
		 	 $input->flaws		 		 == null ||
		  	 $input->features	 		 == null ||
		  	 $input->allies 	 		 == null ||
		 	 $input->organizations	 	 == null ||
		 	 $input->additionalFeatures	 == null ||
		 	 $input->backstory			 == null ||
		 	 $input->age				 == null ||
		 	 $input->hair				 == null )
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
	//$token  = $c->real_escape_string( $input->token );
	$email 	= $c->real_escape_string( $input->email );
	$name 	= $c->real_escape_string( $input->name  );
	$title 	= $c->real_escape_string( $input->title );
	$class 	= $c->real_escape_string( $input->Class );
	$background 	= $c->real_escape_string( $input->background );
	$alignment 		= $c->real_escape_string( $input->alignment  );
	$ideals 		= $c->real_escape_string( $input->ideals     );
	$flaws 			= $c->real_escape_string( $input->flaws 	 );
	$features 		= $c->real_escape_string( $input->features 	 );
	$allies 		= $c->real_escape_string( $input->allies 	 );
	$backstory 		= $c->real_escape_string( $input->backstory  );
	$hair 			= $c->real_escape_string( $input->hair 		 );
	$organizations 	= $c->real_escape_string( $input->organizations );
	$additionalFeatures = $c->real_escape_string( $input->additionalFeatures );

	// check if user's email is already in db - if so, return null object
	$q = "select * from users where email like '" . $email . "'";
	
	// set result and ensure query to check email is valid
	if ( ( $result = $c->query( $q ) ) == false )
	{
		echo ( $c->error );
		exit();
	}
	// if the result returns any rows we know that the email is already in use - return empty.
	if( $result->num_rows <= 0 )
	{
		$output->email = "No user with this email";
		echo json_encode( $output );
		exit();
	}
	
	// authenticate user's email + token
    // we can confirm success by pulling the users data from the users table to confirm

	// if the result is valid, authenticate token
	/*$tmp = $result->fetch_object();
    $id = $tmp->id;
    if( $token != $tmp->token )
    {
        $output->token="invalid token";
        echo json_encode( $output );
        exit();
    }
    // TODO: replace token with new token
    $output->token = $token;
	*/

	// create randomly generated token string
	$token = bin2hex( random_bytes( 127 ) );

	// create query string for inserting token into users table
	//$q = 	"insert into users ( token ) values ( '"  . $token . "')";
	
	// create query string for insterting character into character table
	$q2 = "insert into characters ( user_id, char_isNPC, name, Class, level, background, alignment, xp, inspiration, profBonus, strength, dexterity, constitution, intelligence, wisdom, charisma, ac, speed, maxHP, currentHP, tmpHP, saving_str, saving_dex, saving_con, saving_int, saving_wis, saving_cha, 
	skills_acr, skills_arc, skills_an, skills_ath, skills_dec, skills_his, skills_ins, skills_int, skills_inv, skills_med, skills_nat, skills_perc, skills_perf, skills_pers, skills_rel, skills_sle, skills_ste, skills_sur, CP, SP, EP, GP, PP, ideals, flaws, features, allies, organizations, additionalFeatures, backstory, age, hair)
	VALUES ('". $input->user_id . "', '" . $input->isNPC . "', '" . $input->name . "', '" . $input->Class . "', '" . $input->level . "', '" . $input->background . "', '" . $input->alignment . "', '" . $input->xp. "', '" . $input->inspiration . "', '" . $input->profBonus . "', '" . $input->strength . "', '" . $input->dexterity . "', '" . $input->constitution . "', '" . $input->intelligence . "', '" . $input->wisdom . "', '" . $input->charisma 
	. "', '" . $input->ac . "', '" . $input->speed . "', '" . $input->maxHP . "', '" . $input->currentHP . "', '" . $input->tmpHP . "', '" . $input->saving_str . "', '" . $input->saving_dex . "', '" . $input->saving_con . "', '" . $input->saving_int . "', '" . $input->saving_wis . "', '" . $input->saving_cha . "', '" . $input->skills_acr . "', '" . $input->skills_arc . "', '" . $input->skills_an . "', '" . $input->skills_ath . "', '" . $input->skills_dec 
	. "', '" . $input->skills_his . "', '" . $input->skills_ins . "', '" . $input->skills_int . "', '" . $input->skills_inv . "', '" . $input->skills_med . "', '" . $input->skills_nat . "', '" . $input->skills_perc . "', '" . $input->skills_perf . "', '" . $input->skills_pers . "', '" . $input->skills_rel . "', '" . $input->skills_sle . "', '" . $input->skills_ste . "', '" . $input->skills_sur . "', '" . $input->CP . "', '" . $input->SP . "', '" . $input->EP 
	. "', '" . $input->GP . "', '" . $input->PP . "', '" . $input->ideals . "', '" . $input->flaws . "', '" . $input->features . "', '" . $input->allies . "', '" . $input->organizations . "', '" . $input->additionalFeatures . "', '" . $input->backstory . "', '" . $input->age . "', '" . $input->hair
	. "')";

	// set result and ensure query for adding user to table is valid
	$result = $c->query($q2);
	if ( $result == false )
	{
		echo ($c->error);
		exit();
	}

	// we can confirm success by pulling the users data from the users table to confirm
	$q = "select * from characters where user_id like '" . $input->user_id . "'";
	
	// set result and ensure query for receiving user data is valid
	if ( ( $result = $c->query( $q ) ) == false )
	{
		echo ($c->error);
		exit();
	}

	// if the result is valid, fill out the users data
	$tmp = $result->fetch_object();
	$output->name 		= $tmp->name;
	$output->token 		= $tmp->token;
	$output->tokenTime 	= $tmp->tokenTime;

	//clean up
	$result->free();
	$c->close();

	// send updated user obj back to client
	echo json_encode( $output );

?>

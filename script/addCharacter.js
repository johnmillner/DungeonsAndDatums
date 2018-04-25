function addUser()
{

    var input =
    {
          email: new String(), 	
		  token: new String(), 	
		  name : new String(),		
          isNPC: new Boolean(),
          title: new String(),		
		  Class: new String(),		
		  level: new Number(),
		  background: new String(), 
		  alignment: new String(), 
		  xp: new Number(),
		  inspiration: new Number(),
		  profBonus: new Number(),
		  strength: new Number(),
		  dexterity: new Number(),
		  constitution: new Number(),
		  wisdom: new Number(),
		  intelligence: new Number(),
		  charisma: new Number(),
		  ac: new Number(),
		  speed: new Number(),
		  maxHP: new Number(),
		  currentHP: new Number(),
		  tmpHP: new Number(),
		  saving_str: new Boolean(), 
		  saving_dex: new Boolean(), 
		  saving_con: new Boolean(), 
		  saving_int: new Boolean(), 
		  saving_wis: new Boolean(), 
		  saving_cha: new Boolean(),
		  skills_acr: new Boolean(), 
		  skills_arc: new Boolean(), 
		  skills_an: new Boolean(), 
		  skills_ath: new Boolean(), 
		  skills_dec: new Boolean(), 
		  skills_his: new Boolean(), 
		  skills_ins: new Boolean(), 
		  skills_int: new Boolean(), 
		  skills_inv: new Boolean(), 
		  skills_med: new Boolean(), 
		  skills_nat: new Boolean(), 
		  skills_perc: new Boolean(),
		  skills_perf: new Boolean(),
		  skills_pers: new Boolean(),
		  skills_rel: new Boolean(), 
		  skills_sle: new Boolean(), 
		  skills_ste: new Boolean(), 
		  skills_sur: new Boolean(),
		  CP: new Number(),
		  SP: new Number(), 
		  EP: new Number(), 
		  GP: new Number(), 
		  PP: new Number(),
		  ideals: new String(), 
		  flaws: new String(), 
		  features: new String(), 
		  allies: new String(), 
		  organizations: new String(), 
		  additionalFeatures: new String(), 
		  backstory: new String(), 
		  age: new Number(),
		  hair: new String()
    };

    var output =
    {
        email:      new String(),
        currentHP:  new Number(),
        token:      new String(),
        tokenTime:  new String()
    };

	var url = "https://dungeonsanddatums.com/test/server/addUser.php";

	// read in user commands and set to user obj
	input.email= document.getElementById("").value;
	input.name 					= document.getElementById("name").value;
    input.isNPC					= document.getElementById("isNPC").value;
    input.title					= document.getElementById("title").value;
	input.Class					= document.getElementById("Class").value;
	input.level					= document.getElementById("level").value;
	input.background			= document.getElementById("background").value;
	input.alignment				= document.getElementById("alignment").value;
	input.xp					= document.getElementById("xp").value;
	input.inspiration			= document.getElementById("inspiration").value;
	input.profBonus				= document.getElementById("profBonus").value;
	input.strength				= document.getElementById("strength").value;
	input.dexterity				= document.getElementById("dexterity").value;
	input.constitution			= document.getElementById("constitution").value;
	input.wisdom				= document.getElementById("wisdom").value;
	input.intelligence			= document.getElementById("intelligence").value;
	input.charisma				= document.getElementById("charisma").value;
	input.ac					= document.getElementById("ac").value;
	input.speed					= document.getElementById("speed").value;
	input.maxHP					= document.getElementById("maxHP").value;
	input.currentHP				= document.getElementById("currentHP").value;
	input.tmpHP					= document.getElementById("tmpHP").value;
	input.saving_str			= document.getElementById("saving_str").value;
	input.saving_dex			= document.getElementById("saving_dex").value;
	input.saving_con			= document.getElementById("saving_con").value;
	input.saving_int			= document.getElementById("saving_int").value;
	input.saving_wis			= document.getElementById("saving_wis").value;
	input.saving_cha			= document.getElementById("saving_cha").value;
	input.skills_acr			= document.getElementById("skills_acr").value;
	input.skills_arc			= document.getElementById("skills_arc").value;
	input.skills_an				= document.getElementById("skills_an").value;
	input.skills_ath			= document.getElementById("skills_ath").value;
	input.skills_dec			= document.getElementById("skills_dec").value;
	input.skills_his			= document.getElementById("skills_his").value;
	input.skills_ins			= document.getElementById("skills_ins").value;
	input.skills_int			= document.getElementById("skills_int").value;
	input.skills_inv			= document.getElementById("skills_inv").value;
	input.skills_med			= document.getElementById("skills_med").value;
	input.skills_nat			= document.getElementById("skills_nat").value;
	input.skills_perc			= document.getElementById("skills_perc").value;
	input.skills_perf			= document.getElementById("skills_perf").value;
	input.skills_pers			= document.getElementById("skills_pers").value;
	input.skills_rel			= document.getElementById("skills_rel").value;
	input.skills_sle			= document.getElementById("skills_sle").value;
	input.skills_ste			= document.getElementById("skills_ste").value;
	input.skills_sur			= document.getElementById("skills_sur").value;
	input.CP					= document.getElementById("CP").value;
	input.SP					= document.getElementById("SP").value;
	input.EP					= document.getElementById("EP").value;
	input.GP					= document.getElementById("GP").value;
	input.PP					= document.getElementById("PP").value;
	input.ideals				= document.getElementById("ideals").value;
	input.flaws					= document.getElementById("flaws").value;
	input.features				= document.getElementById("features").value;
	input.allies				= document.getElementById("allies").value;
	input.organizations			= document.getElementById("organizations").value;
	input.additionalFeatures	= document.getElementById("additionalFeatures").value;
	input.backstory				= document.getElementById("backstory").value;
	input.age 					= document.getElementById("age").value;
	input.hair 					= document.getElementById("hair").value;

	// start up request service
	var xhttp = new XMLHttpRequest();
	xhttp.open("POST", url, true);
	xhttp.setRequestHeader("Content-type", "'application/json'");

	// send off JSON message for user obj
	xhttp.send( JSON.stringify( input ) );

	// wait for respond from the server
	xhttp.onreadystatechange = function()
	{
		if (this.readyState == 4 && this.status == 200)
		{
			//update the user obj from the database's response
			output = JSON.parse( this.responseText );
			console.log( output );
			if ( output.name )
            {
				document.getElementById("soutput").innerHTML = "Welcome " + output.name + "!";
			}
			else
			{
				document.getElementById("soutput").innerHTML = "Error: " + output.email;
			}
		}
	}
}

/*
email
	token 	
	name 		
    isNPC
    title
	Class
	level
	background
	alignment
	xp
	inspiration
	profBonus
	strength
	dexterity
	constitution
	wisdom
	intelligence
	charisma
	ac
	speed
	maxHP
	currentHP
	tmpHP
	saving_str
	saving_dex
	saving_con
	saving_int
	saving_wis
	saving_cha
	skills_acr
	skills_arc
	skills_an
	skills_ath
	skills_dec
	skills_his
	skills_ins
	skills_int
	skills_inv
	skills_med
	skills_nat
	skills_perc
	skills_perf
	skills_pers
	skills_rel
	skills_sle
	skills_ste
	skills_sur
	CP
	SP
	EP
	GP
	PP
	ideals
	flaws
	features
	allies
	organizations
	additionalFeatures
	backstory
	age
	hair
	*/

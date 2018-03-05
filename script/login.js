function login()
{

    var input =
    {
        email:      new String(),
        password:   new String()
    };

    var output =
    {
        email:      new String(),
        name:       new String(),
        phone:      new String(),
        token:      new String(),
        tokenTime:  new String()
    };

	var url = "https://dungeonsanddatums.com/test/server/login.php";

	// read in user commands and set to user obj
	input.email 	= document.getElementById("email").value;
	input.password 	= document.getElementById("password").value;

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
				document.getElementById("output").innerHTML = "Welcome " + output.name + "!";
			}
			else
			{
				document.getElementById("output").innerHTML = "Error: " + output.email;
			}
		}
	}
}

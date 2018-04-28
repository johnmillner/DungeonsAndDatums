
var Login = new Vue
({
	el: "#login",

	data:
	{
		url: "https://dungeonsanddatums.com/test/server/login.php",

		login:
		{
			email: "",
			password: ""
		},

		message: 'please log in'
	},

	methods:
	{
		submit: function()
		{
			// Pushes posts to the server when called.
			axios.post( Login.url, Login.login )

			.then(function (response)
			{
				Login.message = "your name is: " + response.data.token;
				console.log(response);
			})

			.catch( function (error)
			{
				Login.message="there was an error: " + error;
			})
		}
	}
})

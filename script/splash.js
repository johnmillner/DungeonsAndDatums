
var vm = new Vue
({
	el: "#login",
	
    data:
    {
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
			axios.post( `https://dungeonsanddatums.com/test/server/login.php`, vm.login )
			.then(function (response) { vm.message = "your name is: " + response.data.name; console.log(response); })
			.catch( function (error) { vm.message="there was an error: " + error; })
        }
    }
})

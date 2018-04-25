package com.dungeonsanddatums.dungeonsanddatums;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;

import org.json.JSONObject;

public class SignIn extends AppCompatActivity
{
    private EditText emailEditText;     // Email text field
    private EditText passwordEditText;  // Password text field

    private TextView failedSignInTextView;      // This is the red text that appears beneath password

    private ProgressBar progressBar;

    private Button signInButton;
    private Button createAccountButton;
    private Button testButton;

    // return the email inside the editable text field
    private String getEmail(){ return emailEditText.getText().toString();}

    // return the password inside the editable text field
    private String getPassword(){ return passwordEditText.getText().toString();}

    // This method acts as a constructor, and gets executed at the launch of an activity
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {


        super.onCreate(savedInstanceState);

        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // Tie this class to an XML file containing the layout
        setContentView(R.layout.activity_sign_in);




        // define variables to xml layout
        emailEditText = (EditText) findViewById(R.id.et_email);
        passwordEditText = (EditText) findViewById(R.id.et_password);
        failedSignInTextView = (TextView) findViewById(R.id.tv_failedSignIn);
        signInButton = (Button) findViewById(R.id.b_signIn);
        createAccountButton = (Button) findViewById(R.id.b_createAccount);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        testButton = (Button) findViewById(R.id.b_test);

        testButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v)
            {
                // Define where we are now
                Context context = SignIn.this;

                // Define what activity we are switching to
                Class destinationActivity = CreateCharacter.class;

                // Tell intent where we are now, and where we are going
                Intent startChildActivityIntent = new Intent(context, destinationActivity);

                // launch the activity
                startActivity(startChildActivityIntent);
            }
        });

        // make succesfulSignInTextView visible and display search results upon click
        signInButton.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                progressBar.setVisibility(View.VISIBLE);
                // Format the JSON to include necessary fields
                JSONObject json = new JSONObject();
                try
                {
                    json.put("email", getEmail());
                    json.put("password", getPassword());
                }
                catch(Exception e )
                {
                    e.printStackTrace();
                }

                // Execute AsyncTask in charge of connecting to the internet and getting info from database
                new Login().execute("https://dungeonsanddatums.com/test/server/login.php", json.toString());
            }
        });

        createAccountButton.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                progressBar.setVisibility(View.VISIBLE);
                // Format the JSON to include necessary fields
                JSONObject json = new JSONObject();
                try
                {
                    json.put("email", getEmail());
                    json.put("password", getPassword());
                }
                catch(Exception e )
                {
                    e.printStackTrace();
                }

                // Define where we are now
                Context context = SignIn.this;

                // Define what activity we are switching to
                Class destinationActivity = CreateAccount.class;

                // Tell intent where we are now, and where we are going
                Intent startChildActivityIntent = new Intent(context, destinationActivity);

                // Pass any desired variables to the activity we are going to
                startChildActivityIntent.putExtra(Intent.EXTRA_TEXT, json.toString());

                // launch the activity
                startActivity(startChildActivityIntent);
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    /* Connects to the database via the internet, if login is successful, launch the "CreateAccount" activity
         * AsyncTasks get run in the background, and are necessary for connecting to the internet
         * as of android 6.0.
         */
    class Login extends AsyncTask<String, String, String>
    {
        // NOTHING INVOLVING UI SHOULD EVER BE IN THIS METHOD, BACKEND STUFF ONLY
        @Override
        protected String doInBackground(String... params)
        {
            // Sends a json query to the server and returns the result
            return NetworkUtils.postQueryServer(params[0], params[1]);
        }

        /* Gets executed after doInBackground, doInBackground returns desired variable to this parameter
         * Here we are allowed to manipulate the UI in any way we like using the data we got from doInBackground
         * In this case, we are using the information from the database to determine if the login combination
         * was correct or not, if it was then we launch the "CreateAccount" activity
         */
        @Override
        protected void onPostExecute(String result)
        {
            //reset visibilities
            failedSignInTextView.setVisibility(View.INVISIBLE);

            try
            {
                JSONObject json = new JSONObject(result);

                // If the email entered is not in the database
                if(json.getString("email").equals("No User"))
                {
                    // Display an invalid username in red text
                    failedSignInTextView.setText("Invalid Username");
                    failedSignInTextView.setVisibility(View.VISIBLE);
                }

                // If the Password was incorrect, but user was in the database
                else if( json.getString("email").equals("Bad Password"))
                {
                    // Display an incorrect passord in red text
                    failedSignInTextView.setText("Incorrect Password");
                    failedSignInTextView.setVisibility(View.VISIBLE);
                }

                // initiate home activity
                else
                {
                    progressBar.setVisibility(View.INVISIBLE);
                    // Define where we are now
                    Context context = SignIn.this;

                    // Define what activity we are switching to
                    Class destinationActivity = Campaigns.class;

                    // Tell intent where we are now, and where we are going
                    Intent startChildActivityIntent = new Intent(context, destinationActivity);

                    // Pass any desired variables to the activity we are going to
                    startChildActivityIntent.putExtra(Intent.EXTRA_TEXT, result);

                    // launch the activity
                    startActivity(startChildActivityIntent);
                }
            }
            // If this happens, we are fucked ('Cause I have no clue why it would go here)
            catch(Exception e)
            {
                Toast.makeText(getApplicationContext(), "Error, Try Again", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }

}

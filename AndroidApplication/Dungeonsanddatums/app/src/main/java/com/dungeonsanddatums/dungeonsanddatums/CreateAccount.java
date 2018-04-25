package com.dungeonsanddatums.dungeonsanddatums;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class CreateAccount extends AppCompatActivity
{

    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText phoneNumberEditText;
    private EditText nameEditText;
    private Button createAccountButton;

    private TextView debugTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        emailEditText = (EditText) findViewById(R.id.et_email);
        passwordEditText = (EditText) findViewById(R.id.et_password);
        phoneNumberEditText = (EditText) findViewById(R.id.et_phoneNumber);
        nameEditText = (EditText) findViewById(R.id.et_name);

        createAccountButton = (Button) findViewById(R.id.b_createAccount);

        debugTextView = (TextView) findViewById(R.id.tv_debug);

        createAccountButton.setOnClickListener(new View.OnClickListener()
        {
            JSONObject json = new JSONObject();

            @Override
            public void onClick(View v)
            {
                try
                {
                    json.put("email", emailEditText.getText().toString());
                    json.put("password", passwordEditText.getText().toString());
                    json.put("name", nameEditText.getText().toString());
                    json.put("phone", phoneNumberEditText.getText().toString());
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }

                new SendCreateAccount().execute("https://dungeonsanddatums.com/test/server/addUser.php", json.toString());
            }
        });
    }

    class SendCreateAccount extends AsyncTask<String, String, String>
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
            try
            {
                JSONObject json = new JSONObject(result);

                // If the email entered is not in the database
                if(json.getString("name").equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Account already exists with that email", Toast.LENGTH_LONG).show();
                }


                // initiate home activity
                else
                {
                    // Define where we are now
                    Context context = CreateAccount.this;

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

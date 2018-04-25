package com.dungeonsanddatums.dungeonsanddatums;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.Toast;
import android.support.v7.widget.RecyclerView;

import com.dungeonsanddatums.dungeonsanddatums.Adapters.CampaignListAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

public class Campaigns extends AppCompatActivity
    implements CampaignListAdapter.ListItemClickListener
{
    String email;
    String token;
    String passedExtraJson; // will hold the data passed to this activity (we are expecting a string formatted like a JSON)
    RecyclerView mrecyclerView ;
    CampaignListAdapter mAdapter;
    int numOfCampaigns;
    Campaigns thisCampaign = this;
    private static final String TAG = CampaignListAdapter.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaigns);

        // reference to the recycler view
        mrecyclerView = (RecyclerView) findViewById(R.id.rv_campaignList);

        // The linear layout manager will position list items in a vertical list
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mrecyclerView.setLayoutManager(layoutManager);


        passedExtraJson = getIntent().getStringExtra(Intent.EXTRA_TEXT);

        try
        {
            JSONObject passedJson = new JSONObject(passedExtraJson);
            email = passedJson.getString("email");
            token = passedJson.getString("token");

            JSONObject json = new JSONObject();
            json.put("email", email);
            json.put("token", token);



            // The adapter is responsible for displaying each item in the list



            new GetCampains().execute("https://dungeonsanddatums.com/test/server/listCampaigns.php", json.toString());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }


    }

    /**
     * This is where we receive our callback from the clicklistener in the adapter
     * This callback is invoked when you click on an item in the list.
     *
     * @param clickedItemIndex Index in the list of the item that was clicked.
     */
    @Override
    public void onListItemClick(int clickedItemIndex)
    {
        // In here, handle what happens when an item is clicked
        // In this case, I am just logging the index of the item clicked

        Log.v(TAG, "List item clicked at index: " + clickedItemIndex);
    }

    class GetCampains extends AsyncTask<String, String, String>
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
            JSONObject json;
            try
            {
                json = new JSONObject(result);

                JSONArray campaigns = json.getJSONArray("campaigns");
                numOfCampaigns = campaigns.length();

                mAdapter = new CampaignListAdapter(thisCampaign, campaigns);
                mrecyclerView.setAdapter(mAdapter);

                Log.d(TAG, "NumOfCampaigns: " + campaigns.length());

                for(int i=0; i < campaigns.length(); i++)
                {
                    //TODO For every campaign, add a view to the viewGroup
                    Log.i("Campaigns", campaigns.getJSONObject(i).getString("name"));
                }

                //campaignListTextView.setText(json.getString("name"));
                //campaignListTextView.append(json.getString("name").toString());
            }
            catch(Exception e)
            {
                Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }





        }
    }


}

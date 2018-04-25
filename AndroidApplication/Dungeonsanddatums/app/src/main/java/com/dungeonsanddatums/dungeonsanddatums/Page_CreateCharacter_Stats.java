package com.dungeonsanddatums.dungeonsanddatums;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.dungeonsanddatums.dungeonsanddatums.Adapters.CampaignListAdapter;
import com.dungeonsanddatums.dungeonsanddatums.Adapters.ListStatsAdapter;

import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Page_CreateCharacter_Class.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Page_CreateCharacter_Class#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Page_CreateCharacter_Stats extends Fragment implements AdapterView.OnItemSelectedListener
{
    Spinner spinner_MainRace;
    View rootView;
    ArrayAdapter<CharSequence> adapter_mainRaceSpinner;
    ListStatsAdapter listViewAdapter;
    private OnFragmentInteractionListener mListener;
    private static final String TAG = CampaignListAdapter.class.getSimpleName();
    String[] statValues;
    TextView tv_numOfPoints;
    int numOfPoints;
    int pointsRemaining = 27;
    Button b_submitCharacter;
    String raceSelected;
    String subraceSelected;

    public Page_CreateCharacter_Stats()
    {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment Page_CreateCharacter_Class.
     */
    // TODO: Rename and change types and number of parameters
    public static Page_CreateCharacter_Class newInstance(String param1, String param2)
    {
        Page_CreateCharacter_Class fragment = new Page_CreateCharacter_Class();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        numOfPoints = 27;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.page_create_character__stats, container, false);

        // Restore any saved data
        if(savedInstanceState != null)
        {
            statValues = savedInstanceState.getStringArray("statValues");
            numOfPoints = savedInstanceState.getInt("numOfPoints");
            pointsRemaining = savedInstanceState.getInt("pointsRemaining");
        }
        else
            statValues = getResources().getStringArray(R.array.default_stat_values);

        // initialize text view that displays the number of points
        tv_numOfPoints = rootView.findViewById(R.id.tv_numOfPoints);
        tv_numOfPoints.setText(Integer.toString(pointsRemaining));

        // Options selected from other fragments
        raceSelected = Page_CreateCharacter_Race.getRace();
        subraceSelected = Page_CreateCharacter_Race.getSubrace();

        // "Submit DnDCharacter" Button
        b_submitCharacter = rootView.findViewById(R.id.b_submitCharacter);
        b_submitCharacter.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                JSONObject json = new JSONObject();
                try
                {
                    //TODO Fill in values with user input
                    json.put("name", "TestName");
                    json.put("isNPC", 0);
                    json.put("title", "Title");
                    json.put("race", raceSelected);
                    json.put("Class", Page_CreateCharacter_Class.getClassType());
                    json.put("level", 1);
                    json.put("background", Page_CreateCharacter_Background.getBackground());
                    json.put("alignment", "testAlignment");
                    json.put("xp", 0);
                    json.put("inspiration", 0);
                    json.put("profBonus", 2);

                    json.put("strength", statValues[0]);
                    json.put("dexterity", statValues[1]);
                    json.put("constitution", statValues[2]);
                    json.put("wisdom", statValues[3]);
                    json.put("intelligence", statValues[4]);
                    json.put("charisma", statValues[5]);

                    json.put("ac", 15);
                    json.put("speed", 16);
                    json.put("maxHP", 17);
                    json.put("currentHP", 17);
                    json.put("tmpHP", 0);

                    json.put("saving_str", 0);
                    json.put("saving_dex", 0);
                    json.put("saving_con", 0);
                    json.put("saving_int", 0);
                    json.put("saving_wis", 0);
                    json.put("saving_cha", 0);

                    json.put("skills_acr", 1);
                    json.put("skills_arc", 1);
                    json.put("skills_an", 1);
                    json.put("skills_ath", 1);
                    json.put("skills_dec", 1);
                    json.put("skills_his", 1);
                    json.put("skills_ins", 1);
                    json.put("skills_int", 1);
                    json.put("skills_inv", 1);
                    json.put("skills_med", 1);
                    json.put("skills_nat", 1);
                    json.put("skills_perc", 1);
                    json.put("skills_perf", 1);
                    json.put("skills_pers", 1);
                    json.put("skills_rel", 1);
                    json.put("skills_sle", 1);
                    json.put("skills_ste", 1);
                    json.put("skills_sur", 1);

                    json.put("CP", 10);
                    json.put("SP", 10);
                    json.put("EP", 10);
                    json.put("GP", 10);
                    json.put("PP", 10);

                    json.put("ideals", "IDEALS");
                    json.put("flaws", "FLAWS");
                    json.put("features", "FEATURES");
                    json.put("allies", "ALLIES");
                    json.put("organizations", "ORGANIZATIONS");
                    json.put("additionalFeatures", "ADDITIONALFEATURES");
                    json.put("backstory", "BACKSTORY");
                    json.put("age", 0);
                    json.put("hair", "HAIR");

                    new SubmitCharacter().execute("https://dungeonsanddatums.com/test/server/addCharacter.php", json.toString());

                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }

                //Toast.makeText(rootView.getContext(), json.toString(), Toast.LENGTH_LONG).show();
                //Toast.makeText(rootView.getContext(),"= " + json.toString(),Toast.LENGTH_LONG);
            }
        });

        // set up spinner
        spinner_MainRace = (Spinner) rootView.findViewById(R.id.spinner_stats);

        adapter_mainRaceSpinner = ArrayAdapter.createFromResource(this.getContext(),R.array.stats_list, android.R.layout.simple_spinner_item);
        adapter_mainRaceSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_MainRace.setAdapter(adapter_mainRaceSpinner);
        spinner_MainRace.setOnItemSelectedListener(this);

        // set up list view
        String[] menuItems = getResources().getStringArray(R.array.stats_description);

        ListView listView = (ListView) rootView.findViewById(R.id.lv_statsDescription);

        /*ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(this.getContext(), R.layout.list_stats,
                R.id.tv_statAttribute, menuItems);
        */
        listViewAdapter = new ListStatsAdapter(this.getContext(), statValues, rootView, numOfPoints);

        listView.setAdapter(listViewAdapter);

        // set button on click listeners
        final Button b_plus = (Button) rootView.findViewById(R.id.b_statPlus);
        final Button b_minus = (Button) rootView.findViewById(R.id.b_statMinus);
        final TextView tv_statScore = (TextView) rootView.findViewById(R.id.tv_statScore);

       /* b_plus.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                int num = Integer.valueOf(tv_statScore.getText().toString());
                num++;
                tv_statScore.setText(num);
            }
        });
        */

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri)
    {
        if (mListener != null)
        {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener)
        {
            mListener = (OnFragmentInteractionListener) context;
        } else
        {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        String optionSelected = parent.getItemAtPosition(position).toString();
        ListView listView = (ListView) rootView.findViewById(R.id.lv_statsDescription);

        // This will be displayed in the list view
        String[] barbarianItems = getResources().getStringArray(R.array.BarbarianFeatures);
        String[] druidItems = getResources().getStringArray(R.array.DruidFeatures);

        Toast.makeText(this.getContext(), optionSelected, Toast.LENGTH_SHORT).show();


        //TODO fill out race information
        //TODO add subrace spinner/ListView and info to go along with
        switch(optionSelected)
        {
            case "Barbarian":
                ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                        getActivity(),android.R.layout.simple_list_item_1,
                        barbarianItems);
                listView.setAdapter(listViewAdapter);
                break;
            case "Druid":
                listViewAdapter = new ArrayAdapter<String>(
                        getActivity(),android.R.layout.simple_list_item_1,
                        druidItems);
                listView.setAdapter(listViewAdapter);

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener
    {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onSaveInstanceState(Bundle savedState)
    {
        super.onCreate(savedState);

        String[] values = listViewAdapter.getValues();
        savedState.putStringArray("statValues", values);
        savedState.putInt("numOfPoints", numOfPoints);
        savedState.putInt("pointsRemaining",Integer.valueOf(tv_numOfPoints.getText().toString()));
    }


    // Handles sending information to the server
    class SubmitCharacter extends AsyncTask<String, String, String>
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
                Toast.makeText(rootView.getContext(), json.toString(), Toast.LENGTH_LONG).show();


            }
            // If this happens, we are fucked ('Cause I have no clue why it would go here)
            catch(Exception e)
            {
                Toast.makeText(rootView.getContext(), "Error, Try Again", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }
}

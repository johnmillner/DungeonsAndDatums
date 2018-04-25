package com.dungeonsanddatums.dungeonsanddatums;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.dungeonsanddatums.dungeonsanddatums.Adapters.CampaignListAdapter;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Page_CreateCharacter_Class.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Page_CreateCharacter_Class#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Page_CreateCharacter_Race extends Fragment implements AdapterView.OnItemSelectedListener
{
    //    private Page_CreateCharacter_Race.OnFragmentInteractionListener mListener;
    static Spinner spinner_MainRace;
    static Spinner spinner_subrace;
    View rootView;
    ArrayAdapter<CharSequence> adapter_mainRaceSpinner;
    ArrayAdapter<CharSequence> adapter_subraceSpinner;
    String[] menuItems;
    private OnFragmentInteractionListener mListener;

    private static final String TAG = CampaignListAdapter.class.getSimpleName();


    public Page_CreateCharacter_Race()
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        rootView = inflater.inflate(R.layout.page_create_character__race, container, false);

        spinner_MainRace = (Spinner) rootView.findViewById(R.id.spinner_RaceSelect);

        // set up main race select spinner adapter
        adapter_mainRaceSpinner = ArrayAdapter.createFromResource(this.getContext(),R.array.race_list, android.R.layout.simple_spinner_item);
        adapter_mainRaceSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_MainRace.setAdapter(adapter_mainRaceSpinner);
        spinner_MainRace.setOnItemSelectedListener(this);

        // set up subrace select spinner
        spinner_subrace = (Spinner) rootView.findViewById(R.id.spinner_subraceSelect);

        // adapter for subrace spinner
        adapter_subraceSpinner =  ArrayAdapter.createFromResource(this.getContext(),R.array.myDefault, android.R.layout.simple_spinner_item);
        adapter_subraceSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // link subrace spinner to adapter
        spinner_subrace.setAdapter(adapter_subraceSpinner);
        spinner_subrace.setOnItemSelectedListener(this);
        spinner_subrace.setVisibility(View.INVISIBLE);

        String[] menuItems = {getString(R.string.human_abilityScoreIncrease),
                getString(R.string.human_speed),
                getString(R.string.human_size),
                getString(R.string.human_age),
                getString(R.string.human_languages)};

        ListView listView = (ListView) rootView.findViewById(R.id.lv_raceDescription);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),android.R.layout.simple_list_item_1,
                menuItems);

        listView.setAdapter(listViewAdapter);
        // Inflate the layout for this fragment
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
        ListView listView = (ListView) rootView.findViewById(R.id.lv_raceDescription);

        String[] dwarfTraits = getResources().getStringArray(R.array.dwarfTraits);
        String[] elfTraits = getResources().getStringArray(R.array.elfTraits);
        String[] halflingTraits = getResources().getStringArray(R.array.halflingTraits);
        String[] human_Items = getResources().getStringArray(R.array.humanTraits);
        String[] dragonbornTraits = getResources().getStringArray(R.array.dragonbornTraits);
        String[] gnomeTraits = getResources().getStringArray(R.array.gnomeTraits);
        String[] halfElfTraits = getResources().getStringArray(R.array.halfElfTraits);
        String[] halfOrcTraits = getResources().getStringArray(R.array.halfOrcTraits);
        String[] tieflingTraits = getResources().getStringArray(R.array.tieflingTraits);


        //TODO fill out race information
        //TODO add subrace spinner/ListView and info to go along with
        ArrayAdapter<String> listViewAdapter; // To be set on a case by case basis
        switch(optionSelected)
        {
            // Main races:
            case "Dwarf":
                // fill out race description text view
                listViewAdapter = new ArrayAdapter<String>(
                        getActivity(),android.R.layout.simple_list_item_1,
                        dwarfTraits);
                listView.setAdapter(listViewAdapter);

                // change subrace spinner
                spinner_subrace.setVisibility(View.VISIBLE);
                adapter_subraceSpinner = ArrayAdapter.createFromResource(this.getContext(),R.array.dwarf_subraces, android.R.layout.simple_spinner_item);
                adapter_subraceSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner_subrace.setAdapter(adapter_subraceSpinner);
                break;

            case "Elf":
                listViewAdapter = new ArrayAdapter<String>(
                        getActivity(),android.R.layout.simple_list_item_1,
                        elfTraits);
                listView.setAdapter(listViewAdapter);

                // change subrace spinner
                spinner_subrace.setVisibility(View.VISIBLE);
                adapter_subraceSpinner = ArrayAdapter.createFromResource(this.getContext(),R.array.elf_subraces, android.R.layout.simple_spinner_item);
                adapter_subraceSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner_subrace.setAdapter(adapter_subraceSpinner);
                break;

            case "Halfling":
                listViewAdapter = new ArrayAdapter<String>(
                        getActivity(),android.R.layout.simple_list_item_1,
                        halflingTraits);
                listView.setAdapter(listViewAdapter);

                // change subrace spinner
                spinner_subrace.setVisibility(View.VISIBLE);
                adapter_subraceSpinner = ArrayAdapter.createFromResource(this.getContext(),R.array.halfling_subraces, android.R.layout.simple_spinner_item);
                adapter_subraceSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner_subrace.setAdapter(adapter_subraceSpinner);
                break;

            case "Human":
                listViewAdapter = new ArrayAdapter<String>(
                        getActivity(),android.R.layout.simple_list_item_1,
                        human_Items);
                listView.setAdapter(listViewAdapter);

                // change subrace spinner
                spinner_subrace.setVisibility(View.VISIBLE);
                adapter_subraceSpinner = ArrayAdapter.createFromResource(this.getContext(),R.array.human_subraces, android.R.layout.simple_spinner_item);
                adapter_subraceSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner_subrace.setAdapter(adapter_subraceSpinner);
                break;

            case "Dragonborn":
                listViewAdapter = new ArrayAdapter<String>(
                        getActivity(),android.R.layout.simple_list_item_1,
                        dragonbornTraits);
                listView.setAdapter(listViewAdapter);

                // change subrace spinner
                spinner_subrace.setVisibility(View.VISIBLE);
                adapter_subraceSpinner = ArrayAdapter.createFromResource(this.getContext(),R.array.dragonborn_subraces, android.R.layout.simple_spinner_item);
                adapter_subraceSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner_subrace.setAdapter(adapter_subraceSpinner);
                break;

            case "Gnome":
                listViewAdapter = new ArrayAdapter<String>(
                        getActivity(),android.R.layout.simple_list_item_1,
                        gnomeTraits);
                listView.setAdapter(listViewAdapter);

                // change subrace spinner
                spinner_subrace.setVisibility(View.VISIBLE);
                adapter_subraceSpinner = ArrayAdapter.createFromResource(this.getContext(),R.array.gnome_subraces, android.R.layout.simple_spinner_item);
                adapter_subraceSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner_subrace.setAdapter(adapter_subraceSpinner);
                break;

            case "Half-Elf":
                listViewAdapter = new ArrayAdapter<String>(
                        getActivity(),android.R.layout.simple_list_item_1,
                        halfElfTraits);
                listView.setAdapter(listViewAdapter);

                // change subrace spinner
                spinner_subrace.setVisibility(View.INVISIBLE);
                break;

            case "Half-Orc":
                listViewAdapter = new ArrayAdapter<String>(
                        getActivity(),android.R.layout.simple_list_item_1,
                        halfOrcTraits);
                listView.setAdapter(listViewAdapter);

                // change subrace spinner
                spinner_subrace.setVisibility(View.INVISIBLE);
                break;

            case "Tiefling":
                listViewAdapter = new ArrayAdapter<String>(
                        getActivity(),android.R.layout.simple_list_item_1,
                        tieflingTraits);
                listView.setAdapter(listViewAdapter);

                // change subrace spinner
                spinner_subrace.setVisibility(View.INVISIBLE);
                break;

            // Subraces:

            // Dwarf:
            case "Hill Dwarf":


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

    public static String getRace()
    {
        return spinner_MainRace.getSelectedItem().toString();
    }
    public static String getSubrace()
    {
        return spinner_subrace.getSelectedItem().toString();
    }

}

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
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.dungeonsanddatums.dungeonsanddatums.Adapters.myExpandableListAdapter;
import com.dungeonsanddatums.dungeonsanddatums.Holders.DetailInfo;
import com.dungeonsanddatums.dungeonsanddatums.Holders.HeaderInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Page_CreateCharacter_Class.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Page_CreateCharacter_Class#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Page_CreateCharacter_Class extends Fragment
{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private static String classType;
    Spinner spinner_classSelect;
    Spinner spinner_subclassSelect;
    ExpandableListView elv_classDetails;
    ExpandableListAdapter elv_classDetails_adapter;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    private OnFragmentInteractionListener mListener;

    private ArrayList<HeaderInfo> SectionList = new ArrayList<>();
    private LinkedHashMap<String, HeaderInfo> mySection = new LinkedHashMap<>();

    public Page_CreateCharacter_Class()
    {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
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
        if (getArguments() != null)
        {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.page_create_character__class, container, false);

        // get the list view
        elv_classDetails = (ExpandableListView) rootView.findViewById(R.id.elv_classDetails);

        // preparing list data
        prepareListData();

        AddProduct();
        elv_classDetails_adapter = new myExpandableListAdapter(this.getContext(), SectionList);

        elv_classDetails.setAdapter(elv_classDetails_adapter);

        elv_classDetails.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener()
        {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id)
            {
                return false;
            }
        });

        // set on group expand listener
        elv_classDetails.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener()
        {
            @Override
            public void onGroupExpand(int groupPosition)
            {
                Toast.makeText(getActivity().getApplicationContext(),listDataHeader.get(groupPosition) + " Expanded", Toast.LENGTH_SHORT).show();
            }
        });

        // set on group collapse listener
        elv_classDetails.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener()
        {
            @Override
            public void onGroupCollapse(int groupPosition)
            {
                Toast.makeText(getActivity().getApplicationContext(),listDataHeader.get(groupPosition) + " Collapsed", Toast.LENGTH_SHORT).show();
            }
        });

        elv_classDetails.setOnChildClickListener(new ExpandableListView.OnChildClickListener()
        {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id)
            {
                Toast.makeText(getActivity().getApplicationContext(),listDataHeader.get(groupPosition) + " : " + listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition), Toast.LENGTH_SHORT).show();

                return false;
            }
        });

        //setup class select spinner
        spinner_classSelect = (Spinner) rootView.findViewById(R.id.spinner_ClassSelect);
        ArrayAdapter<CharSequence> adapter_ClassSelect = ArrayAdapter.createFromResource(rootView.getContext(), R.array.classes, android.R.layout.simple_spinner_dropdown_item);
        spinner_classSelect.setAdapter(adapter_ClassSelect);

        spinner_classSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                classType = getResources().getStringArray(R.array.classes)[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                classType = getResources().getStringArray(R.array.classes)[0];
            }
        });

        // setup subclass select spinner
        spinner_subclassSelect = (Spinner) rootView.findViewById(R.id.spinner_subClassSelect);
        ArrayAdapter<CharSequence> adapter_subclassSpinner = ArrayAdapter.createFromResource(rootView.getContext(),R.array.myDefault, android.R.layout.simple_spinner_dropdown_item);
        spinner_subclassSelect.setAdapter(adapter_subclassSpinner);

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

    public static String getClassType()
    {
        return classType;
    }

    private void prepareListData()
    {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // adding header data
        listDataHeader.add("Ability Score Increase");
        listDataHeader.add("Class Features");

        // adding child data
        List<String> abilityScoreIncrease = new ArrayList<String>();
        abilityScoreIncrease.add("Con + 2");
        abilityScoreIncrease.add("that's all you need");

        List<String> classFeatures = new ArrayList<String>();
        classFeatures.add("Barrel Rolls get advantage");
        classFeatures.add("Falcon Punch");
        classFeatures.add("Pocket Sand");

        listDataChild.put(listDataHeader.get(0), abilityScoreIncrease);
        listDataChild.put(listDataHeader.get(1), classFeatures);

    }

    //load some initial data into out list
    private void AddProduct(){

        addProduct("Vegetable","Potato");
        addProduct("Vegetable","Cabbage");
        addProduct("Vegetable","Onion");

        addProduct("Fruits","Apple");
        addProduct("Fruits","Orange");
    }

    private int addProduct(String department, String product){

        int groupPosition = 0;

        //check the hash map if the group already exists
        HeaderInfo headerInfo = mySection.get(department);
        //add the group if doesn't exists
        if(headerInfo == null){
            headerInfo = new HeaderInfo();
            headerInfo.setName(department);
            mySection.put(department, headerInfo);
            SectionList.add(headerInfo);
        }

        //get the children for the group
        ArrayList<DetailInfo> productList = headerInfo.getProductList();
        //size of the children list
        int listSize = productList.size();
        //add to the counter
        listSize++;

        //create a new child and add that to the group
        DetailInfo detailInfo = new DetailInfo();
        detailInfo.setSequence(String.valueOf(listSize));
        detailInfo.setName(product);
        productList.add(detailInfo);
        headerInfo.setProductList(productList);

        //find the group position inside the list
        groupPosition = SectionList.indexOf(headerInfo);
        return groupPosition;
    }
}

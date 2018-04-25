package com.dungeonsanddatums.dungeonsanddatums.Adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.dungeonsanddatums.dungeonsanddatums.Page_CreateCharacter_Background;
import com.dungeonsanddatums.dungeonsanddatums.Page_CreateCharacter_Gear;
import com.dungeonsanddatums.dungeonsanddatums.Page_CreateCharacter_Race;
import com.dungeonsanddatums.dungeonsanddatums.Page_CreateCharacter_Stats;
import com.dungeonsanddatums.dungeonsanddatums.Page_CreateCharacter_Class;

/**
 * Created by matth on 3/28/2018.
 */

public class CreateCharacter_PagerAdapter extends FragmentPagerAdapter
{
    int numOfTabs;
    public CreateCharacter_PagerAdapter(FragmentManager fm, int numOfTabs)
    {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position)
    {
        Fragment fragment;
        Bundle args = new Bundle();
        // Our object is just an integer :-P
        //args.putInt(Page_CreateCharacter_Race.ARG_OBJECT, i + 1);

        switch (position)
        {
            case 0:
                fragment = new Page_CreateCharacter_Race();
                fragment.setArguments(args);
                break;
            case 1:
                fragment = new Page_CreateCharacter_Class();
                fragment.setArguments(args);
                break;
            case 2:
                fragment = new Page_CreateCharacter_Background();
                fragment.setArguments(args);
                break;
            case 3:
                fragment = new Page_CreateCharacter_Gear();
                fragment.setArguments(args);
                break;
            case 4:
                fragment = new Page_CreateCharacter_Stats();
                fragment.setArguments(args);
                break;

            default:
                fragment = new Page_CreateCharacter_Race();
                fragment.setArguments(args);
                break;
        }

        return fragment;
        /*
        switch(position)
        {
            case 0:
                Page_CreateCharacter_Race createCharacter_race = new Page_CreateCharacter_Race();
                return createCharacter_race;
            case 1:
                Page_CreateCharacter_Class createCharacter_class = new Page_CreateCharacter_Class();
                return createCharacter_class;
            case 2:
                Page_CreateCharacter_Background createCharacter_background = new Page_CreateCharacter_Background();
                return createCharacter_background;
            case 3:
                Page_CreateCharacter_Gear page_createCharacter_gear = new Page_CreateCharacter_Gear();
                return page_createCharacter_gear;
            case 4:
                Page_CreateCharacter_Stats createCharacter_stats = new Page_CreateCharacter_Stats();
                return createCharacter_stats;



            default:
                return null;
        }
        */
    }

    @Override
    public int getCount()
    {
        return numOfTabs;
    }
}

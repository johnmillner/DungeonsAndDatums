package com.dungeonsanddatums.dungeonsanddatums.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.dungeonsanddatums.dungeonsanddatums.Page_Character_attacks;
import com.dungeonsanddatums.dungeonsanddatums.Page_Character_details;
import com.dungeonsanddatums.dungeonsanddatums.Page_Character_features;
import com.dungeonsanddatums.dungeonsanddatums.Page_Character_inventory;
import com.dungeonsanddatums.dungeonsanddatums.Page_Character_stats;

/**
 * Created by matth on 3/28/2018.
 */

public class CharacterSheet_PagerAdapter extends FragmentStatePagerAdapter
{
    int numOfTabs;
    public CharacterSheet_PagerAdapter(FragmentManager fm, int numOfTabs)
    {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position)
    {
        switch(position)
        {
            case 0:
                Page_Character_attacks character_attacks = new Page_Character_attacks();
                return character_attacks;
            case 1:
                Page_Character_stats character_stats = new Page_Character_stats();
                return character_stats;
            case 2:
                Page_Character_features character_features = new Page_Character_features();
                return character_features;
            case 3:
                Page_Character_inventory character_inventory = new Page_Character_inventory();
                return character_inventory;
            case 4:
                Page_Character_details character_details = new Page_Character_details();
                return character_details;
            default:
                return null;
        }
    }

    @Override
    public int getCount()
    {
        return numOfTabs;
    }
}

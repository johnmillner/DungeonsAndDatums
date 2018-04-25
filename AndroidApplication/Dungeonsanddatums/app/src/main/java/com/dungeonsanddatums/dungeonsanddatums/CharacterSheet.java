package com.dungeonsanddatums.dungeonsanddatums;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.dungeonsanddatums.dungeonsanddatums.Adapters.CharacterSheet_PagerAdapter;

public class CharacterSheet extends AppCompatActivity implements Page_Character_attacks.OnFragmentInteractionListener, Page_Character_details.OnFragmentInteractionListener, Page_Character_features.OnFragmentInteractionListener, Page_Character_inventory.OnFragmentInteractionListener, Page_Character_stats.OnFragmentInteractionListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_sheet);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout_CharacterSheet);
        tabLayout.addTab(tabLayout.newTab().setText("attacks"));
        tabLayout.addTab(tabLayout.newTab().setText("stats"));
        tabLayout.addTab(tabLayout.newTab().setText("features"));
        tabLayout.addTab(tabLayout.newTab().setText("gear"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.vp_CharacterSheet);
        final CharacterSheet_PagerAdapter characterSheetPagerAdapter = new CharacterSheet_PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(characterSheetPagerAdapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab)
            {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab)
            {
                viewPager.setCurrentItem(tab.getPosition());
            }

        });
    }

    @Override
    public void onFragmentInteraction(Uri uri)
    {

    }
}

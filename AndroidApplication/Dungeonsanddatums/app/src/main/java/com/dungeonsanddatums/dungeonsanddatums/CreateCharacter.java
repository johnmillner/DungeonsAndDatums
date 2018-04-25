package com.dungeonsanddatums.dungeonsanddatums;

import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.dungeonsanddatums.dungeonsanddatums.Adapters.CharacterSheet_PagerAdapter;
import com.dungeonsanddatums.dungeonsanddatums.Adapters.CreateCharacter_PagerAdapter;
import com.dungeonsanddatums.dungeonsanddatums.Holders.DnDCharacter;

public class CreateCharacter extends AppCompatActivity implements Page_CreateCharacter_Race.OnFragmentInteractionListener, Page_CreateCharacter_Class.OnFragmentInteractionListener, Page_CreateCharacter_Background.OnFragmentInteractionListener, Page_CreateCharacter_Stats.OnFragmentInteractionListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        DnDCharacter character = new DnDCharacter();

        setContentView(R.layout.activity_create_character);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout_CreateCharacter);
        tabLayout.addTab(tabLayout.newTab().setText("race"));
        tabLayout.addTab(tabLayout.newTab().setText("class"));
        tabLayout.addTab(tabLayout.newTab().setText("background"));
        tabLayout.addTab(tabLayout.newTab().setText("gear"));
        tabLayout.addTab(tabLayout.newTab().setText("stats"));
        //tabLayout.addTab(tabLayout.newTab().setText("details"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        //transaction.replace(R.id.vp_CreateCharacter,createCharacter_race);
        transaction.commit();

        final ViewPager viewPager = (ViewPager) findViewById(R.id.vp_CreateCharacter);
        final CreateCharacter_PagerAdapter createCharacter_pagerAdapter  = new CreateCharacter_PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(createCharacter_pagerAdapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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

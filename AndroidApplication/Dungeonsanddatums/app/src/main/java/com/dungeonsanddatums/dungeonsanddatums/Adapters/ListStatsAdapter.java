package com.dungeonsanddatums.dungeonsanddatums.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.dungeonsanddatums.dungeonsanddatums.Page_CreateCharacter_Race;
import com.dungeonsanddatums.dungeonsanddatums.R;

import org.w3c.dom.Text;

/**
 * Created by matth on 4/16/2018.
 */

public class ListStatsAdapter extends ArrayAdapter<String>
{
    private final Context context;
    private final String[] statValues;
    private final View parentView;
    int availablePoints;
    TextView tv_statModifier;

    // input from other fragments
    String raceSelected;
    String subraceSelected;



    public ListStatsAdapter(Context context, String[] statValues, View parentView, int availablePoints)
    {
        super(context, -1, statValues);
        this.context = context;
        this.statValues = statValues;
        this.parentView = parentView;
        parentView.findViewById(R.id.tv_numOfPoints);
        this.availablePoints = availablePoints;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_stats, parent, false);
        final TextView tv_attribute = (TextView) rowView.findViewById(R.id.tv_statAttribute);
        final TextView tv_stat = (TextView) rowView.findViewById(R.id.tv_statScore);
        Button b_plus = (Button) rowView.findViewById(R.id.b_statPlus);
        Button b_minus = (Button) rowView.findViewById((R.id.b_statMinus));

        final TextView tv_numOfPoints = (TextView) parentView.findViewById(R.id.tv_numOfPoints);
        String attribute = context.getResources().getStringArray(R.array.stats_description)[position];
        // set textViews for attributes (str, dex, etc.)
        tv_attribute.setText(context.getResources().getStringArray(R.array.stats_description)[position]);
        tv_stat.setText(statValues[position]);

        // set actions for buttons
        b_plus.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {

                int num = Integer.valueOf(tv_stat.getText().toString());
                if(availablePoints > 0 && num < 15)
                {
                    // change variables
                    num++;
                    availablePoints = availablePoints - 1;

                    if(num == 14 || num == 15)
                    {
                        availablePoints = availablePoints -1;
                    }

                    statValues[position] = Integer.toString(Integer.valueOf(statValues[position]) + 1);


                    // update views
                    tv_stat.setText(Integer.toString(num));
                    tv_numOfPoints.setText(Integer.toString(availablePoints));

                }
            }
        });

        b_minus.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                int num = Integer.valueOf(tv_stat.getText().toString());

                if(num > 0)
                {
                    num --;
                    if(num == 15 || num == 14)
                    {
                        availablePoints += 2;
                    }
                    else
                    {
                        availablePoints++;
                    }

                    statValues[position] = Integer.toString(Integer.valueOf(statValues[position]) - 1);
                    tv_stat.setText(Integer.toString(num));
                    tv_numOfPoints.setText(Integer.toString(availablePoints));
                }

            }
        });

        tv_statModifier = rowView.findViewById(R.id.tv_statModifier);

        // input from other fragments
        raceSelected = Page_CreateCharacter_Race.getRace();
        subraceSelected = Page_CreateCharacter_Race.getSubrace();
        /* statModifier is defined as followed:
         * strength      (0)
         * dexterity     (1)
         * consititution (2)
         * wisdom        (3)
         * intelligence  (4)
         * charisma      (5)
         */
        int[] statModifiers = {0,0,0,0,0,0};

        switch(raceSelected)
        {
            case "Dwarf":
                for(int i = 0; i < statModifiers.length; i++)
                    statModifiers[i] = 0;
                statModifiers[2] = 2;
                break;

            case "Elf":
                for(int i = 0; i < statModifiers.length; i++)
                    statModifiers[i] = 0;
                statModifiers[1] = 2;
                break;

            case "Halfling":
                for(int i = 0; i < statModifiers.length; i++)
                    statModifiers[i] = 0;
                statModifiers[1] = 2;
                break;

            case "Human":
                for(int i = 0; i < statModifiers.length; i++)
                    statModifiers[i] = 1;
                break;

            case "Dragonborn":
                for(int i = 0; i < statModifiers.length; i++)
                    statModifiers[i] = 0;
                statModifiers[0] = 2;
                statModifiers[5] = 1;
                break;

            case "Gnome":
                for(int i = 0; i < statModifiers.length; i++)
                    statModifiers[i] = 0;
                statModifiers[4] = 2;
                break;
            case "Half-Elf":
                for(int i = 0; i < statModifiers.length; i++)
                    statModifiers[i] = 0;
                statModifiers[5] = 2;
                // TODO stat of choice increases by 1
                break;
            case "Half-Orc":
                for(int i = 0; i < statModifiers.length; i++)
                    statModifiers[i] = 0;
                statModifiers[0] = 2;
                statModifiers[2] = 1;
                break;
            case "Tiefling":
                for(int i = 0; i < statModifiers.length; i++)
                    statModifiers[i] = 0;
                statModifiers[4] = 1;
                statModifiers[5] = 2;
                break;
        }

        /* statModifier is defined as followed:
         * strength      (0)
         * dexterity     (1)
         * consititution (2)
         * wisdom        (3)
         * intelligence  (4)
         * charisma      (5)
         */
        switch(subraceSelected)
        {
            case "Hill Dwarf":
                statModifiers[3] += 1;
                //TODO Dwarven Toughness
                break;
            case "Mountain Dwarf":
                statModifiers[0] += 2;
                //TODO Dwarven Armor Training
                break;

            case "High Elf":
                statModifiers[4] += 1;
                //TODO Elf weapon Training
                //TODO Cantrip
                //TODO Extra Language
                break;
            case "Woof Elf":
                statModifiers[3] += 1;
                //TODO Elf Weapon Training
                //TODO Fleet of Foot
                //TODO Mask of the wild
                break;
            case "DarkElf":
                statModifiers[5] += 1;
                //TODO Superior Darkvision
                //TODO Sunlight Sensitivity
                //TODO Drow Magic
                //TODO Drow Weapon Training
                break;

            case "Lightfoot":
                statModifiers[5] += 1;
                //TODO Naturally Stealthy
                break;
            case "Stout":
                statModifiers[2] += 1;
                //TODO Stout Resilience
                break;

            case "Classic":
                for(int i = 0; i < statModifiers.length; i++)
                    statModifiers[i] = 1;
                break;
            case "Variant":
                //TODO Increase your choice of 2 ability scores by 1
                //TODO Gain 1 proficiency in skill of choice
                //TODO Gain 1 feat of choice
                break;

            case "Forest Gnome":
                statModifiers[1] += 1;
                //TODO Natural Illusionist
                //TODO Speak with Small Beasts
                break;
            case "Rock Gnome":
                statModifiers[2] += 1;
                //TODO artificer's lore
                //TODO Tinkerer
                break;
        }

        tv_statModifier.setText(Integer.toString(statModifiers[position]));

        return rowView;
    }

    public String[] getValues()
    {
        return statValues;
    }
}

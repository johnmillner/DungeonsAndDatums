package com.dungeonsanddatums.dungeonsanddatums.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.dungeonsanddatums.dungeonsanddatums.Holders.EquimentChoice;
import com.dungeonsanddatums.dungeonsanddatums.R;
import com.dungeonsanddatums.dungeonsanddatums.Holders.ViewHolder;


import java.util.ArrayList;

/**
 * Created by matth on 4/23/2018.
 */

public class GearListAdapter extends BaseAdapter
{
    //private String[] mData;
    private String[] equipmentOptions1;
    private String[] equipmentOptions2;
    private LayoutInflater mInflater;
    private Context context;
    private String classType;

    public GearListAdapter(Context context, String classType)
    {
        this.context = context;
        this.classType = classType;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        switch(classType)
        {
            case "Barbarian":
                equipmentOptions1 = context.getResources().getStringArray(R.array.equipment1_Barbarian);
                equipmentOptions2 = context.getResources().getStringArray(R.array.equipment2_Barbarian);
                break;

            case "Bard":
                equipmentOptions1 = context.getResources().getStringArray(R.array.equipment1_Bard);
                equipmentOptions2 = context.getResources().getStringArray(R.array.equipment2_Bard);
                break;

            case "Cleric":
                equipmentOptions1 = context.getResources().getStringArray(R.array.equipment1_Cleric);
                equipmentOptions2 = context.getResources().getStringArray(R.array.equipment2_Cleric);
                break;

            case "Druid":
                equipmentOptions1 = context.getResources().getStringArray(R.array.equipment1_Druid);
                equipmentOptions2 = context.getResources().getStringArray(R.array.equipment2_Druid);
                break;

            case "Fighter":
                equipmentOptions1 = context.getResources().getStringArray(R.array.equipment1_Fighter);
                equipmentOptions2 = context.getResources().getStringArray(R.array.equipment2_Fighter);
                break;

            case "Monk":
                equipmentOptions1 = context.getResources().getStringArray(R.array.equipment1_Monk);
                equipmentOptions2 = context.getResources().getStringArray(R.array.equipment2_Monk);
                break;

            case "Paladin":
                equipmentOptions1 = context.getResources().getStringArray(R.array.equipment1_Paladin);
                equipmentOptions2 = context.getResources().getStringArray(R.array.equipment2_Paladin);
                break;

            case "Ranger":
                equipmentOptions1 = context.getResources().getStringArray(R.array.equipment1_Ranger);
                equipmentOptions2 = context.getResources().getStringArray(R.array.equipment2_Ranger);
                break;

            case "Rogue":
                equipmentOptions1 = context.getResources().getStringArray(R.array.equipment1_Rogue);
                equipmentOptions2 = context.getResources().getStringArray(R.array.equipment2_Rogue);
                break;

            case "Sorcerer":
                equipmentOptions1 = context.getResources().getStringArray(R.array.equipment1_Sorcerer);
                equipmentOptions2 = context.getResources().getStringArray(R.array.equipment2_Sorcerer);
                break;

            case "Warlock":
                equipmentOptions1 = context.getResources().getStringArray(R.array.equipment1_Warlock);
                equipmentOptions2 = context.getResources().getStringArray(R.array.equipment2_Warlock);
                break;

            default:
                equipmentOptions1 = context.getResources().getStringArray(R.array.equipment1_Wizard);
                equipmentOptions2 = context.getResources().getStringArray(R.array.equipment2_Wizard);
                break;
        }

    }
    /*
    public void addItem(final String item) {
        mData.add(item);
        notifyDataSetChanged();
    }
    */

    @Override
    public int getItemViewType(int position)
    {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public int getCount() {
        return equipmentOptions1.length;
    }

    @Override
    public String getItem(int position) {
        return equipmentOptions1[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        int type = getItemViewType(position);
        if (convertView == null)
        {
            holder = new ViewHolder();

            convertView = mInflater.inflate(R.layout.list_gear, null);
            holder.checkbox1 = (CheckBox) convertView.findViewById(R.id.cb_gearOption1);
            holder.checkBox2 = (CheckBox) convertView.findViewById(R.id.cb_gearOption2);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.checkbox1.setText(equipmentOptions1[position]);
        holder.checkBox2.setText(equipmentOptions2[position]);
        return convertView;
    }
}

package com.dungeonsanddatums.dungeonsanddatums.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dungeonsanddatums.dungeonsanddatums.R;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by matth on 3/5/2018.
 */

public class CampaignListAdapter extends RecyclerView.Adapter<CampaignListAdapter.ArticleViewHolder>
{
    private int numOfCampaigns;
    JSONArray campaigns;
    private static final String TAG = CampaignListAdapter.class.getSimpleName();

    /*
     * An on-click handler that we've defined to make it easy for an Activity to interface with
     * our RecyclerView
     */
    final private ListItemClickListener mOnClickListener;

    /**
     * The interface that receives onClick messages.
     */
    public interface ListItemClickListener
    {
        void onListItemClick(int clickedItemIndex);
    }

    /**
     * Constructor for Adapter; includes a list item click listener
     *
     * @param listener Listener for list item clicks
     */
    public CampaignListAdapter(ListItemClickListener listener, JSONArray campaigns)
    {
        mOnClickListener = listener;
        numOfCampaigns = campaigns.length();
        this.campaigns = campaigns;
    }

    /**
     *
     * This gets called when each new ViewHolder is created. This happens when the RecyclerView
     * is laid out. Enough ViewHolders will be created to fill the screen and allow for scrolling.
     *
     * @param viewGroup The ViewGroup that these ViewHolders are contained within.
     * @param viewType  If your RecyclerView has more than one type of item (which ours doesn't) you
     *                  can use this viewType integer to provide a different layout. See
     *                  {android.support.v7.widget.RecyclerView.Adapter#getItemViewType(int)}
     *                  for more details.
     * @return A new ArticleViewHolder that holds the View for each list item
     */
    @Override
    public CampaignListAdapter.ArticleViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.selector_campaigns;

        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        ArticleViewHolder viewHolder = new ArticleViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CampaignListAdapter.ArticleViewHolder holder, int position)
    {
        try
        {
            holder.bindCampaign(campaigns.getJSONObject(position));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        Log.d(TAG, "#" + position);

        //Set values if given from database - for now they are placeholders in the list_item xml,
        // so this won't be implemented
    }


    /**
     * This method simply returns 10 items to display. It is used behind the scenes
     * to help layout our Views and for animations.
     *
     * @return The number of Campaigns
     */
    @Override
    public int getItemCount()
    {
        return numOfCampaigns; //TODO change return to represent the number of campaigns
    }

    /**
     * Cache of the children views for a list item.
     */
    class ArticleViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener
    {

        ImageView icon;
        TextView campaignNameTextView;
        TextView characterNameTextView;


        public ArticleViewHolder(View itemView) {
            super(itemView);

            icon = (ImageView) itemView.findViewById(R.id.personIcon);
            campaignNameTextView = (TextView) itemView.findViewById(R.id.campaignName);
            characterNameTextView= (TextView) itemView.findViewById(R.id.characterName);

            itemView.setOnClickListener(this);
        }


        /**
         * Called whenever a user clicks on an item in the list.
         * @param v The View that was clicked
         */
        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }

        public void bindCampaign(JSONObject campaign)
        {
            try
            {
                campaignNameTextView.setText(campaign.getString("name"));

            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }

}

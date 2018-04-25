package com.dungeonsanddatums.dungeonsanddatums.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by matth on 3/10/2018.
 */

public class CharacterListAdapter extends RecyclerView.Adapter<CharacterListAdapter.ArticleViewHolder>
{

    @Override
    public CharacterListAdapter.ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(CharacterListAdapter.ArticleViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ArticleViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener
    {
        ImageView characterPortrait;
        TextView characterNameTextView;

        public ArticleViewHolder(View itemView)
        {
            super(itemView);

        }

        @Override
        public void onClick(View v) {

        }
    }
}

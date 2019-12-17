package com.tourism.wiproapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tourism.wiproapp.R;
import com.tourism.wiproapp.model.Rows;

import java.util.List;


/**
 * This class is the adapter class for the Country Details list
 */
public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {

    private List<Rows> mCountryInfoList;
    private Context mContext;
    private final OnItemClickListener listener;
    private String mCountryName;

    public CountryAdapter(Context context, String countryName, List<Rows> countryInfoList, OnItemClickListener listener) {
        mCountryInfoList = countryInfoList;
        mContext = context;
        mCountryName = countryName;
        this.listener = listener;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Rows countryInfo = mCountryInfoList.get(position);

        if (countryInfo.getmTitle() != null) {
            holder.tvTitle.setText(countryInfo.getmTitle());
        } else {
            holder.tvTitle.setText("");
        }
        if (countryInfo.getmDescription() != null) {
            holder.tvDescription.setText(countryInfo.getmDescription());
        } else {
            holder.tvDescription.setText("");
        }

        if (countryInfo.getmLink() != null) {
            Glide.with(mContext)
                    .load(countryInfo.getmLink())
                    .error(Glide.with(holder.ivThumbnail).load(R.drawable.ic_error_placeholder))
                    .into(holder.ivThumbnail);
        } else {
            Glide.with(mContext)
                    .load(Glide.with(holder.ivThumbnail).load(R.drawable.ic_error_placeholder))
                    .into(holder.ivThumbnail);
        }

        holder.ivNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(countryInfo,mCountryName);
            }
        });
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.country_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        if (mCountryInfoList != null && mCountryInfoList.size() > 0) {
            return mCountryInfoList.size();
        } else {
            return 0;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    // Used to cache the views within the item layout for fast access
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivThumbnail;
        TextView tvTitle;
        TextView tvDescription;
        ImageView ivNext;


        public ViewHolder(View itemView) {
            super(itemView);
            ivThumbnail = itemView.findViewById(R.id.ivThumbnail);
            tvTitle = itemView.findViewById(R.id.countryTitle);
            tvDescription = itemView.findViewById(R.id.countryDescription);
            ivNext = itemView.findViewById(R.id.ivNext);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Rows item,String cxountryName);
    }
}
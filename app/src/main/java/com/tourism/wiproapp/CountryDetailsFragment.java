package com.tourism.wiproapp;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;
import com.tourism.wiproapp.model.Rows;

/**
 * This class is the fragment will read the country info and send to adapter class
 */
public class CountryDetailsFragment extends Fragment {

    private View rootView;
    private TextView mTooBarTitle;
    private TextView mTitle;
    private TextView mDescription;
    private ImageView mThumbNail;
    private ImageView mBack;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_country_details, container, false);
        return rootView;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initializationViews();
        setData();
        setListeners();

    }

    /**
     * This method will setup back click listener
     */
    private void setListeners() {
        mBack.setOnClickListener(v -> {
            FragmentManager fm = getActivity().getSupportFragmentManager();
            fm.popBackStack();
        });
    }

    /**
     * This method will read data from bundle and update the ui
     */
    private void setData() {
        if (getArguments() != null) {
            String countryName = getArguments().getString(Constants.COUNTRY_NAME);
            Rows countryInfo = (Rows) getArguments().getSerializable(Constants.COUNTRY_INFO);
            if (countryName != null) {
                mTooBarTitle.setText(countryName);
            }
            if (countryInfo.getmTitle() != null) {
                mTitle.setText(countryInfo.getmTitle());
            }
            if (countryInfo.getmDescription() != null) {
                mDescription.setText(countryInfo.getmDescription());
            }
            if (countryInfo.getmLink() != null) {
                Glide.with(getActivity())
                        .load(Glide.with(mThumbNail).load(countryInfo.getmLink())
                                .into(mThumbNail));
            }
        }

    }

    /**
     * This method will initialises the views
     */
    private void initializationViews() {

        mTooBarTitle = rootView.findViewById(R.id.toolbarTitle);
        mBack = rootView.findViewById(R.id.imgBack);
        mBack.setVisibility(View.VISIBLE);
        mThumbNail = rootView.findViewById(R.id.imageView);
        mTitle = rootView.findViewById(R.id.countryTitleTV);
        mDescription = rootView.findViewById(R.id.countryDescriptionTV);
    }
}

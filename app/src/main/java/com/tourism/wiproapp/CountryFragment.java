package com.tourism.wiproapp;

import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.tourism.wiproapp.adapter.CountryAdapter;
import com.tourism.wiproapp.model.Country;
import com.tourism.wiproapp.model.Rows;
import com.tourism.wiproapp.viewmodel.CountryViewModel;

import java.util.List;

/**
 * This class is the fragment will read the country info and send to adapter class
 */
public class CountryFragment extends Fragment implements CountryAdapter.OnItemClickListener {

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private TextView mTooBarTitle;
    private CountryViewModel countryViewModel;
    private CountryAdapter mCountryAdapter;
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_country, container, false);
        return rootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initializationViews();
        countryViewModel = ViewModelProviders.of(this).get(CountryViewModel.class);

        if (NetworkUtils.isNetworkAvailable(getActivity())) {
            getCountryDetails();
        } else {
            noInternetDialog();
        }

    }

    /**
     * This method will initialises the views
     */
    private void initializationViews() {
        mRecyclerView = rootView.findViewById(R.id.blogRecyclerView);
        mSwipeRefreshLayout = rootView.findViewById(R.id.swiperefresh);
        mTooBarTitle = rootView.findViewById(R.id.toolbarTitle);

        // lambda expression
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            if (NetworkUtils.isNetworkAvailable(getActivity())) {
                getCountryDetails();
            } else {
                noInternetDialog();
            }
        });

    }

    /**
     * This method will prepare recycler view data
     */
    private void prepareRecyclerView(List<Rows> blogList, String countryName) {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);

        for (int i = 0; i < blogList.size(); i++) {

            if (blogList.get(i).getmTitle() == null && blogList.get(i).getmDescription() == null && blogList.get(i).getmLink() == null) {
                blogList.remove(i);
            }
        }
        mCountryAdapter = new CountryAdapter(getActivity(), countryName, blogList, this::onItemClick);

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        } else {
            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4));

        }
        mRecyclerView.setAdapter(mCountryAdapter);
        mCountryAdapter.notifyDataSetChanged();

    }


    /**
     * This method will read the country details form live data observer
     */
    private void getCountryDetails() {

        countryViewModel.getCountryInfo().observe(this, new Observer<Country>() {
            @Override
            public void onChanged(@Nullable Country country) {
                mSwipeRefreshLayout.setRefreshing(false);
                if (country != null) {
                    mTooBarTitle.setText(country.getCountryName());
                    if (country.getmData() != null) {
                        prepareRecyclerView(country.getmData(), country.getCountryName());
                    }

                }
            }
        });

    }

    /**
     * This method will display no internet alert dialog
     */
    private void noInternetDialog() {
        mSwipeRefreshLayout.setRefreshing(false);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getActivity().getResources().getString(R.string.no_internet_title));
        builder.setMessage(getActivity().getResources().getString(R.string.no_internet_msg));
        builder.setNegativeButton(getActivity().getResources().getString(R.string.ok_msg), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void onItemClick(Rows item, String countryName) {
        CountryDetailsFragment ldf = new CountryDetailsFragment();
        Bundle args = new Bundle();
        args.putString(Constants.COUNTRY_NAME, countryName);
        args.putSerializable(
                Constants.COUNTRY_INFO,
                item);
        ldf.setArguments(args);

        //Inflate the fragment
        getFragmentManager().beginTransaction().replace(R.id.container, ldf).addToBackStack(Constants.COUNTRY_NAME).commit();
    }
}

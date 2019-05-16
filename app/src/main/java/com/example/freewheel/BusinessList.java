package com.example.freewheel;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class BusinessList extends Fragment implements ImageButton.OnClickListener {

    private List<Business> businessList = new ArrayList<>();
    private SearchView searchView;
    private RecyclerView recyclerView;
    private BusinessRecyclerAdapter adapter;
    private ImageButton addPlace;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_outer, container, false);

        handlingRecycleViewer(view);

        return view;
    }


    private void handlingRecycleViewer(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.list);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        adapter = new BusinessRecyclerAdapter(businessList, new BusinessRecyclerAdapter.OnItemClickListener() {
            @Override public void onItemClick(Business business) {
                BusinessPageFragment.businessToDisplay = business;
                loadBusinessPageFragment();
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }



    /**
     * this function replaces the layout to a book page layout in case some book was clicked in the list
     */
    private void loadBusinessPageFragment() {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack("ListView");  // enables to press "return" and go back to the list view
        transaction.replace(R.id.main_fragment, new BusinessPageFragment());
        transaction.commit();
    }






    @Override
    public void onClick(View v) {
        /* when + button is clicked */


    }
}

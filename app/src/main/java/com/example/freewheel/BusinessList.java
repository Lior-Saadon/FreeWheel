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

import java.util.ArrayList;
import java.util.List;

public class BusinessList extends Fragment implements SearchView.OnQueryTextListener {

    private List<Business> businessList = new ArrayList<>();
    private RecyclerView recyclerView;
    private BusinessRecyclerAdapter adapter;
    private SearchView searchView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_layout, container, false);


        handlingSearchView(view);

        /* handling recycleViewer */
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

        return view;
    }



    private void handlingSearchView(View view) {
        searchView = (SearchView) view.findViewById(R.id.search_bar);
        CharSequence query = searchView.getQuery(); // get the query string currently in the text field
        searchView.setQueryHint("Where do you wanna go?");
        searchView.setOnQueryTextListener(this);
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
    public boolean onQueryTextSubmit(String query) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String place) {
        adapter.filter(place);
        return true;
    }



}

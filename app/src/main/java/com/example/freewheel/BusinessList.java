package com.example.freewheel;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SearchView;

import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BusinessList extends Fragment implements ImageButton.OnClickListener {

    private List<GoogleInfo> businessList = new ArrayList<>();
    private RecyclerView recyclerView;
    private BusinessRecyclerAdapter adapter;
    private EditText search_bar;
    private ImageButton addPlace;

    private Button search;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_outer, container, false);

        handlingRecycleViewer(view);

        return view;
    }


    private void handlingRecycleViewer(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.list);
        search_bar = (EditText) view.findViewById(R.id.input_search);

        search = (Button) view.findViewById(R.id.search);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Requests r = Requests.getInstance(getContext());
                r.locationRequest(search_bar.getText().toString(), new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            businessList.clear();
                            adapter.notifyDataSetChanged();
                            JSONArray arr = response.getJSONArray("results");
                            for (int i = 0; i < arr.length(); i++) {
                                businessList.add(new GoogleInfo(arr.getJSONObject(i), getContext(), adapter));
                            }
                        } catch (Exception e) {
                        }
                    }
                });

            }
        });



        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        adapter = new BusinessRecyclerAdapter(businessList, new BusinessRecyclerAdapter.OnItemClickListener() {
            @Override public void onItemClick(GoogleInfo business) {

                BusinessPageFragment.businessToDisplay = business;
                ServerApi.getInstance().getAccessibilities(business, getFragmentManager());
                //
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }






    @Override
    public void onClick(View v) {
        /* when + button is clicked */


    }
}

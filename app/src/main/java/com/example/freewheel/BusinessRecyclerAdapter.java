package com.example.freewheel;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;


public class BusinessRecyclerAdapter extends RecyclerView.Adapter<BusinessRecyclerAdapter.MyViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Business business);
    }

    private final List<Business> businessList, copyList;
    private final OnItemClickListener mListener;


    public BusinessRecyclerAdapter(List<Business> list, OnItemClickListener listener) {
        this.businessList = list;
        this.mListener = listener;
        this.copyList = new ArrayList<>();
        copyList.addAll(list);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_singleitem, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.bind(businessList.get(position), mListener);
    }

    @Override
    public int getItemCount() {
        return businessList.size();
    }


    public void filter(String queryText)
    {
        businessList.clear();

        if(queryText.isEmpty()) {
            businessList.addAll(copyList);
            return;
        }
        for(Business business: copyList) {
            if(business.getName().toLowerCase().contains(queryText.toLowerCase())) {
               businessList.add(business);
            }
        }
        notifyDataSetChanged();
    }

    //--------------------------------------


    static class MyViewHolder extends RecyclerView.ViewHolder {
        private Business business;
        private TextView name, address;
        private ImageView logo;
        private View mView;

        public MyViewHolder(View view) {
            super(view);
            mView = view;
            name = (TextView) view.findViewById(R.id.Business_name);
            address = (TextView) view.findViewById(R.id.business_address);
            logo = (ImageView) view.findViewById(R.id.logo);
        }

        public void bind(final Business business, final OnItemClickListener listener) {
            name.setText(business.getName());
            address.setText(business.getAdrress());
            logo.setImageResource(business.getLogo());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View view) {
                    listener.onItemClick(business);
                }
            });
        }

    }
}




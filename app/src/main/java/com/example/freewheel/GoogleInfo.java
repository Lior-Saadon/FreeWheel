package com.example.freewheel;

import android.content.Context;
import android.graphics.Bitmap;

import com.android.volley.Response;

import org.json.JSONObject;

public class GoogleInfo {
    private String id, name, address;
    private Bitmap image;
    public GoogleInfo(JSONObject json, Context context, final BusinessRecyclerAdapter adapter)
    {
        image = null;
        try{
            this.id = json.getString("place_id");
            this.name = json.getString("name");
            this.address= json.getString("formatted_address");
            Requests r = Requests.getInstance(context);
            r.requestImage(json.getJSONArray("photos").getJSONObject(0).getString("photo_reference"),
                    new Response.Listener<Bitmap>() {
                        @Override
                        public void onResponse(Bitmap response) {
                            image = response;
                            adapter.notifyDataSetChanged();
                        }
                    });
        }
        catch (Exception e)
        {
            this.id = "error";
            this.name = "error";
            this.address = "error";
        }

    }


    public Bitmap getImage()
    {
        return  this.image;
    }

    public String getId()
    {
        return this.id;
    }

    public String getName()
    {
        return this.name;
    }

    public String getAddress()
    {
        return this.address;
    }
}

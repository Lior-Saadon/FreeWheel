package com.example.freewheel;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class Requests {
    private static Requests requests = null;
    private RequestQueue queue;
    private static Context _context;

    private Requests(Context context) {
        _context = context;
        queue = Volley.newRequestQueue(context);
    }

    public static Requests getInstance(Context context)
    {
        if (requests == null)
        {
            requests = new Requests(context);
        }
        return requests;
    }

    public void locationRequest(String searchBar, Response.Listener<JSONObject> res)
    {
        String url = _context.getResources().getString(R.string.places_api_url);
        url += "&input=" + searchBar;
        url += "&key=" + _context.getResources().getString(R.string.places_api_key);
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url, null, res,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        this.queue.add(req);
    }

}

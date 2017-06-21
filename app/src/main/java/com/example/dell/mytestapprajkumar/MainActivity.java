package com.example.dell.mytestapprajkumar;

/**
 * Created by dell on 23/05/2017.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends Activity implements Config {

    private List<RandomUser> listSuperHeroes;
    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RandomUserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Initializing our superheroes list
        listSuperHeroes = new ArrayList<>();

        //Calling method to get data
        getData();

    }

    //This method will get data from the web api
    private void getData() {
        //Showing a progress dialog
        if (isNetworkAvailable()) {

            final ProgressDialog loading = ProgressDialog.show(this, "Loading Data", "Please wait...", false, false);

            Runnable progressRunnable = new Runnable() {

                @Override
                public void run() {
                    loading.cancel();
                }
            };

            Handler pdCanceller = new Handler();
            pdCanceller.postDelayed(progressRunnable, 60000);
            StringRequest stringRequest = new StringRequest(DATA_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            loading.dismiss();
                            try {
                                parseData(response);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //Toast.makeText(MainActivityess.this,error.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    });


            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        } else {
            Toast.makeText(this, "Check your internet connections", Toast.LENGTH_LONG).show();
        }
    }

    private void parseData(String json) throws JSONException {
        listSuperHeroes = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(json);
        JSONArray users = jsonObject.getJSONArray("results");
        for (int i = 0; i < users.length(); i++) {
            try {
                JSONObject jsons = users.getJSONObject(i);
                String email = (jsons.isNull(Email) || jsons.getString(Email).equals("")) ? "" : jsons.getString(Email);
                String phone = (jsons.isNull(Phone) || jsons.getString(Phone).equals("")) ? "" : jsons.getString(Phone);
                JSONObject jsonObjec = jsons.getJSONObject("name");
                String title = (jsonObjec.isNull(Title) || jsonObjec.getString(Title).equals("")) ? "" : jsonObjec.getString(Title);
                String first = (jsonObjec.isNull(First) || jsonObjec.getString(First).equals("")) ? "" : jsonObjec.getString(First);
                String last = (jsonObjec.isNull(Last) || jsonObjec.getString(Last).equals("")) ? "" : jsonObjec.getString(Last);
                JSONObject jsonObjectLocation = jsons.getJSONObject("location");
                String street = (jsonObjectLocation.isNull(Street) || jsonObjectLocation.getString(Street).equals("")) ? "" : jsonObjectLocation.getString(Street);
                String city = (jsonObjectLocation.isNull(City) || jsonObjectLocation.getString(City).equals("")) ? "" : jsonObjectLocation.getString(City);
                String state = (jsonObjectLocation.isNull(State) || jsonObjectLocation.getString(State).equals("")) ? "" : jsonObjectLocation.getString(State);
                String postcode = (jsonObjectLocation.isNull(Postcode) || jsonObjectLocation.getString(Postcode).equals("")) ? "" : jsonObjectLocation.getString(Postcode);
                JSONObject jsonObjectPicture = jsons.getJSONObject("picture");
                String thumbnail = (jsonObjectPicture.isNull(Thumbnail) || jsonObjectPicture.getString(Thumbnail).equals("")) ? "" : jsonObjectPicture.getString(Thumbnail);
                listSuperHeroes.add(new RandomUser(city, email, first, last, phone, postcode, state, street, thumbnail, title));

            } catch (JSONException e) {
                e.printStackTrace();
            }
            //    listSuperHeroes.add(superHero);
        }

        //Finally initializing our adapter
        adapter = new RandomUserAdapter(listSuperHeroes, this);

        //Adding adapter to recyclerview
        recyclerView.setAdapter(adapter);
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}

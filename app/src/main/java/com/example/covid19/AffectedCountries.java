package com.example.covid19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.models.PieModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AffectedCountries extends AppCompatActivity {

    EditText searchET;
    SimpleArcLoader simpleArcLoader;
    RecyclerView listView;

    public static List<CountryModel> countryModelList=new ArrayList<>();

    CountryModel countryModel;
    
    CustomAdapter myCustomAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affected_countries);

        getSupportActionBar().setTitle("Affected CountryList");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        searchET=findViewById(R.id.edtSearch);
        simpleArcLoader=findViewById(R.id.acloader);
        listView=findViewById(R.id.listView);
        myCustomAdapter =new CustomAdapter(AffectedCountries.this,countryModelList);





        fetchData();

        searchET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
        
    }

    private void fetchData() {

        String url="https://corona.lmao.ninja/v2/countries/";

        simpleArcLoader.start();

        StringRequest request=new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {


                            JSONArray jsonArray=new JSONArray(response);

                            for(int i=0;i<jsonArray.length();i++){


                                JSONObject jsonObject=jsonArray.getJSONObject(i);

                                String countryName=jsonObject.getString("country");
                                String cases=jsonObject.getString("cases");
                                String todayCases=jsonObject.getString("todayCases");
                                String deaths=jsonObject.getString("deaths");
                                String todayDeaths=jsonObject.getString("todayDeaths");
                                String active=jsonObject.getString("active");
                                String critical=jsonObject.getString("critical");
                                String recovered=jsonObject.getString("recovered");


                                JSONObject object=jsonObject.getJSONObject("countryInfo");
                                String  flagUrl=object.getString("flag");



                                countryModel=new CountryModel(flagUrl,countryName,cases,todayCases,deaths,todayDeaths,recovered,active,critical);

                                countryModelList.add(countryModel);


                            }


                            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
                            listView.setLayoutManager(linearLayoutManager);
                            listView.setAdapter(myCustomAdapter);
                            simpleArcLoader.stop();

                            simpleArcLoader.setVisibility(View.GONE);
                            listView.setVisibility(View.VISIBLE);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(AffectedCountries.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    private void filter(String text) {
        //new array list that will hold the filtered data
        List<CountryModel> filterdNames = new ArrayList<>();

        //looping through existing elements
        for (CountryModel s : countryModelList) {
            //if the existing elements contains the search input

            if (s.getCountry().toLowerCase().contains(text.toLowerCase())) {
                //adding the element to filtered list
                filterdNames.add(s);
            }
        }

        //calling a method of the adapter class and passing the filtered list
       myCustomAdapter.filterList(filterdNames);
    }
}

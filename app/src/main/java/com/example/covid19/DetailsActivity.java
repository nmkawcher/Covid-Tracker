package com.example.covid19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    TextView cases,todayCases,death,todayDeaths,recoverd,active,critical;

    int positionCountry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        getSupportActionBar().setTitle("Details of : "+AffectedCountries.countryModelList.get(positionCountry).getCountry().toString());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent=getIntent();


        positionCountry=intent.getIntExtra("position",0);


    cases=findViewById(R.id.tvCases);
    todayCases=findViewById(R.id.tvTodayCases);
    death=findViewById(R.id.tvTodayDeath);
    todayDeaths=findViewById(R.id.tvTotalDeath);
    recoverd=findViewById(R.id.tvRecover);
    critical=findViewById(R.id.tvCritical);
    active=findViewById(R.id.tvActive);

    cases.setText(AffectedCountries.countryModelList.get(positionCountry).getCases());
    todayCases.setText(AffectedCountries.countryModelList.get(positionCountry).getTodayCases());
    death.setText(AffectedCountries.countryModelList.get(positionCountry).getTotalDeaths());
    todayDeaths.setText(AffectedCountries.countryModelList.get(positionCountry).getDeaths());
    recoverd.setText(AffectedCountries.countryModelList.get(positionCountry).getRecovered());
    active.setText(AffectedCountries.countryModelList.get(positionCountry).getActive());
    critical.setText(AffectedCountries.countryModelList.get(positionCountry).getCritical());




    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

       if(item.getItemId()==android.R.id.home){
           finish();
       }
        return super.onOptionsItemSelected(item);
    }
}

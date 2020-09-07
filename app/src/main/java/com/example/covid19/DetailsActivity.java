package com.example.covid19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import static com.example.covid19.AffectedCountries.countryModelList;

public class DetailsActivity extends AppCompatActivity {

    TextView cases,todayCases,death,todayDeaths,recoverd,active,critical;

    int positionCountry;
    String countryName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent mIntent = getIntent();
        countryName = mIntent.getStringExtra("value");



     positionCountry= getIndex(countryName);





        getSupportActionBar().setTitle("Details of : "+countryName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);




    cases=findViewById(R.id.tvCases);
    todayCases=findViewById(R.id.tvTodayCases);
    death=findViewById(R.id.tvTodayDeath);
    todayDeaths=findViewById(R.id.tvTotalDeath);
    recoverd=findViewById(R.id.tvRecover);
    critical=findViewById(R.id.tvCritical);
    active=findViewById(R.id.tvActive);

    cases.setText(countryModelList.get(positionCountry).getCases());
    todayCases.setText(countryModelList.get(positionCountry).getTodayCases());
    death.setText(countryModelList.get(positionCountry).getTotalDeaths());
    todayDeaths.setText(countryModelList.get(positionCountry).getDeaths());
    recoverd.setText(countryModelList.get(positionCountry).getRecovered());
    active.setText(countryModelList.get(positionCountry).getActive());
    critical.setText(countryModelList.get(positionCountry).getCritical());




    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

       if(item.getItemId()==android.R.id.home){
           finish();
       }
        return super.onOptionsItemSelected(item);
    }

  public int getIndex(String countryName)
    {
        for (int i = 0; i <countryModelList.size(); i++)
        {
             CountryModel countryModel = countryModelList.get(i);
            if (countryName.equals(countryModel.getCountry()))
            {
                return i;

            }
        }

        return -1;
    }
}

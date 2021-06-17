package com.example.coronaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private int positionCountry;

    TextView tvCountry,tveCases,tveRecovered,tveCritical,tveActive,tveTodayCases,tveTotalDeath,
            tveTodayDeath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        positionCountry = intent.getIntExtra("position",0);

        getSupportActionBar().setTitle("Details of "+ AffectedCountriesActivity.countryModelList.get(positionCountry).getCountry());
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        tvCountry = findViewById(R.id.tvCountryName1);
        tveCases = findViewById(R.id.tvCases1);
        tveRecovered = findViewById(R.id.tvRecovered1);
        tveCritical = findViewById(R.id.tvCritical1);
        tveActive = findViewById(R.id.tvActive1);
        tveTodayCases = findViewById(R.id.tvTodayCases1);
        tveTotalDeath = findViewById(R.id.tvTotalDeath1);
        tveTodayDeath = findViewById(R.id.tvTodayDeath1);

        tvCountry.setText(AffectedCountriesActivity.countryModelList.get(positionCountry).getCountry());
        tveCases.setText(AffectedCountriesActivity.countryModelList.get(positionCountry).getCases());
        tveRecovered.setText(AffectedCountriesActivity.countryModelList.get(positionCountry).getRecovered());
        tveCritical.setText(AffectedCountriesActivity.countryModelList.get(positionCountry).getCritical());
        tveTodayCases.setText(AffectedCountriesActivity.countryModelList.get(positionCountry).getTodayCases());
        tveTotalDeath.setText(AffectedCountriesActivity.countryModelList.get(positionCountry).getDeath());
        tveTodayDeath.setText(AffectedCountriesActivity.countryModelList.get(positionCountry).getTodayDeaths());
        tveActive.setText(AffectedCountriesActivity.countryModelList.get(positionCountry).getActive());



    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }
}
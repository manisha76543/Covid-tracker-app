package com.example.coronaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView tveCases,tveRecovered,tveCritical,tveActive,tveTodayCases,tveTotalDeath,tveTodayDeath,
    tveAffectedCountry;
    SimpleArcLoader simpleArcLoader;
    ScrollView scrollView;
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tveCases = findViewById(R.id.tvCases);
        tveRecovered = findViewById(R.id.tvRecovered);
        tveCritical = findViewById(R.id.tvCritical);
        tveActive = findViewById(R.id.tvActive);
        tveTodayCases = findViewById(R.id.tvTodayCases);
        tveTotalDeath = findViewById(R.id.tvTotalDeath);
        tveTodayDeath = findViewById(R.id.tvTodayDeath);
        tveAffectedCountry = findViewById(R.id.tvAffectedCountry);

        simpleArcLoader = findViewById(R.id.loader);
        scrollView = findViewById(R.id.scrollStates);
        pieChart = findViewById(R.id.piechart);


        fetchData();


    }

    private void fetchData() {

        String url = "https://corona.lmao.ninja/v2/all";

        simpleArcLoader.start();


        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response.toString());






                            tveCases.setText(jsonObject.getString("cases"));
                            tveRecovered.setText(jsonObject.getString("recovered"));
                            tveCritical.setText(jsonObject.getString("critical"));
                            tveActive.setText(jsonObject.getString("active"));
                            tveTodayCases.setText(jsonObject.getString("todayCases"));
                            tveTotalDeath.setText(jsonObject.getString("totalDeaths"));
                            tveTodayDeath.setText(jsonObject.getString("todayDeaths"));
                            tveAffectedCountry.setText(jsonObject.getString("affectedCountries"));


                            pieChart.addPieSlice(new PieModel("Cases",Integer.parseInt(tveCases.getText().toString()),
                                    Color.parseColor("#FFA726")));

                            pieChart.addPieSlice(new PieModel("Recovered",Integer.parseInt(tveRecovered.getText().toString()),
                                    Color.parseColor("#66BB6A")));

                            pieChart.addPieSlice(new PieModel("Deaths",Integer.parseInt(tveTotalDeath.getText().toString()),
                                    Color.parseColor("#FF6200EE")));

                            pieChart.addPieSlice(new PieModel("Active",Integer.parseInt(tveActive.getText().toString()),
                                    Color.parseColor("#29B6F6")));


                            pieChart.startAnimation();
                            simpleArcLoader.setVisibility(View.GONE);
                            scrollView.setVisibility(View.VISIBLE);



                        } catch (JSONException e)
                        {
                            e.printStackTrace();
                            simpleArcLoader.stop();
                            simpleArcLoader.setVisibility(View.GONE);
                            scrollView.setVisibility(View.VISIBLE);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                simpleArcLoader.stop();
                simpleArcLoader.setVisibility(View.GONE);
                scrollView.setVisibility(View.VISIBLE);
                Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

    }








    public void goTrackCountries(View view)
    {
        startActivity(new Intent(getApplicationContext(),AffectedCountriesActivity.class));
    }

}
package com.example.examenamst2.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.examenamst2.R;
import com.example.examenamst2.databinding.ActivityPerfilBindingImpl;
import com.example.examenamst2.databinding.ActivityResultsBinding;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;


public class perfil extends AppCompatActivity {
    public BarChart grafico;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityPerfilBindingImpl binding = DataBindingUtil
                .setContentView(this, R.layout.activity_perfil);
        RequestQueue mRequestQueue;
        Intent intent = getIntent();
        String id_hero = intent.getStringExtra("Id");

        mRequestQueue = Volley.newRequestQueue(this); // 'this' is the Context


        /*Json Request*/
        String url = "https://www.superheroapi.com/api.php/10220131994587867/"+id_hero;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            System.out.println(response);
                            binding.nombre.setText(response.getString("name"));
                            JSONObject biography = response.getJSONObject("biography");
                            binding.nombreReal.setText(biography.getString("full-name"));
                            ArrayList powerstats = new ArrayList();
                            powerstats.add(new BarEntry(Float.valueOf(response.getJSONObject("powerstats").getString("intelligence")), 0));
                            powerstats.add(new BarEntry(Float.valueOf(response.getJSONObject("powerstats").getString("strength")), 1));
                            powerstats.add(new BarEntry(Float.valueOf(response.getJSONObject("powerstats").getString("speed")), 2));
                            powerstats.add(new BarEntry(Float.valueOf(response.getJSONObject("powerstats").getString("durability")), 3));
                            powerstats.add(new BarEntry(Float.valueOf(response.getJSONObject("powerstats").getString("power")), 4));
                            powerstats.add(new BarEntry(Float.valueOf(response.getJSONObject("powerstats").getString("combat")), 5));

                            ArrayList<String> power = new ArrayList<>();

                            power.add("intelligence");
                            power.add("strength");
                            power.add("speed");
                            power.add("durability");
                            power.add("power");
                            power.add("combat");


                            BarDataSet bardataset = new BarDataSet(powerstats, "PowerStats");
                            binding.barChart.animateY(5000);
                            BarData data = new BarData(power,bardataset);
                            bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
                            binding.barChart.setData(data);
                            binding.barChart.invalidate();




                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.e("Response", Objects.requireNonNull(error.getMessage()));

                    }
                });
        //add request to queue
        mRequestQueue.add(jsonObjectRequest);




    }







}

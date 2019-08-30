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
import com.example.examenamst2.databinding.ActivityLogInBinding;
import com.example.examenamst2.databinding.ActivityResultsBinding;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.LinkedList;
import java.util.Objects;

public class ResultsActivity extends AppCompatActivity {
    RequestQueue mRequestQueue;
    String searchName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityResultsBinding binding = DataBindingUtil
                .setContentView(this, R.layout.activity_results);
        Intent ingreso = getIntent();
        searchName = ingreso.getStringExtra("heroName");
        mRequestQueue = Volley.newRequestQueue(this); // 'this' is the Context


        /*Json Request*/
        String url = "https://www.superheroapi.com/api.php/10220131994587867/search/"+searchName;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Response", "Si");
                        try {
                            final JSONArray lista_results =  (JSONArray) response.get("results");

                            binding.results.setText("Resultados:  "+lista_results.length());
                            for (int i = 0; i<lista_results.length();i++){
                                final int index = i;
                                Log.d("Nombre",lista_results.getJSONObject(i).getString("name") );
                                final TextView textView = new TextView(getApplicationContext());
                                textView.setTextSize(18);
                                textView.setText(lista_results.getJSONObject(i).getString("name"));

                                textView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        try {
                                            Log.d("click", lista_results.getJSONObject(index).getString("id"));
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });

                                binding.mainLinear.addView(textView);

                            }


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

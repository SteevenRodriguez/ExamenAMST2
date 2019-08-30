package com.example.examenamst2.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.examenamst2.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.ChartData;


public class perfil extends AppCompatActivity {
    public BarChart grafico;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
    }

}

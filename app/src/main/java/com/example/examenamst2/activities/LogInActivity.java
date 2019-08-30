package com.example.examenamst2.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.examenamst2.R;
import com.example.examenamst2.databinding.ActivityLogInBinding;

public class LogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityLogInBinding binding = DataBindingUtil
                .setContentView(this, R.layout.activity_log_in);



    }
}

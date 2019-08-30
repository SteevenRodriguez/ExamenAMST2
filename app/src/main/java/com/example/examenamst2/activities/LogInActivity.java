package com.example.examenamst2.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.example.examenamst2.R;
import com.example.examenamst2.databinding.ActivityLogInBinding;

public class LogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityLogInBinding binding = DataBindingUtil
                .setContentView(this, R.layout.activity_log_in);

        binding.buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ingreso= new Intent(LogInActivity.this, ResultsActivity.class);
                ingreso.putExtra("heroName",binding.nombreHeroe.getText().toString());
                startActivity(ingreso);
            }
        });




    }
    public void actualizarAutomaticamente(){
        try{
            final Handler handler = new Handler();
            final Runnable runnable = new Runnable() {
                @Override
                public void run() {
//                    identificarObjeto();
//                    getBatery();

                }
            };
            handler.postDelayed(runnable, 3000);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}

package com.example.final_project.ServiceActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.final_project.R;
import com.example.final_project.databinding.ActivityChoseBinding;

public class ChoseActivity extends AppCompatActivity {
    private ActivityChoseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChoseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonBatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChoseActivity.this,PatientRegister.class));
            }
        });

        binding.buttonDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChoseActivity.this,DoctorRegister.class));
            }
        });

    }
}
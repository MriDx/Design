package com.mridx.design.example;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mridx.design.element.CustomOTPField;
import com.mridx.design.example.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.getOTP.setOnClickListener(view -> {
            Log.d("kaku", "onCreate: " + binding.customOTP.getOTP());
        });

        binding.customOTP.setListener(otp -> {
            Log.d("kaku", "onCreate: otp completed " + otp);
            return null;
        });


    }
}
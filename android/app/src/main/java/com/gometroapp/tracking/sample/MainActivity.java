package com.gometroapp.tracking.sample;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.gometroapp.tracking.GoMetroTracking;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context context = getApplicationContext();

        GoMetroTracking.initialise(
                context,
                "test.tracking@gometroapp.com",
                "P@ssw0rd123!",
                "AGroup",
                "AnEntity"
        );
    }
}
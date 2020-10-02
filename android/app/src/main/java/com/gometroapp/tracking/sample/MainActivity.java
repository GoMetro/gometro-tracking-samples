package com.gometroapp.tracking.sample;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.gometroapp.tracking.GoMetroTracking;
import com.gometroapp.tracking.logging.LoggerFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context context = getApplicationContext();

        LoggerFactory.setLogger(new DebugOverrideLogger());

        GoMetroTracking.initialise(
                context,
                BuildConfig.GOMETRO_TRACKING_CLIENT_ID,
                BuildConfig.GOMETRO_TRACKING_CLIENT_SECRET,
                "ThisIsATestDevice"
        );
    }
}
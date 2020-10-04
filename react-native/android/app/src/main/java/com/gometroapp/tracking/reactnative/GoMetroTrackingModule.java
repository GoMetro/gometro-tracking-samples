package com.gometroapp.tracking.reactnative;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.gometroapp.tracking.GoMetroTracking;

public class GoMetroTrackingModule extends ReactContextBaseJavaModule {

    private static ReactApplicationContext reactContext;

    GoMetroTrackingModule(ReactApplicationContext context) {
        super(context);
        this.reactContext = context;
    }

    @ReactMethod
    public void initialise(String clientId, String clientSecret, String deviceId) {
        GoMetroTracking.initialise(getReactApplicationContext(), clientId, clientSecret, deviceId);
    }

    @NonNull
    @Override
    public String getName() {
        return "GoMetroTracking";
    }
}
package com.gometroapp.tracking.sample;

import android.util.Log;

import com.gometroapp.tracking.logging.AndroidLogger;
import com.gometroapp.tracking.logging.Logger;

public final class DebugOverrideLogger implements Logger {
    
    private Logger delegate = AndroidLogger.INSTANCE;

    public DebugOverrideLogger() {
    }

    @Override
    public void debug(String message) {
        Log.d("GoMetroTracking", message);
    }

    @Override
    public void debug(String format, Object... args) {
        String message = String.format(format, args);
        Log.d("GoMetroTracking", message);
    }

    @Override
    public void debug(String message, Throwable throwable) {
        Log.d("GoMetroTracking", message, throwable);
    }

    @Override
    public void info(String message) {
        this.delegate.info(message);
    }

    @Override
    public void info(String message, Throwable throwable) {
        this.delegate.info(message, throwable);
    }

    @Override
    public void warn(String message) {
        this.delegate.warn(message);
    }

    @Override
    public void warn(String message, Throwable throwable) {
        this.delegate.warn(message, throwable);
    }

    @Override
    public void warn(Throwable throwable) {
        this.delegate.warn(throwable);
    }

    @Override
    public void error(String message) {
        this.delegate.error(message);
    }

    @Override
    public void error(String message, Throwable throwable) {
        this.delegate.error(message, throwable);
    }

    @Override
    public void error(Throwable throwable) {
        this.delegate.error(throwable);
    }
}

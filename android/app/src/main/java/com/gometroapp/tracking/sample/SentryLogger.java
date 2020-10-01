package com.gometroapp.tracking.sample;

import android.util.Log;

import com.gometroapp.tracking.logging.AndroidLogger;
import com.gometroapp.tracking.logging.Logger;

import io.sentry.core.Sentry;
import io.sentry.core.SentryEvent;
import io.sentry.core.SentryLevel;
import io.sentry.core.protocol.Message;

public class SentryLogger implements Logger {
    
    private Logger delegate = AndroidLogger.INSTANCE;

    public SentryLogger() {
    }

    @Override
    public void debug(String message) {
        Log.d("GoMetroTracking", message);
        this.delegate.debug(message);
        sendMessage("DEBUG", message);
    }

    @Override
    public void debug(String format, Object... args) {
        String message = String.format(format, args);
        Log.d("GoMetroTracking", message);
        this.delegate.debug(format, args);
        sendMessage("DEBUG", message);
    }

    @Override
    public void debug(String message, Throwable throwable) {
        Log.d("GoMetroTracking", message, throwable);
        this.delegate.debug(message, throwable);
        sendMessage("DEBUG", message);
        Sentry.captureException(throwable);
    }

    @Override
    public void info(String message) {
        this.delegate.info(message);
        sendMessage("INFO", message);
    }

    @Override
    public void info(String message, Throwable throwable) {
        this.delegate.info(message, throwable);
        sendMessage("INFO", message);
        Sentry.captureException(throwable);
    }

    @Override
    public void warn(String message) {
        this.delegate.warn(message);
        sendMessage("WARNING", message);
    }

    @Override
    public void warn(String message, Throwable throwable) {
        this.delegate.warn(message, throwable);
        sendMessage("WARNING", message);
        Sentry.captureException(throwable);
    }

    @Override
    public void warn(Throwable throwable) {
        this.delegate.warn(throwable);
        Sentry.captureException(throwable);
    }

    @Override
    public void error(String message) {
        this.delegate.error(message);
        sendMessage("ERROR", message);
    }

    @Override
    public void error(String message, Throwable throwable) {
        this.delegate.error(message, throwable);
        sendMessage("ERROR", message);
        Sentry.captureException(throwable);
    }

    @Override
    public void error(Throwable throwable) {
        this.delegate.error(throwable);
        Sentry.captureException(throwable);
    }

    public void sendMessage(String level, String string) {
        try {
            Message message = new Message();
            message.setMessage(string);

            SentryEvent event = new SentryEvent();
            event.setLevel(SentryLevel.valueOf(level));
            event.setMessage(message);

            Sentry.captureEvent(event);
        } catch (Exception exception) {
            Sentry.captureException(exception);
        }
    }
}

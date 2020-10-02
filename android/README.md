## GoMetro Tracking SDK for Android Sample

### Build File

```groovy
...

repositories {
    ...

    maven { url "https://developer.gometroapp.com/nexus/repository/maven-public" }
}

dependencies {
    ...

    implementation "com.gometroapp.tracking:gometro-tracking-android:1.0.16"
    
    ...
}
```

### Permissions

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.gometroapp.tracking.sample">

    <uses-permission android:name="android.permission.FOREGROUND_SERVICE" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_BACKGROUND_LOCATION" />
    <uses-permission android:name="android.permission.INTERNET" />

    ...

</manifest>
```

### Activating the SDK

```java
package com.gometroapp.tracking.sample;

...

import com.gometroapp.tracking.GoMetroTracking;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        ...

        Context context = getApplicationContext();
        
        GoMetroTracking.initialise(
                context,
                "<clientId>",
                "<clientsSecret>",
                "<externalDeviceIdentifier>"
        );
    }
}
```

### Device Identifier

In order to access/correct the data at a later stage using the REST API, you will need to provide an
identifier for the device on which the SDK is installed. Whilst it is RECOMMENDED that this 
identifier NOT be based on Personally Identifiable Information, the SDK will still perform a one 
way hashing on this identifier to ensure the anonymity of the individual.   
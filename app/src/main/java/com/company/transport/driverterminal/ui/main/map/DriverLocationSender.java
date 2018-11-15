package com.company.transport.driverterminal.ui.main.map;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class DriverLocationSender extends Service {
    private static final String TAG = "DRIVERLOCATION";

    private LocationManager mLocationManager = null;
    private static final int LOCATION_INTERVAL = 1000;
    private static final float LOCATION_DISTANCE = 10f;

    private class LocationListener implements android.location.LocationListener {
        Location mLastLocation;

        public LocationListener(String provider) {
            Log.e(TAG, "LocationListener " + provider);
            mLastLocation = new Location(provider);
            sendLocation();
        }

        @Override
        public void onLocationChanged(Location location) {
            Log.e(TAG, "onLocationChanged: " + location);
            mLastLocation.set(location);
            sendLocation();
        }

        @Override
        public void onProviderDisabled(String provider) {
            Log.e(TAG, "onProviderDisabled: " + provider);
        }

        @Override
        public void onProviderEnabled(String provider) {
            Log.e(TAG, "onProviderEnabled: " + provider);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.e(TAG, "onStatusChanged: " + provider);
        }

        private void sendLocation() {
            double lat, lon;
            lat = mLastLocation.getLatitude();
            lon = mLastLocation.getLongitude();

            new Sender().execute(lat, lon);
        }
    }

    private class Sender extends AsyncTask<Double, Void, Void> {

        @Override
        protected Void doInBackground(Double... objects) {
            try {
                HttpClient httpclient = new DefaultHttpClient();
                URI uri;
                try {
                    uri = new URI("http", "f6d1285.ngrok.io", "/api/v1/parcels/0/", null, null);

                } catch (URISyntaxException e) {
                    Log.e(TAG, e.toString());
                    return null;
                }
                HttpPut httpput = new HttpPut(uri);
                httpput.addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImNjYjA4YzEzZjQwNmRlNzE0ZTRlN2UyNzU2NmYzMGEzNDU5MThjNWE1YTk4MzJlZjMwY2I2ZDE1N2RjYTc2YWU4OGY5YTE3MjhlOTNiMjJmIn0.eyJhdWQiOiIyIiwianRpIjoiY2NiMDhjMTNmNDA2ZGU3MTRlNGU3ZTI3NTY2ZjMwYTM0NTkxOGM1YTVhOTgzMmVmMzBjYjZkMTU3ZGNhNzZhZTg4ZjlhMTcyOGU5M2IyMmYiLCJpYXQiOjE1NDIyNTI4NTMsIm5iZiI6MTU0MjI1Mjg1MywiZXhwIjoxNTczNzg4ODUzLCJzdWIiOiI0Iiwic2NvcGVzIjpbXX0.QZZSh8mr943O5K6Z89fAqf3cv0z81inb7EhT3Rh6MdVCQUAoYEqq5Gh0Nbj9eOSwhc4RG1bY0PVBUomLkpufOFFpyGwmw8kOh9GiYU9cuXmiuxHw3RzntqZYYBDChFtM-PXeerrvPqOwqP4kr0pOLWI3MceWsc4yI8T9B5f7C9E-WSZG3Bf5LWBU_cs82Q0mNxspMyh9MXRmT9Ip0RJ5VOlsHJ5X8DuwsSFOLVica6-qDq1-H-LsZi80a5PPTaP1idJpP8K6D5U2m-jbMduoo-cWbHCfcRDqYJ8i5dsYWvP9X6nndCJ8Q7eROLK92ii6WtQAx9UMRx43NlFtmCEJ2eYOaVN93RzKVwJA-ODwikMgq1lSJdXVO2MfmXA4jn1-QZxL1pSgTObYUkOn-cFBKXq7bnCdXLfWiIWIzmB7IA2F1-XmmV3f_cCOTUSDfu-a9G0tGbZgltPBLjsNxo9bQy6oxjHi9LynIpyDaegKkazNoaY7_naWJYyrET_OQZZ_Lghm_VHb-DUXL_naj-f3u56H5LdN2YgjAVHXrSi59iFjsaDf0Hr5Xa6IJ29-S1s5MqdhYmRb_DYZFdciYTvy5qdkTZ-fIfWAR9tmLgYG67jMxeHNSVQrBkwZ6ZAL3llXjdZqJa9zrQqR0UL0sKW5yD9K204Syi8c8bWzAeKeSwE");
                httpput.addHeader("Accept", "application/json");
                httpput.addHeader("Content-Type", "application/x-www-form-urlencoded");
                try {
                    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(4);
                    nameValuePairs.add(new BasicNameValuePair("state", "Sent"));
                    nameValuePairs.add(new BasicNameValuePair("location_address", "SomeAddr"));
                    nameValuePairs.add(new BasicNameValuePair("location_latitude", Double.toString(objects[0])));
                    nameValuePairs.add(new BasicNameValuePair("location_longitude", Double.toString(objects[1])));
                    //todo also send user token
                    httpput.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    // Execute HTTP Post Request
                    try {
                        HttpResponse response = httpclient.execute(httpput);
                    } catch (Exception e) {
                        Log.e(TAG, "err in sending post request: " + e.toString());
                    }

                } catch (IOException e) {
                    Log.e(TAG, e.toString());
                }
            } catch (Exception e) {
                Log.d(TAG, "doInBackground: Exception", e);
            }
            return null;
        }

    }

    LocationListener[] mLocationListeners = new LocationListener[]{
            new LocationListener(LocationManager.GPS_PROVIDER),
            new LocationListener(LocationManager.NETWORK_PROVIDER)
    };

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand");
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        Log.e(TAG, "onCreate");
        initializeLocationManager();
        try {
            mLocationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,
                    mLocationListeners[1]);
        } catch (java.lang.SecurityException ex) {
            Log.i(TAG, "fail to request location update, ignore", ex);
        } catch (IllegalArgumentException ex) {
            Log.d(TAG, "network provider does not exist, " + ex.getMessage());
        }
        try {
            mLocationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,
                    mLocationListeners[0]);
        } catch (java.lang.SecurityException ex) {
            Log.i(TAG, "fail to request location update, ignore", ex);
        } catch (IllegalArgumentException ex) {
            Log.d(TAG, "gps provider does not exist " + ex.getMessage());
        }
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy");
        super.onDestroy();
        if (mLocationManager != null) {
            for (int i = 0; i < mLocationListeners.length; i++) {
                try {
                    mLocationManager.removeUpdates(mLocationListeners[i]);
                } catch (Exception ex) {
                    Log.i(TAG, "fail to remove location listners, ignore", ex);
                }
            }
        }
    }

    private void initializeLocationManager() {
        Log.e(TAG, "initializeLocationManager");
        if (mLocationManager == null) {
            mLocationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        }
    }
}



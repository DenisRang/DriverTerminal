package com.company.transport.driverterminal.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkManager {
    private NetworkInfo networkInfo;

    public NetworkManager(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) networkInfo = cm.getActiveNetworkInfo();
    }

    public boolean isConnected() {
        return networkInfo != null && networkInfo.isConnected();
    }
}

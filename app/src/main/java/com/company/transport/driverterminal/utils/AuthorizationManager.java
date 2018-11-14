package com.company.transport.driverterminal.utils;

import android.content.SharedPreferences;

public class AuthorizationManager {
    private final String PREF_LOGIN = "LOGIN";

    private final String PREF_PASSWORD = "PASSWORD";
    private final String PREF_AUTH_TOKEN = "PREF_AUTH_TOKEN";

    private SharedPreferences preferences;

    public AuthorizationManager(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    public void saveLoginData(String login, String password) {
        preferences.edit().putString(PREF_LOGIN, login).apply();
        preferences.edit().putString(PREF_PASSWORD, password).apply();
    }

    public String getLogin() {
        return preferences.getString(PREF_LOGIN, null);
    }

    public String getPassword() {
        return preferences.getString(PREF_PASSWORD, null);
    }

    public void saveResponseAuthToken(String token) {
        preferences.edit().putString(PREF_AUTH_TOKEN, token).apply();
    }

    public String getAuthToken() {
        return preferences.getString(PREF_AUTH_TOKEN, null);
    }

    public void deleteToken() {
        preferences.edit().remove(PREF_AUTH_TOKEN).apply();
    }

}

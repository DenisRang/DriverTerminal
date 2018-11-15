package com.company.transport.driverterminal.transportCompanyApi.requestAuthorization;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthorizationRequest {

    @SerializedName("grant_type")
    public String grantType;
    @SerializedName("client_id")
    @Expose
    public String clientId;
    @SerializedName("client_secret")
    @Expose
    public String clientSecret;
    @SerializedName("username")
    @Expose
    public String username;
    @SerializedName("password")
    @Expose
    public String password;

    public AuthorizationRequest withGrantType(String grantType) {
        this.grantType = grantType;
        return this;
    }

    public AuthorizationRequest withClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    public AuthorizationRequest withClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
        return this;
    }

    public AuthorizationRequest withUsername(String username) {
        this.username = username;
        return this;
    }

    public AuthorizationRequest withPassword(String password) {
        this.password = password;
        return this;
    }

}
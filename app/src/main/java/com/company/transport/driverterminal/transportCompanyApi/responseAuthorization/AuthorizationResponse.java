package com.company.transport.driverterminal.transportCompanyApi.responseAuthorization;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthorizationResponse {

    @SerializedName("token_type")
    @Expose
    public String tokenType;
    @SerializedName("expires_in")
    @Expose
    public Integer expiresIn;
    @SerializedName("access_token")
    @Expose
    public String accessToken;
    @SerializedName("refresh_token")
    @Expose
    public String refreshToken;
    @SerializedName("error")
    @Expose
    public String error;
    @SerializedName("message")
    @Expose
    public String message;

    public String getTokenType() {
        return tokenType;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }
}
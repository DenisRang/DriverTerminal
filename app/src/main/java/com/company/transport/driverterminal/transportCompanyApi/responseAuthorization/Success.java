package com.company.transport.driverterminal.transportCompanyApi.responseAuthorization;

public class Success {
    private String token;

    public Success(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}

package com.company.transport.driverterminal.transportCompanyApi.responseAuthorization;

public class AuthorizationResponseParams {
    private Success success;

    public AuthorizationResponseParams(Success success) {
        this.success = success;
    }

    public Success getSuccess() {
        return success;
    }
}

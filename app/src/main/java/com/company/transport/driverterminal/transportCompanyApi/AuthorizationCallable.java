package com.company.transport.driverterminal.transportCompanyApi;

import com.company.transport.driverterminal.transportCompanyApi.responseAuthorization.AuthorizationResponseParams;
import com.company.transport.driverterminal.transportCompanyApi.responseAuthorization.Success;

import java.util.concurrent.Callable;

public class AuthorizationCallable implements Callable<AuthorizationResponseParams> {
    String login;
    String password;

    public AuthorizationCallable(String login, String password) {
        this.login = login;
        this.password = password;
    }

    AuthorizationResponseParams authorize() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (login.equals("demo") && password.equals("1")) return new AuthorizationResponseParams(new Success("good"));
        else return null;
    }


    @Override
    public AuthorizationResponseParams call() throws Exception {
        return authorize();
    }
}

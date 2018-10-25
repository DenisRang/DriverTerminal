package com.company.transport.driverterminal.ui.authorization;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.company.transport.driverterminal.R;
import com.company.transport.driverterminal.ui.main.view.MainActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import butterknife.OnTouch;
import dagger.android.AndroidInjection;

public class AuthorizationActivity extends AppCompatActivity implements AuthorizationContract.View {

    private static final String LOGIN_ERROR = "loginError";
    private static final String LOGIN_ERROR_STR = "loginErrorStr";
    private static final String PASSWORD_ERROR = "passwordError";
    private static final String PASSWORD_ERROR_STR = "passwordErrorStr";

    @BindView(R.id.edit_login_field)
    EditText login;
    @BindView(R.id.edit_password)
    EditText password;
    @BindView(R.id.layout_login_wrapper)
    TextInputLayout loginWrapper;
    @BindView(R.id.layout_password_wrapper)
    TextInputLayout passwordWrapper;
    @Inject
    AuthorizationContract.Presenter presenter;
    private AlertDialog loadingDialog;
    private View dialogView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);

        ButterKnife.bind(this);
        if (savedInstanceState != null) {
            if (savedInstanceState.get(LOGIN_ERROR) != null) {
                loginWrapper.setHint(savedInstanceState.getString(LOGIN_ERROR_STR));
            }
            if (savedInstanceState.get(PASSWORD_ERROR) != null) {
                passwordWrapper.setHint(savedInstanceState.getString(PASSWORD_ERROR_STR));
            }
        }

        initLoadingDialog();

        AndroidInjection.inject(this);
        presenter.attachView(this, true);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (loginWrapper.isErrorEnabled()) {
            outState.putBoolean(LOGIN_ERROR, true);
            if (loginWrapper != null) {
                outState.putString(LOGIN_ERROR_STR, loginWrapper.getHint().toString());
            }
        }
        if (passwordWrapper.isErrorEnabled()) {
            outState.putBoolean(PASSWORD_ERROR, true);
            if (passwordWrapper != null) {
                outState.putString(PASSWORD_ERROR_STR, passwordWrapper.getHint().toString());
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
        presenter.stop();
    }

    @OnClick(R.id.button_login)
    public void login() {
        loginWrapper.setError(null);
        loginWrapper.setErrorEnabled(true);
        loginWrapper.setHint(getString(R.string.msg_login));
        passwordWrapper.setError(null);
        passwordWrapper.setErrorEnabled(true);
        passwordWrapper.setHint(getString(R.string.msg_password));
        presenter.login();
    }

    @Override
    public String getLogin() {
        return login.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return password.getText().toString().trim();
    }

    @Override
    public void resetPassword() {
        password.setText("");
    }

    @Override
    public void showLoadingDialog() {
        if (loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
        loadingDialog.show();
    }

    @Override
    public void dismissLoadingDialog() {
        loadingDialog.dismiss();
    }

    @OnTouch(R.id.layout_container)
    public boolean hideKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (inputMethodManager != null)
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return true;
    }

    public void showPoorConnection() {
        Toast.makeText(this, getString(R.string.msg_poor_connection), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showNoConnection() {
        Toast.makeText(this, getString(R.string.msg_no_internet), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoginError() {
        passwordWrapper.setError(null);
        passwordWrapper.setErrorEnabled(true);
        passwordWrapper.setHint(getString(R.string.msg_password));
        loginWrapper.setHint(getString(R.string.msg_no_login));
        loginWrapper.setError(" ");
    }

    @Override
    public void showWrongEmail() {
        passwordWrapper.setError(null);
        passwordWrapper.setErrorEnabled(true);
        passwordWrapper.setHint(getString(R.string.msg_password));
        loginWrapper.setHint(getString(R.string.msg_wrong_data));
        loginWrapper.setError(" ");
    }

    @Override
    public void showPasswordError() {
        loginWrapper.setError(null);
        loginWrapper.setErrorEnabled(true);
        loginWrapper.setHint(getString(R.string.msg_login));
        passwordWrapper.setHint(getString(R.string.msg_no_password));
        passwordWrapper.setError(" ");
    }

    @Override
    public void navigateToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void initLoadingDialog() {
        dialogView = getLayoutInflater().inflate(R.layout.item_loading_dialog, null);
        loadingDialog = new AlertDialog.Builder(this).setView(dialogView).create();
        loadingDialog.setCanceledOnTouchOutside(false);
    }

    @OnEditorAction(R.id.edit_password)
    protected boolean onEditPasswordFinished(int actionId) {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            presenter.login();
        }
        return false;
    }

}

package it.crispybacon.mundial1x2.sections.authentication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import it.crispybacon.mundial1x2.Activity1x2;
import it.crispybacon.mundial1x2.R;
import it.crispybacon.mundial1x2.core.apimodels.User;
import it.crispybacon.mundial1x2.core.authentication.Authentication;
import it.crispybacon.mundial1x2.sections.home.HomeActivity;
import it.crispybacon.mundial1x2.ui.text.TextFieldsUtils;

public class LoginActivity extends Activity1x2
        implements View.OnClickListener {

    public static Intent getStartIntent(final Context context) {
        Intent startIntent = new Intent(context, LoginActivity.class);
        return startIntent;
    }

    private AppCompatEditText mEditEmail;
    private AppCompatEditText mEditPassword;

    private Consumer<User> mSuccessConsumer = new Consumer<User>() {
        @Override
        public void accept(User user) throws Exception {
            hideLoadingDialog();
            startActivity(HomeActivity.getStartIntent(LoginActivity.this));
            finish();
        }
    };

    private Consumer<Throwable> mErrorConsumer = new Consumer<Throwable>() {
        @Override
        public void accept(Throwable throwable) throws Exception {
            hideLoadingDialog();
            showSnackBar(findViewById(R.id.view_root), throwable.getMessage());
        }
    };

    private Disposable mAuthDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEditEmail = findViewById(R.id.edit_email);
        mEditPassword = findViewById(R.id.edit_password);

        TextFieldsUtils.disableCopyPaste(mEditPassword);

        findViewById(R.id.button_login)
                .setOnClickListener(this);
        findViewById(R.id.button_register)
                .setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mAuthDisposable != null && !mAuthDisposable.isDisposed()) {
            mAuthDisposable.dispose();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_login:
                login();
                break;
            case R.id.button_register:
                startActivity(RegisterActivity.getStartIntent(this));
                break;
        }
    }

    private void login() {
        if (validateFields()) {
            showLoadingDialog();
            mAuthDisposable = Authentication.get()
                    .login(
                            mEditEmail.getText().toString(),
                            mEditPassword.getText().toString()
                    )
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(mSuccessConsumer, mErrorConsumer);
        }
    }

    private boolean validateFields() {
        boolean isValid = true;
        if (mEditEmail.getText().toString().isEmpty()) {
            mEditEmail.setError(getString(R.string.auth_email_empty_error));
            isValid = false;
        } else {
            mEditEmail.setError(null);
        }
        if (mEditPassword.getText().toString().isEmpty()) {
            mEditPassword.setError(getString(R.string.auth_password_empty_error));
            isValid = false;
        } else {
            mEditPassword.setError(null);
        }

        return isValid;
    }
}

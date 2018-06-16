package it.crispybacon.mundial1x2.sections.authentication;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.view.ActionMode;
import android.support.v7.widget.AppCompatEditText;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import it.crispybacon.mundial1x2.core.apimodels.User;
import it.crispybacon.mundial1x2.Activity1x2;
import it.crispybacon.mundial1x2.R;
import it.crispybacon.mundial1x2.core.authentication.Authentication;
import it.crispybacon.mundial1x2.sections.home.HomeActivity;

public class AuthenticationActivity extends Activity1x2
        implements View.OnClickListener {

    public static Intent getStartIntent(final Context context) {
        Intent startIntent = new Intent(context, AuthenticationActivity.class);
        return startIntent;
    }

    private AppCompatEditText mEditEmail;
    private AppCompatEditText mEditPassword;
    private AppCompatEditText mEditRepeatPassword;

    private Consumer<User> mSuccessConsumer = new Consumer<User>() {
        @Override
        public void accept(User user) throws Exception {
            hideLoadingDialog();
            startActivity(HomeActivity.getStartIntent(AuthenticationActivity.this));
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
        setContentView(R.layout.activity_authentication);

        mEditEmail = findViewById(R.id.edit_email);
        mEditPassword = findViewById(R.id.edit_password);
        mEditRepeatPassword = findViewById(R.id.edit_repeat_password);

        disableCopyPaste(mEditPassword);
        disableCopyPaste(mEditPassword);

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
                register();
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

    private void register() {
        if (validateFields()) {
            showLoadingDialog();
            mAuthDisposable = Authentication.get()
                    .register(
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
        if (mEditRepeatPassword.getText().toString().isEmpty()) {
            mEditRepeatPassword.setError(getString(R.string.auth_password_repeat_empty_error));
            isValid = false;
        } else if (!mEditRepeatPassword.getText().toString().equals(mEditPassword.getText().toString())) {
            mEditRepeatPassword.setError(getString(R.string.auth_password_repeat_mismatch_error));
            isValid = false;
        } else {
            mEditRepeatPassword.setError(null);
        }

        return isValid;
    }

    private void disableCopyPaste(final AppCompatEditText editText) {
        editText.setCustomSelectionActionModeCallback(new ActionMode.Callback() {

            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            public boolean onActionItemClicked(ActionMode actionMode, MenuItem item) {
                return false;
            }

            public void onDestroyActionMode(ActionMode actionMode) {
            }
        });

        editText.setLongClickable(false);
        editText.setTextIsSelectable(false);
    }
}

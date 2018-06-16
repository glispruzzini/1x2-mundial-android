package it.crispybacon.mundial1x2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Observable;

import io.reactivex.disposables.Disposable;
import it.crispybacon.mundial1x2.core.authentication.Authentication;

public class SplashActivity extends AppCompatActivity {

    private Disposable mUserDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        checkAuthentication();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mUserDisposable != null && !mUserDisposable.isDisposed()){
            mUserDisposable.dispose();
        }
    }

    private void checkAuthentication() {
        if (Authentication.get().getFirebaseUser() != null) {
            startActivity(HomeActivity.getStartIntent(this));
        } else {
            startActivity(AuthenticationActivity.getStartIntent(this));
        }
        finish();
    }
}

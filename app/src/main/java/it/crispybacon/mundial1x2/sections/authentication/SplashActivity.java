package it.crispybacon.mundial1x2.sections.authentication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import it.crispybacon.mundial1x2.R;
import it.crispybacon.mundial1x2.core.authentication.Authentication;
import it.crispybacon.mundial1x2.sections.home.HomeActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        checkAuthentication();
    }

    private void checkAuthentication() {
        if (Authentication.get().getUser() != null) {
            startActivity(HomeActivity.getStartIntent(this));
        } else {
            startActivity(AuthenticationActivity.getStartIntent(this));
        }
        finish();
    }
}

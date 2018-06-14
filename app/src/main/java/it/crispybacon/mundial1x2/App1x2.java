package it.crispybacon.mundial1x2;

import android.app.Application;

import it.crispybacon.mundial1x2.core.Core;

/**
 * Created by Luca Rossi on 14/06/2018.
 */
public class App1x2 extends Application {
    private static App1x2 sInstance;

    public static App1x2 get() {
        return sInstance == null ? sInstance = new App1x2() : sInstance;
    }

    public App1x2(){}

    @Override
    public void onCreate() {
        super.onCreate();

        Core.get().setup(this);
    }
}

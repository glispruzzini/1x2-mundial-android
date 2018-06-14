package it.crispybacon.mundial1x2.core;

import android.annotation.SuppressLint;
import android.content.Context;

/**
 * Created by Jameido on 14/06/2018.
 */

public class Core {

    private static final String BASE_URL = "https://gli.spruzzini.it";

    private static final String TAG = "Core";

    private static Core sInstance;

    public static Core get() {
        if (sInstance == null) {
            sInstance = new Core();
        }
        return sInstance;
    }

    private Core() {
    }

    private AuthCredentialsReader mAuthCredentialsReader = new AuthCredentialsReader() {
        @Override
        public String getToken() {
            return null;
        }

        @Override
        public String getId() {
            return null;
        }

        @Override
        public String getUserName() {
            return null;
        }

        @Override
        public String getPassword() {
            return null;
        }
    };
    private AuthTokenWriter mAuthTokenWriter = new AuthTokenWriter() {
        @Override
        public void writeAuthToken(String token) {

        }
    };

    public String getBaseUrl() {
        return BASE_URL;
    }

    public AuthCredentialsReader getAuthCredentialsReader() {
        return mAuthCredentialsReader;
    }

    public AuthTokenWriter getAuthTokenWriter() {
        return mAuthTokenWriter;
    }

    public void setup(final Context context) {
        final Context vContext = context.getApplicationContext();

        mAuthCredentialsReader = new AuthCredentialsReader() {
            @Override
            public String getToken() {
                return null;
            }

            @Override
            public String getId() {
                return null;
            }

            @Override
            public String getUserName() {
                return null;
            }

            @Override
            public String getPassword() {
                return null;
            }
        };

        mAuthTokenWriter = new AuthTokenWriter() {
            @Override
            public void writeAuthToken(String token) {

            }
        };
    }

    public interface AuthCredentialsReader {
        String getToken();

        String getId();

        String getUserName();

        String getPassword();
    }

    public interface AuthTokenWriter {
        void writeAuthToken(String token);
    }
}

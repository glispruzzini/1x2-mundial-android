package it.crispybacon.mundial1x2.core;

import android.content.Context;

import com.google.firebase.FirebaseApp;
import com.squareup.moshi.Moshi;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

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

    private FirebaseApp mFirebaseApp;

    private OkHttpClient mAuthHttpClient = new OkHttpClient();
    private OkHttpClient mNoAuthHttpClient = new OkHttpClient();

    private Moshi mMoshi = new Moshi
            .Builder()
            .build();

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

    public void setup(final Context context, final FirebaseApp firebaseApp) {
        final Context vContext = context.getApplicationContext();

        mFirebaseApp = firebaseApp;

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

        initOkHttpClient(context);
    }

    private void initOkHttpClient(final Context context) {

        OkHttpClient.Builder vBuilder = new OkHttpClient.Builder()
                .connectTimeout(60_000, TimeUnit.MILLISECONDS)
                .readTimeout(60_000, TimeUnit.MILLISECONDS)
                .writeTimeout(60_000, TimeUnit.MILLISECONDS);

        mNoAuthHttpClient = vBuilder
                .build();

        mAuthHttpClient = vBuilder
                .addInterceptor(new TokenInterceptor())
                .build();
    }

    public String getBaseUrl() {
        return BASE_URL;
    }

    public FirebaseApp getFirebaseApp() {
        return mFirebaseApp;
    }

    public OkHttpClient getAuthHttpClient() {
        return mAuthHttpClient;
    }

    public OkHttpClient getNoAuthHttpClient() {
        return mNoAuthHttpClient;
    }

    public Moshi getMoshi() {
        return mMoshi;
    }

    public AuthCredentialsReader getAuthCredentialsReader() {
        return mAuthCredentialsReader;
    }

    public AuthTokenWriter getAuthTokenWriter() {
        return mAuthTokenWriter;
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

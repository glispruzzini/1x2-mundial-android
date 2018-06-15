package it.crispybacon.mundial1x2.core.authentication;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;

import it.crispybacon.mundial1x2.core.Core;

/**
 * Created by Jameido on 14/06/2018.
 */
public class Authentication {

    private static Authentication sInstance;

    public static Authentication get() {
        return sInstance == null ? sInstance = new Authentication() : sInstance;
    }

    private Authentication() {

    }

    private FirebaseAuth getFirebaseAuth() {
        return FirebaseAuth.getInstance(Core.get().getFirebaseApp());
    }

    public Task<AuthResult> register(final String email, final String passowrd) {
        return getFirebaseAuth()
                .createUserWithEmailAndPassword(email, passowrd);
    }

    public Task<AuthResult> login(final String email, final String passowrd) {
        return getFirebaseAuth()
                .signInWithEmailAndPassword(email, passowrd);
    }

    public FirebaseUser getUser() {
        return getFirebaseAuth()
                .getCurrentUser();
    }

    public Task<GetTokenResult> getToken() {
        FirebaseUser vFirebaseUser = getUser();
        if (vFirebaseUser == null) {
            return Tasks.forException(new Exception("Need a logged user to get a token, please login or register"));
        } else {
            return vFirebaseUser.getIdToken(true);
        }
    }
}

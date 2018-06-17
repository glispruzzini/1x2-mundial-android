package it.crispybacon.mundial1x2.core.authentication;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import it.crispybacon.mundial1x2.core.Core;
import it.crispybacon.mundial1x2.core.apimodels.User;
import it.crispybacon.mundial1x2.core.user.UserApiService;

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

    public Observable<User> register(final String email, final String passowrd) {
        return registerFirebase(email, passowrd)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .flatMap(new Function<AuthResult, ObservableSource<User>>() {
                    @Override
                    public ObservableSource<User> apply(AuthResult authResult) throws Exception {
                        return createUser();
                    }
                });
    }

    public Observable<AuthResult> registerFirebase(final String email, final String passowrd) {
        return Observable.create(new ObservableOnSubscribe<AuthResult>() {
            @Override
            public void subscribe(final ObservableEmitter<AuthResult> emitter) throws Exception {
                getFirebaseAuth()
                        .createUserWithEmailAndPassword(email, passowrd)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                emitter.onNext(authResult);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                emitter.onError(e);
                            }
                        });
            }
        });
    }

    public Observable<User> login(final String email, final String passowrd) {
        return loginFirebase(email, passowrd)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .flatMap(new Function<AuthResult, ObservableSource<User>>() {
                    @Override
                    public ObservableSource<User> apply(AuthResult authResult) throws Exception {
                        return getUser();
                    }
                });
    }

    public Observable<AuthResult> loginFirebase(final String email, final String passowrd) {

        return Observable.create(new ObservableOnSubscribe<AuthResult>() {
            @Override
            public void subscribe(final ObservableEmitter<AuthResult> emitter) throws Exception {
                getFirebaseAuth()
                        .signInWithEmailAndPassword(email, passowrd)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                emitter.onNext(authResult);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                emitter.onError(e);
                            }
                        });
            }
        });
    }

    public FirebaseUser getFirebaseUser() {
        return getFirebaseAuth().getCurrentUser();
    }

    public Observable<String> getToken() {
        final FirebaseUser vFirebaseUser = getFirebaseUser();
        if (vFirebaseUser == null) {
            return Observable.error(new Exception("Need a logged user to get a token, please login or register"));
        }
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(final ObservableEmitter<String> emitter) throws Exception {
                vFirebaseUser.getIdToken(true)
                        .addOnSuccessListener(new OnSuccessListener<GetTokenResult>() {
                            @Override
                            public void onSuccess(GetTokenResult getTokenResult) {
                                emitter.onNext(getTokenResult.getToken());
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                emitter.onError(e);
                            }
                        });
            }
        });
    }

    public Observable<User> createUser() {
        return getToken()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .flatMap(new Function<String, ObservableSource<User>>() {
                    @Override
                    public ObservableSource<User> apply(String token) throws Exception {
                        return UserApiService.get().createUser(token);
                    }
                });
    }

    public Observable<User> getUser() {
        return UserApiService.get().getUser();
    }
}

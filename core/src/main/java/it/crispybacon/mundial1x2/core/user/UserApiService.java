package it.crispybacon.mundial1x2.core.user;

import java.util.List;

import io.reactivex.Observable;
import it.crispybacon.mundial1x2.core.ApiService;
import it.crispybacon.mundial1x2.core.Core;
import it.crispybacon.mundial1x2.core.apimodels.TokenId;
import it.crispybacon.mundial1x2.core.apimodels.User;

/**
 * Created by Jameido on 15/06/2018.
 */
public class UserApiService extends ApiService<UserApi> {
    private static UserApiService sInstance;

    public static UserApiService get() {
        return sInstance == null ? sInstance = new UserApiService() : sInstance;
    }

    private UserApiService() {
        super(Core.get().getBaseUrl());
    }

    public UserApi getService() {
        return super.getService(UserApi.class);
    }

    public Observable<User> createUser(String token) {
        return getService()
                .createUser(new TokenId(token));
    }

    public Observable<User> getUser() {
        return getService()
                .getUser();
    }

    public Observable<List<User>> getUsers() {
        return getService()
                .getUsers();
    }
}

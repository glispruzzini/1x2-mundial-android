package it.crispybacon.mundial1x2.core.authentication;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import it.crispybacon.mundial1x2.core.ApiService;
import it.crispybacon.mundial1x2.core.Core;
import it.crispybacon.mundial1x2.core.apimodels.Match;
import it.crispybacon.mundial1x2.core.apimodels.TokenId;
import it.crispybacon.mundial1x2.core.apimodels.User;
import okhttp3.OkHttpClient;

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

    @Override
    protected OkHttpClient getHttpClient() {
        return Core.get().getNoAuthHttpClient();
    }

    public Observable<User> getUser(String token) {
        return getService()
                .getUser(new TokenId(token));
    }
}

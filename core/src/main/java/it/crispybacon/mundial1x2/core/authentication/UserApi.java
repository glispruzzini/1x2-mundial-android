package it.crispybacon.mundial1x2.core.authentication;

import io.reactivex.Observable;
import it.crispybacon.mundial1x2.core.apimodels.TokenId;
import it.crispybacon.mundial1x2.core.apimodels.User;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Jameido on 15/06/2018.
 */
public interface UserApi {

    String URL_CREATE_USER = "public/user";
    String URL_GET_USER = "public/user/me";

    @POST(URL_CREATE_USER)
    Observable<User> createUser(@Body TokenId tokenId);

    @POST(URL_GET_USER)
    Observable<User> getUser();
}

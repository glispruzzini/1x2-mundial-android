package it.crispybacon.mundial1x2.core.authentication;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import it.crispybacon.mundial1x2.core.apimodels.Match;
import it.crispybacon.mundial1x2.core.apimodels.TokenId;
import it.crispybacon.mundial1x2.core.apimodels.User;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Jameido on 15/06/2018.
 */
public interface UserApi {

    String URL_USER = "public/user";

    @POST(URL_USER)
    Observable<User> getUser(@Body TokenId tokenId);
}

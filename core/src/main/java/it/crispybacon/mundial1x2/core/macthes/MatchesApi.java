package it.crispybacon.mundial1x2.core.macthes;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

/**
 * Created by Jameido on 15/06/2018.
 */
public interface MatchesApi {

    final static String URL_GET_MATCHES = "security/matches";

    @GET(URL_GET_MATCHES)
    Observable<ResponseBody> getMatches();

}

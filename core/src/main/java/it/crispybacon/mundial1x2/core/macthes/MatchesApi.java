package it.crispybacon.mundial1x2.core.macthes;

import java.util.List;

import io.reactivex.Observable;
import it.crispybacon.mundial1x2.core.apimodels.Match;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

/**
 * Created by Jameido on 15/06/2018.
 */
public interface MatchesApi {

    final static String URL_GET_MATCHES = "security/matches";

    @GET(URL_GET_MATCHES)
    Observable<List<Match>> getMatches();

}

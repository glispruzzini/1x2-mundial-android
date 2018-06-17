package it.crispybacon.mundial1x2.core.bets;

import java.util.List;

import io.reactivex.Observable;
import it.crispybacon.mundial1x2.core.apimodels.Bet;
import it.crispybacon.mundial1x2.core.apimodels.SimpleResponse;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Jameido on 15/06/2018.
 */
public interface BetsApi {

    String URL_PLACE_BET = "security/bet";
    String URL_MY_BETS = "security/bet/me";

    @POST(URL_PLACE_BET)
    Observable<SimpleResponse> placeBet(@Body Bet betResult);

    @GET(URL_MY_BETS)
    Observable<List<Bet>> myBets();

}

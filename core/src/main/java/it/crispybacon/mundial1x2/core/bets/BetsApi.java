package it.crispybacon.mundial1x2.core.bets;

import java.util.List;

import io.reactivex.Observable;
import it.crispybacon.mundial1x2.core.apimodels.Bet;
import it.crispybacon.mundial1x2.core.apimodels.Match;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Jameido on 15/06/2018.
 */
public interface BetsApi {

    String URL_POST_BET = "security/bet";

    @POST(URL_POST_BET)
    Observable<ResponseBody> postBet(@Body Bet betResult);

}

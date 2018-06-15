package it.crispybacon.mundial1x2.core.bets;

import io.reactivex.Observable;
import it.crispybacon.mundial1x2.core.apimodels.Bet;
import it.crispybacon.mundial1x2.core.apimodels.SimpleResponse;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Jameido on 15/06/2018.
 */
public interface BetsApi {

    String URL_PLACE_BET = "security/bet";

    @POST(URL_PLACE_BET)
    Observable<SimpleResponse> placeBet(@Body Bet betResult);

}

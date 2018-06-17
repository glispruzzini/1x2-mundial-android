package it.crispybacon.mundial1x2.core.bets;

import java.util.List;

import io.reactivex.Observable;
import it.crispybacon.mundial1x2.core.ApiService;
import it.crispybacon.mundial1x2.core.Core;
import it.crispybacon.mundial1x2.core.apimodels.Bet;
import it.crispybacon.mundial1x2.core.apimodels.Match;
import it.crispybacon.mundial1x2.core.apimodels.SimpleResponse;

/**
 * Created by Jameido on 15/06/2018.
 */
public class BetsApiService extends ApiService<BetsApi> {
    private static BetsApiService sInstance;

    public static BetsApiService get() {
        return sInstance == null ? sInstance = new BetsApiService() : sInstance;
    }

    private BetsApiService() {
        super(Core.get().getBaseUrl());
    }

    public BetsApi getService() {
        return super.getService(BetsApi.class);
    }

    public Observable<SimpleResponse> placeBet(final Match match, final Bet.BetResult betResult) {
        return getService()
                .placeBet(new Bet(match, betResult));
    }

    public Observable<List<Bet>> myBets() {
        return getService()
                .myBets();
    }
}

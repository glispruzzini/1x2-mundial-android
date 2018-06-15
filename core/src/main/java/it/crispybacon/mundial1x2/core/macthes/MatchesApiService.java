package it.crispybacon.mundial1x2.core.macthes;

import java.util.List;

import io.reactivex.Observable;
import it.crispybacon.mundial1x2.core.ApiService;
import it.crispybacon.mundial1x2.core.Core;
import it.crispybacon.mundial1x2.core.apimodels.Match;
import okhttp3.ResponseBody;

/**
 * Created by Jameido on 15/06/2018.
 */
public class MatchesApiService extends ApiService<MatchesApi> {
    private static MatchesApiService sInstance;

    public static MatchesApiService get() {
        return sInstance == null ? sInstance = new MatchesApiService() : sInstance;
    }

    private MatchesApiService() {
        super(Core.get().getBaseUrl());
    }

    public MatchesApi getService() {
        return super.getService(MatchesApi.class);
    }

    public Observable<List<Match>> getMatches() {
        return getService()
                .getMatches();
    }
}

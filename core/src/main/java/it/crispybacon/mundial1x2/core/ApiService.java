package it.crispybacon.mundial1x2.core;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Created by Jameido on 15/06/2018.
 */
public class ApiService<S> {

    private String mBaseUrl;

    private Retrofit mRetrofit;

    public ApiService(String aBaseUrl) {
        mBaseUrl = aBaseUrl;
    }

    protected S getService(Class<S> serviceClass) {
        return getRetrofit().create(serviceClass);
    }

    private String getBaseUrl() {
        return mBaseUrl;
    }

    protected OkHttpClient getHttpClient() {
        return Core.get().getAuthHttpClient();
    }

    private Retrofit getRetrofit() {
        if (null == mRetrofit || mRetrofit.baseUrl().toString().equals(mBaseUrl)) {
            initRetrofit();
        }
        return mRetrofit;
    }

    private void initRetrofit() {
        mRetrofit = new Retrofit.Builder()
                .client(getHttpClient())
                .baseUrl(getBaseUrl())
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public void setBaseUrl(String baseUrl) {
        mBaseUrl = baseUrl;
    }
}

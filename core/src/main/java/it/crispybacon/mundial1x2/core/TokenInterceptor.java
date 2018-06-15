package it.crispybacon.mundial1x2.core;

import com.google.android.gms.tasks.Tasks;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import it.crispybacon.mundial1x2.core.authentication.Authentication;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Jameido on 16/06/2018.
 */

public class TokenInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        //Build new request
        Request.Builder builder = request.newBuilder();
        try {
            setAuthHeader(builder, Tasks.await(Authentication.get().getToken()).getToken()); //write current token to request
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        request = builder.build(); //overwrite old request
        return chain.proceed(request);
    }

    private void setAuthHeader(Request.Builder builder, String token) {
        if (token != null) //Add Auth token to each request if authorized
            builder.header("authorization", token);
    }
}

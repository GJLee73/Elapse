package com.chainsmokers.gjlee.elapse.network;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by GILJAE on 2018-02-19.
 */

public class APIClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {

        //OkHttp Client Builder 객체 생성.
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        // Interceptor 추가. Request, Response 처리 가능. 주로 Request Header 설정에 쓰임.
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();

                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder()
                        .header("DJ", "The Chainsmokers");

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        // OkHttp Client 객체 생성.
        OkHttpClient client = httpClient.build();

        // Retrofit 객체 생성. 특정 url로 네트워크 연결 설정.
        retrofit = new Retrofit.Builder()
                .baseUrl("http://35.190.183.146")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        // Request Header 값을 NULL 값으로 설정.
        /*retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.101:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();*/

        return retrofit;
    }
}

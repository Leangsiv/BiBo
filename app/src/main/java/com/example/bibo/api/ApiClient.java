package com.example.bibo.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    //public static final String BASE_URL = "http://127.0.0.1:8888/loginapp/";
    public static final String BASE_URL = "http://192.168.43.208:8888/bibo/";
    public static Retrofit retrofit = null;

    public static Retrofit getRetrofitInstance(){
        if(retrofit == null){
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}

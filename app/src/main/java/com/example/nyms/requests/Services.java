package com.example.nyms.requests;


import com.example.nyms.utils.Credentials;
import com.example.nyms.utils.WordApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//SINGLETON PATTERN FOR RETROFIT API
public class Services {

    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
                    .baseUrl(Credentials.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static WordApi wordApi = retrofit.create(WordApi.class);

    public static WordApi getWordApi() { return wordApi; }

}

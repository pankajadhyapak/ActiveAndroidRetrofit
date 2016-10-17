package com.example.acadgilduser.myapplication.utils;

import android.content.Context;
import android.util.Log;

import com.google.gson.ExclusionStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by acadgilduser on 17/10/16.
 */

public class RetroFit {
    private static final String TAG = "RetroFit";
    private static final String API_BASE_URL = "http://lar53.dev/api/v1/";
    private Context context;
    private Retrofit retrofit = null;

    public RetroFit(Context context) {
        this.context = context;
    }

    public Retrofit getInstance() {
        // For logging Requests and Response ( only for Development )
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.addInterceptor(logging);

        this.retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .client(httpClient.build())
                .addConverterFactory(
                        GsonConverterFactory.create(
                                new GsonBuilder()
                                        .excludeFieldsWithoutExposeAnnotation()
                                        .create()
                        ))
                .build();

        return this.retrofit;
    }
}

package com.example.acadgilduser.myapplication.api;

import com.example.acadgilduser.myapplication.model.User;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;



public interface UserApi {
    @GET("users")
    Call<ArrayList<User>> getAllUsers();
}

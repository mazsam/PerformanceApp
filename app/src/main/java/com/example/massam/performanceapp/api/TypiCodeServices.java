package com.example.massam.performanceapp.api;

import com.example.massam.performanceapp.model.Model;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by massam on 03/01/18.
 */

public interface TypiCodeServices {
    @GET("/photos")
    Call<ArrayList<Model>> data();
}

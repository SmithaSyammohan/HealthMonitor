package com.example.healthmonitor.api;

import com.example.healthmonitor.data.CountriesResponse;
import com.example.healthmonitor.data.AllCountriesResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CoronaService {

    @GET("countries/?sort=country")
    Call<List<CountriesResponse>> getCountries();


    @GET("countries/{country}")
    Call<CountriesResponse> getCountryInfo(

            @Path("country") String country
    );

    @GET("all")
    Call<AllCountriesResponse> getAllCountries();
}

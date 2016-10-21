package com.sdh.interviewvideotest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by sdh on 2016/10/20.
 */

public interface PhoneService {

    @GET("/apistore/mobilenumber/mobilenumber")
    Call<PhoneResult> getResult(@Header("apikey") String apikey, @Query("phone") String phone);
    @GET("/users/{user}")
    Call<Test2> repo(@Path("user") String user);
}

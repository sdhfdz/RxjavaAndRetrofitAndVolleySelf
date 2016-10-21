package com.sdh.interviewvideotest;


import com.sdh.interviewvideotest.com.sdh.http.HttpResult;
import com.sdh.interviewvideotest.com.sdh.http.SubjectForHttpResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by sdh on 2016/10/21.
 */

public  interface MovieService  {

//    @GET("top250")
//    Call<MovieEntity> getTopMovie(@Query("start") int start, @Query("count") int count);
    @GET("top250")
   Observable<HttpResult<List<SubjectForHttpResult>>>  getTopMovie(@Query("start") int start, @Query("count") int count);

}

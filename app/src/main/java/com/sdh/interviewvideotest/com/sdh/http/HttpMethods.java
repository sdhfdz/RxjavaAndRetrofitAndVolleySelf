package com.sdh.interviewvideotest.com.sdh.http;


import com.sdh.interviewvideotest.MovieService;
import com.sdh.interviewvideotest.com.sdh.exception.ApiException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;




/**
 * Created by sdh on 2016/10/21.
 */

public class HttpMethods{
    public static final String BASE_URL = "https://api.douban.com/v2/movie/";

    private static final int DEFAULT_TIMEOUT = 5;

    private Retrofit retrofit;
    private MovieService movieService;

    private  HttpMethods() {
        OkHttpClient.Builder httpClientBuilder=new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        movieService=retrofit.create(MovieService.class);

    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder{
        private static final HttpMethods INSTANCE = new HttpMethods();
    }

    //获取单例
    public static HttpMethods getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public void getTopMovie(Subscriber<List<SubjectForHttpResult>> subscriber, int start, int count){
      Observable observable= movieService.getTopMovie(start,count)
                .map(new HttpResultFunc<List<SubjectForHttpResult>>());
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
        toSubscribe(subscriber,observable);
    }

    private void toSubscribe(Subscriber subscriber, Observable observable) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    private class HttpResultFunc<T> implements Func1<HttpResult<T>,T>{

        @Override
        public T call(HttpResult<T> tHttpResult) {
            if (tHttpResult.getStart()!=0){
                throw new ApiException("start必须为0");
            }
            return tHttpResult.getSubjects();
        }
    }
}

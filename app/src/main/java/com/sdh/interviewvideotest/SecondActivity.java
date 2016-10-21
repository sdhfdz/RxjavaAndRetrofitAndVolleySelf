package com.sdh.interviewvideotest;

import android.net.sip.SipAudioCall;
import android.os.RecoverySystem;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sdh.interviewvideotest.com.sdh.Listener.ProgressSubscriber;
import com.sdh.interviewvideotest.com.sdh.Listener.SubscriberOnNextListener;
import com.sdh.interviewvideotest.com.sdh.http.HttpMethods;
import com.sdh.interviewvideotest.com.sdh.http.SubjectForHttpResult;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SecondActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://api.douban.com/v2/movie/";
    @Bind(R.id.click_me_BN)
    Button btn;

    private List<MovieEntity.SubjectsBean> subjects = new ArrayList<>();
    @Bind(R.id.result_TV)
    TextView resultTv;
    private SubscriberOnNextListener mlistener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.click_me_BN)
    public void click() {

//        getMovie();
//        getMovieByRxjavaAndRetrofit();
//        getMovieByPackaging();
        getMovie5();

    }

    private void getMovie5() {
        mlistener= new SubscriberOnNextListener<List<SubjectForHttpResult>>() {
            @Override
            public void onNext(List<SubjectForHttpResult> subjectForHttpResults) {
                setData2(subjectForHttpResults);
            }
        };
       ProgressSubscriber progressSubscriber=new ProgressSubscriber(mlistener,SecondActivity.this);
        HttpMethods.getInstance().getTopMovie(progressSubscriber,0,10);
    }

//    private void getMovie4() {
//        Subscriber<List<SubjectForHttpResult>> subscriber = new Subscriber<List<SubjectForHttpResult>>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//                System.out.println(e.getMessage()+":"+e.getCause()+"PPPPPPPPPPPPPPP");
//
//            }
//
//            @Override
//            public void onNext(List<SubjectForHttpResult> subjectForHttpResults) {
//
//                setData2(subjectForHttpResults);
//
//            }
//        };
//        HttpMethods.getInstance().getTopMovie(subscriber, 1, 10);
//    }

//    private void getMovieByPackaging() {
//        Subscriber<MovieEntity> subscriber=new Subscriber<MovieEntity>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(MovieEntity movieEntity) {
//                setData(movieEntity);
//
//            }
//        };
//        HttpMethods.getInstance().getTopMovie(subscriber,0,20);
//    }

//    private void getMovieByRxjavaAndRetrofit() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .build();
//        MovieService service = retrofit.create(MovieService.class);
//        service.getTopMovie(0, 10)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<MovieEntity>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
////                        System.out.println("_______________PPPPPP"+e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(MovieEntity movieEntity) {
//
//                      setData(movieEntity);
//
//
//                    }
//                });
//
//    }

//    private void getMovie() {
//        Retrofit retrofit=new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        MovieService service=retrofit.create(MovieService.class);
//        Call<MovieEntity> call=service.getTopMovie(0,10);
//        call.enqueue(new Callback<MovieEntity>() {
//            @Override
//            public void onResponse(Call<MovieEntity> call, Response<MovieEntity> response) {
////                resultTv.setText(response.body().toString());
//                subjects=response.body().getSubjects();
//                StringBuffer buffer=new StringBuffer();
//                for (int i=0;i<subjects.size();i++){
//                    buffer.append(subjects.get(i).getTitle()+"\n");
//                }
//                resultTv.setText(buffer.toString());
//            }
//
//            @Override
//            public void onFailure(Call<MovieEntity> call, Throwable t) {
//                resultTv.setText(t.getMessage());
//            }
//        });
//}

    public void setData(MovieEntity movieEntity) {

        System.out.println("_______________PPPPPP" + Thread.currentThread().getName());
        subjects = movieEntity.getSubjects();
        StringBuffer buffer = new StringBuffer();

        for (int i = 0; i < subjects.size(); i++) {
            buffer.append(subjects.get(i).getTitle() + "%%\n");
        }
        resultTv.setText(buffer.toString());
    }

    public void setData2(List<SubjectForHttpResult> list) {

//        System.out.println("_______________PPPPPP" + Thread.currentThread().getName());
//        subjects = movieEntity.getSubjects();
        StringBuffer buffer = new StringBuffer();

        for (int i = 0; i < list.size(); i++) {
            buffer.append(list.get(i).getTitle() + "%%\n");
        }
        resultTv.setText(buffer.toString());
    }
}

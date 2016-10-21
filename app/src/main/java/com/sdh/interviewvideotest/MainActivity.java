package com.sdh.interviewvideotest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://api.github.com";
    private static final String API_KEY = "8e13586b86e4b7f3758ba3bd6c9c9135";
    private ImageView img;
    private RequestQueue mQueue;
    private EditText et;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = (ImageView) findViewById(R.id.img);
        et = (EditText) findViewById(R.id.et);
        tv = (TextView) findViewById(R.id.tv);
        mQueue = Volley.newRequestQueue(MainActivity.this);


        getImage();

    }

    private void getImage() {
        ImageLoader imageLoader=new ImageLoader(mQueue,new BitmapCache());
        ImageLoader.ImageListener listener=ImageLoader.getImageListener(img,R.drawable.test,R.drawable.test);
        imageLoader.get("http://image95.360doc.com/DownloadImg/2016/02/1902/66267820_41.jpg",listener);
    }


    public void click(View v){
//        startService(new Intent(MainActivity.this,MyIntentService.class));
            query();
    }
    private void query(){
        //创建retrofit对象
        Retrofit retrofit=new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        //
        PhoneService service=retrofit.create(PhoneService.class);
        Call<Test2> call=service.repo(et.getText().toString());

        //发送请求
        call.enqueue(new Callback<Test2>() {
            @Override
            public void onResponse(Call<Test2> call, Response<Test2> response) {
                tv.setText(response.body().getId());
            }

            @Override
            public void onFailure(Call<Test2> call, Throwable t) {

            }
        });
    }

    public void ToSecond(View view){
        startActivity(new Intent(MainActivity.this,SecondActivity.class));
    }
}

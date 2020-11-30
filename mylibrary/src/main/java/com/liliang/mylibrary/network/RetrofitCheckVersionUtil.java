package com.liliang.mylibrary.network;




import com.liliang.mylibrary.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitCheckVersionUtil {

    private static RetrofitCheckVersionUtil instance;

    private static Retrofit retrofit;

    private RetrofitCheckVersionUtil() {
        //声明日志类
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        //设定日志级别
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            //添加拦截器
           // client.addInterceptor(httpLoggingInterceptor);
        }
        client.connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS);
        retrofit = new Retrofit.Builder()
                .client(client.build())
                //.baseUrl(BuildConfig.checkVersitonHttp)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static RetrofitCheckVersionUtil getInstance() {
        if (null == instance) {
            synchronized (RetrofitCheckVersionUtil.class) {
                instance = new RetrofitCheckVersionUtil();
            }
        }
        return instance;
    }

    public <T> T createReq(Class<T> reqServer) {
        return retrofit.create(reqServer);
    }

}

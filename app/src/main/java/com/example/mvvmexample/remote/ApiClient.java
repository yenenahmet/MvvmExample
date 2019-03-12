package com.example.mvvmexample.remote;

import android.util.Log;
import com.google.gson.JsonSyntaxException;
import java.io.IOException;
import java.net.UnknownHostException;
import retrofit2.HttpException;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit = null;
    // Singleton Design Pattern //
    public static <S> S createService(final Class<S> classs) {
        if (retrofit == null) {
            synchronized (ApiClient.class) {
                if (retrofit == null) {
                    retrofit = new Retrofit.Builder()
                            .baseUrl("https://api.openweathermap.org")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                }
            }
        }
        return retrofit.create(classs);
    }

    // Error Handler Message // gelen hata mesajına göre handler edilip geriye ona göre mesaj yollanabilir !
    public static String failMessage(final Throwable t) {
        if(t!=null){
            Log.e("ApiClientFailMessage", t.toString());
            if(t instanceof UnknownHostException || t instanceof HttpException || t instanceof IOException){
                return "İnternet Bağlantınızda Sorun Var ! Bağlantı Kurulamadı ";
            }else if(t instanceof JsonSyntaxException){
                return "Yapısal Bir Sorun Oluştu";
            }else{
                return "Veriler alınırken hata oluştu ! ";
            }
        }
        return "";
    }
    // Response // Doğru gelip gelmediğinin kontrolü  , null data üzerinden işlme yapılmasını engelleme //
    public static <T> boolean responseOperation(final Response<T> response) {
        return response != null && response.isSuccessful() && response.body() != null;
    }

}

package com.app.weather.util;

import com.app.weather.entity.WeatherInfo;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("free/week")
    Call<WeatherInfo> getWeather(@Query("appid") String appid, @Query("appsecret") String appsecret);
//    Call<ResponseBody> getWeather(@Query("appid") String appid,@Query("appsecret") String appsecret,@Query("cityid") String cityid,@Query("city") String city,@Query("ip") String ip,@Query("callback") String callback,@Query("vue") String vue,@Query("unescape") int unescape);

}

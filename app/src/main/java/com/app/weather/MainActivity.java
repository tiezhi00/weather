package com.app.weather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.app.weather.databinding.ActivityMainBinding;
import com.app.weather.entity.WeatherInfo;
import com.app.weather.util.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private String TAG = MainActivity.class.getSimpleName();

    private String baseUrl = "http://v1.yiketianqi.com";
    private AppCompatSpinner spinner;

    private ActivityMainBinding binding;
    private ArrayAdapter<String> adapter;
    private String[] cities;
    private WeatherInfo weatherInfo;

    private Handler handler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            //处理接收的消息
            switch (msg.what) {
                case 1:
                    Log.i(TAG, "----主线程handleMessage: case 1,更新天气UI");
                    //取出数据
                    WeatherInfo weatherInfo1 = (WeatherInfo) msg.obj;
                    List<WeatherInfo.DataDTO> list = weatherInfo1.getData();
                    if (list != null && !list.isEmpty()) {
                        WeatherInfo.DataDTO todayWeather = list.get(0);
                        if (todayWeather != null) {
                            //处理UI更新
                            binding.ivWeather.setBackgroundResource(getWeatherImgResId(todayWeather.getWea_img()));
                            binding.tvTemperature.setText(todayWeather.getWea());
                            binding.tvWeather.setText(todayWeather.getWea() + "(" + todayWeather.getDate() + ")");
                            binding.tvTemperatureRange.setText(todayWeather.getTem_night() + "℃~" + todayWeather.getTem_day() + "℃");
                            binding.tvWind.setText(todayWeather.getWin() + todayWeather.getWin_speed());
                        }
                    }
                    break;
                default:
            }
        }
    };

    private int getWeatherImgResId(String wea) {
        //固定9种类型(您也可以根据wea字段自己处理):
        //xue、lei、shachen、wu、bingbao、yun、yu、yin、qing
        int resId = 0;
        switch (wea) {
            case "xue":
                resId = R.drawable.img_weather_xue;
                break;
            case "lei":
                resId = R.drawable.img_weather_lei;
                break;
            case "shachen":
                resId = R.drawable.img_weather_shachen;
                break;
            case "wu":
                resId = R.drawable.img_weather_wu;
                break;
            case "bingbao":
                resId = R.drawable.img_weather_bingbao;
                break;
            case "yun":
                resId = R.drawable.img_weather_yun;
                break;
            case "yin":
                resId = R.drawable.img_weather_yin;
                break;
            case "yu":
                resId = R.drawable.img_weather_yu;
                break;
            case "qing":
                resId = R.drawable.img_weather_qing;
                break;
            default:
                resId = R.drawable.img_weather_qing;
                break;
        }
        return resId;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initViews();
        initListener();
    }

    private void initListener() {
    }

    private void initViews() {
        cities = getResources().getStringArray(R.array.cities);
        adapter = new ArrayAdapter<>(this, R.layout.sp_item_layout, cities);
        //关联Adapter
        binding.spinner.setAdapter(adapter);
        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String city = cities[i];
                Log.i(TAG, "--------onItemSelected: city=" + city);
                getWeatherOfCity(city);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //
    }

    private void getWeatherOfCity(String city) {
        //开启子线程，请求网络
        new Thread(new Runnable() {
            @Override
            public void run() {
                //请求网络请求
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ApiService apiService = retrofit.create(ApiService.class);

                apiService.getWeather("86655557", "v0e6H7kC", city).enqueue(new Callback<WeatherInfo>() {
                    @Override
                    public void onResponse(Call<WeatherInfo> call, Response<WeatherInfo> response) {
                        if (response.isSuccessful()) {
                            weatherInfo = response.body();
                            // 处理获取到的数据
                            Log.i(TAG, "onResponse: " + weatherInfo.toString());
//                            Log.i(TAG, "onResponse: " + weatherInfo.getData().toString());
                            //将通过Handler传给主线程，发送消息
                            Message message = new Message();//这种方式是从消息池中获取一个可用的 Message 对象进行复用，如果消息池中没有可用的对象，才会创建新的。
                            message.what = 1;
                            message.obj = weatherInfo;
                            handler.sendMessage(message);

                        } else {
                            // 处理错误情况
                        }
                    }

                    @Override
                    public void onFailure(Call<WeatherInfo> call, Throwable t) {
                        // 处理网络请求失败

                    }
                });
            }
        }).start();


    }
}
package com.app.weather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.app.weather.databinding.ActivityMainBinding;
import com.app.weather.entity.WeatherInfo;
import com.app.weather.util.ApiService;

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

                apiService.getWeather("86655557", "v0e6H7kC").enqueue(new Callback<WeatherInfo>() {
                    @Override
                    public void onResponse(Call<WeatherInfo> call, Response<WeatherInfo> response) {
                        if (response.isSuccessful()) {
                            weatherInfo = response.body();
                            // 处理获取到的数据
                            Log.i(TAG, "onResponse: " + weatherInfo.toString());
                            Log.i(TAG, "onResponse: " + weatherInfo.getData().toString());
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
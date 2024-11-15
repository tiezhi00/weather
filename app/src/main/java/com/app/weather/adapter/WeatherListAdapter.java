package com.app.weather.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.app.weather.R;
import com.app.weather.entity.WeatherInfo;
import com.app.weather.util.WeatherResUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.QuickViewHolder;

import java.util.List;

public class WeatherListAdapter extends BaseQuickAdapter<WeatherInfo.DataDTO, QuickViewHolder> {
    //构造方法传入数据
    public WeatherListAdapter(List<WeatherInfo.DataDTO> list) {
        setItems(list);
    }

    @NonNull
    @Override
    protected QuickViewHolder onCreateViewHolder(@NonNull Context context, @NonNull ViewGroup viewGroup, int i) {
        //返回一个QuickViewHolder
        return new QuickViewHolder(R.layout.weather_list_item, viewGroup);
    }

    @Override
    protected void onBindViewHolder(@NonNull QuickViewHolder quickViewHolder, int i, @Nullable WeatherInfo.DataDTO dataDTO) {
        //数据控件进行绑定
        ImageView iv_weather = quickViewHolder.getView(R.id.iv_weather);
        TextView tv_temperature = quickViewHolder.getView(R.id.tv_temperature);
        TextView tv_date = quickViewHolder.getView(R.id.tv_date);
        TextView tv_temperature_range = quickViewHolder.getView(R.id.tv_temperature_range);
        TextView tv_wind = quickViewHolder.getView(R.id.tv_wind);
        TextView tv_wind_speed = quickViewHolder.getView(R.id.tv_wind_speed);
        iv_weather.setBackgroundResource(WeatherResUtil.getWeatherImgResId(getItem(i).getWea_img()));
        tv_temperature.setText(getItem(i).getWea());
        tv_temperature_range.setText(getItem(i).getTem_night() + "℃~" + getItem(i).getTem_day() + "℃");
        tv_date.setText(getItem(i).getDate());
        tv_wind.setText(getItem(i).getWin());
        tv_wind_speed.setText(getItem(i).getWin_speed());
    }
}

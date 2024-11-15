package com.app.weather.util;

import com.app.weather.R;

public abstract class WeatherResUtil {
    public static int getWeatherImgResId(String wea) {
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

}

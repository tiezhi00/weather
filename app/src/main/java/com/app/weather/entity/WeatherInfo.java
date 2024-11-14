package com.app.weather.entity;

import java.util.List;


public class WeatherInfo {

    private String cityid;
    private String city;
    private String update_time;
    private List<DataDTO> data;

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public List<DataDTO> getData() {
        return data;
    }

    public void setData(List<DataDTO> data) {
        this.data = data;
    }

    public static class DataDTO {
        private String date;
        private String wea;
        private String wea_img;
        private String tem_day;
        private String tem_night;
        private String win;
        private String win_speed;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getWea() {
            return wea;
        }

        public void setWea(String wea) {
            this.wea = wea;
        }

        public String getWea_img() {
            return wea_img;
        }

        public void setWea_img(String wea_img) {
            this.wea_img = wea_img;
        }

        public String getTem_day() {
            return tem_day;
        }

        public void setTem_day(String tem_day) {
            this.tem_day = tem_day;
        }

        public String getTem_night() {
            return tem_night;
        }

        public void setTem_night(String tem_night) {
            this.tem_night = tem_night;
        }

        public String getWin() {
            return win;
        }

        public void setWin(String win) {
            this.win = win;
        }

        public String getWin_speed() {
            return win_speed;
        }

        public void setWin_speed(String win_speed) {
            this.win_speed = win_speed;
        }

        @Override
        public String toString() {
            return "DataDTO{" +
                    "date='" + date + '\'' +
                    ", wea='" + wea + '\'' +
                    ", wea_img='" + wea_img + '\'' +
                    ", tem_day='" + tem_day + '\'' +
                    ", tem_night='" + tem_night + '\'' +
                    ", win='" + win + '\'' +
                    ", win_speed='" + win_speed + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "WeatherInfo{" +
                "cityid='" + cityid + '\'' +
                ", city='" + city + '\'' +
                ", update_time='" + update_time + '\'' +
                ", data=" + data +
                '}';
    }
}

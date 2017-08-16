package indi.aljet.network_volleytest.entity;

import java.util.List;

/**
 * Created by PC-LJL on 2017/8/15.
 */

public class Results {

    private String currentCity;
    private List<Weather> weather_data;

    public String getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }

    public List<Weather> getWeather_data() {
        return weather_data;
    }

    public void setWeather_data(List<Weather> weather_data) {
        this.weather_data = weather_data;
    }
}

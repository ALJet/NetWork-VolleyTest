package indi.aljet.network_volleytest.entity;

import java.util.List;

/**
 * Created by PC-LJL on 2017/8/16.
 */

public class MobWeather {

    private int error;
    private String status;
    private String date;
    private List<Results> results;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }
}

package com.example.android.quakereport;

public class location_info {

    private double richter_magnitude;
    private String location;
    private long Date;
    private String mUrl;

    public location_info(double magn, String place, long date,String url){
        richter_magnitude=magn;
        location=place;
        Date=date;
        mUrl=url;
    }

    public double getRichter_magnitude() {
        return (richter_magnitude);
    }

    public long getDate() {
        return Date;
    }

    public String getLocation() {
        return location;
    }

    public String getmUrl() {
        return mUrl;
    }
}

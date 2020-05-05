package com.example.android.quakereport;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    private infoAdapter adapter;
    private String USGS_REQUEST_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=6&limit=10";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);
        ListView earthquakeListView = (ListView) findViewById(R.id.list);
        adapter = new infoAdapter(this, new ArrayList<location_info>());
        earthquakeListView.setAdapter(adapter);

        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                location_info currentEarthquake = adapter.getItem(position);
                Uri earthquakeUri = Uri.parse(currentEarthquake.getmUrl());
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);
                startActivity(websiteIntent);
            }
        });
        TaskAsync task = new TaskAsync();
        task.execute(USGS_REQUEST_URL);
    }
    private class TaskAsync extends AsyncTask<String,Void,ArrayList<location_info>>{

        @Override
        protected ArrayList<location_info> doInBackground(String... url) {
            try {
                return QueryUtils.fetchEarthquakeData(url[0]);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<location_info> location_info) {
            // Clear the adapter of previous earthquake data
            adapter.clear();

            
            if (location_info != null && !location_info.isEmpty()) {
                adapter.addAll(location_info);
            }
        }
    }
}

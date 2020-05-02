package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class infoAdapter extends ArrayAdapter<location_info> {


    public infoAdapter(@NonNull Context context, @NonNull ArrayList<location_info> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.earthquake_list_item, parent, false);
        }
        location_info currentInfo = getItem(position);

        TextView richter_text = (TextView) listItemView.findViewById(R.id.info_richter);
        richter_text.setText(Double.toString(currentInfo.getRichter_magnitude()));
        GradientDrawable magnitudeCircle = (GradientDrawable) richter_text.getBackground();
        switch((int) Math.floor(currentInfo.getRichter_magnitude())){
            case  1:
                magnitudeCircle.setColor(ContextCompat.getColor(getContext(), R.color.magnitude1));
                break;
            case  2:
                magnitudeCircle.setColor(ContextCompat.getColor(getContext(), R.color.magnitude2));
                break;
            case  3:
                magnitudeCircle.setColor(ContextCompat.getColor(getContext(), R.color.magnitude3));
                break;
            case  4:
                magnitudeCircle.setColor(ContextCompat.getColor(getContext(), R.color.magnitude4));
                break;
            case  5:
                magnitudeCircle.setColor(ContextCompat.getColor(getContext(), R.color.magnitude5));
                break;
            case  6:
                magnitudeCircle.setColor(ContextCompat.getColor(getContext(), R.color.magnitude6));
                break;
            case  7:
                magnitudeCircle.setColor(ContextCompat.getColor(getContext(), R.color.magnitude7));
                break;
            case  8:
                magnitudeCircle.setColor(ContextCompat.getColor(getContext(), R.color.magnitude8));
                break;
            case  9:
                magnitudeCircle.setColor(ContextCompat.getColor(getContext(), R.color.magnitude9));
                break;
            default:
                magnitudeCircle.setColor(ContextCompat.getColor(getContext(), R.color.magnitude10plus));
                break;
        }


        TextView location_text = (TextView) listItemView.findViewById(R.id.info_location);
        TextView offset_text = (TextView) listItemView.findViewById(R.id.info_location_offset);
        int i = currentInfo.getLocation().indexOf("of");

        if(i!=-1) {
            offset_text.setText(currentInfo.getLocation().substring(0, i + 2));
            location_text.setText(currentInfo.getLocation().substring(i + 3, currentInfo.getLocation().length()));
        }
        else{
            location_text.setText(currentInfo.getLocation());
            offset_text.setText("");
        }



        TextView date_text = (TextView) listItemView.findViewById(R.id.info_date);
        TextView time_text = (TextView) listItemView.findViewById(R.id.info_time);
        Date dateObject = new Date(currentInfo.getDate());
        String formattedDate = new SimpleDateFormat("MMM dd, yyyy").format(dateObject);
        String formattedTime = new SimpleDateFormat("HH:mm").format(dateObject);
        date_text.setText(formattedDate);
        time_text.setText(formattedTime);

        return listItemView;
    }
}

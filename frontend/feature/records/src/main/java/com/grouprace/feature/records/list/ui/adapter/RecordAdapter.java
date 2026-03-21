package com.grouprace.feature.records.list.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.grouprace.feature.records.R;
import com.grouprace.core.model.Record;

import java.util.List;

public class RecordAdapter extends ArrayAdapter<Record> {

    public RecordAdapter(Context context, List<Record> records) {
        super(context, R.layout.item_record, records);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Record currentRecord = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_record, parent, false);
        }

        if (currentRecord != null) {
            TextView start = convertView.findViewById(R.id.tv_start_time);
            TextView speed = convertView.findViewById(R.id.tv_speed);
            TextView distance = convertView.findViewById(R.id.distance);
            TextView duration = convertView.findViewById(R.id.duration);
            TextView activityType = convertView.findViewById(R.id.tv_activity_type);

            start.setText(currentRecord.getStartTime());

            speed.setText(String.format(java.util.Locale.getDefault(), "%.1f km/h", currentRecord.getSpeed()));

            distance.setText(String.format(java.util.Locale.getDefault(), "%.2f km", currentRecord.getDistance()));

            int totalSeconds = currentRecord.getDuration();
            int minutes = totalSeconds / 60;
            int seconds = totalSeconds % 60;
            duration.setText(String.format(java.util.Locale.getDefault(), "%02d:%02d", minutes, seconds));

            activityType.setText(currentRecord.getActivityType());
        }

        return convertView;
    }
}

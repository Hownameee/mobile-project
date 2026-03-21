package com.grouprace.feature.records.list.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.grouprace.core.model.Record;
import com.grouprace.feature.records.R;
import com.grouprace.feature.records.detail.ui.RecordDetailFragment;
import com.grouprace.feature.records.list.ui.adapter.RecordAdapter;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class RecordListFragment extends Fragment {
    private RecordListViewModel viewModel;
    private RecordAdapter adapter;

    public RecordListFragment() {
        super(R.layout.fragment_list_record);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView listRecord = view.findViewById(R.id.lv_record_list);
        adapter = new RecordAdapter(requireContext(), new ArrayList<>());
        listRecord.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(RecordListViewModel.class);

        viewModel.getRecords().observe(getViewLifecycleOwner(), records -> {
            if (records != null) {
                adapter.clear();
                adapter.addAll(records);
                adapter.notifyDataSetChanged();
            }
        });

        listRecord.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Record clickedRecord = adapter.getItem(position);
                if (clickedRecord != null) {
                    RecordDetailFragment detailFragment = RecordDetailFragment.newInstance(clickedRecord.getActivityType(), clickedRecord.getStartTime(), String.format(java.util.Locale.getDefault(), "%.2f km", clickedRecord.getDistance()), String.format(java.util.Locale.getDefault(), "%.1f km/h", clickedRecord.getSpeed()), String.format(java.util.Locale.getDefault(), "%.0f bpm", clickedRecord.getHeartRate()), String.format(java.util.Locale.getDefault(), "%.0f kcal", clickedRecord.getCalories()), clickedRecord.getDuration() + "s");
                    requireActivity().getSupportFragmentManager().beginTransaction().replace(getId(), detailFragment).addToBackStack(null).commit();
                }
            }
        });
    }
}

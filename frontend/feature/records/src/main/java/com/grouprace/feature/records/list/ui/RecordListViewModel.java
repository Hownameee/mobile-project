package com.grouprace.feature.records.list.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.grouprace.core.data.repository.RecordRepository;
import com.grouprace.core.model.Record;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class RecordListViewModel extends ViewModel {
    private final RecordRepository recordRepository;
    private final MutableLiveData<Void> fetchTrigger = new MutableLiveData<>();
    private final LiveData<List<Record>> records;

    @Inject
    public RecordListViewModel(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;

        this.records = Transformations.switchMap(fetchTrigger, trigger -> {
            return recordRepository.getRecords();
        });

        fetchRecords();
    }

    public LiveData<List<Record>> getRecords() {
        return records;
    }

    public void fetchRecords() {
        fetchTrigger.setValue(null);
    }
}

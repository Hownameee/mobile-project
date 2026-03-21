package com.grouprace.core.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.grouprace.core.model.Record;
import com.grouprace.core.network.model.NetworkRecord;
import com.grouprace.core.network.source.RecordNetworkDataSource;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

public class RecordRepositoryImpl implements RecordRepository {

    private final RecordNetworkDataSource recordNetworkDataSource;

    @Inject
    public RecordRepositoryImpl(RecordNetworkDataSource recordNetworkDataSource) {
        this.recordNetworkDataSource = recordNetworkDataSource;
    }

    @Override
    public LiveData<List<Record>> getRecords() {
        return Transformations.map(recordNetworkDataSource.getRecords(), networkRecords -> {
            if (networkRecords == null) return null;
            return networkRecords.stream().map(NetworkRecord::asExternalModel).collect(Collectors.toList());
        });
    }
}
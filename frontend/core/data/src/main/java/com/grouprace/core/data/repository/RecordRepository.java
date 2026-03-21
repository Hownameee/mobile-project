package com.grouprace.core.data.repository;

import androidx.lifecycle.LiveData;

import com.grouprace.core.model.Record;

import java.util.List;

public interface RecordRepository {
    LiveData<List<Record>> getRecords();
}

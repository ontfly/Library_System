package com.systcloud.dao;

import com.github.pagehelper.Page;
import com.systcloud.domain.Record;

public interface RecordMapper {
    Integer addRecord(Record record);
    Page<Record> searchRecords(Record record);
}

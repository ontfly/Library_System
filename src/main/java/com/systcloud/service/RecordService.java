package com.systcloud.service;

import com.systcloud.domain.Record;
import com.systcloud.domain.User;
import com.systcloud.entity.pageResult;

public interface RecordService {
    Integer addRecord(Record record);
    pageResult searchRecord(Record record, User user, Integer pageNum, Integer pageSize);
}

package com.systcloud.service.serviceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.systcloud.dao.RecordMapper;
import com.systcloud.domain.Record;
import com.systcloud.domain.User;
import com.systcloud.entity.pageResult;
import com.systcloud.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordMapper recordMapper;
    @Override
    public Integer addRecord(Record record) {
        return recordMapper.addRecord(record);
    }

    @Override
    public pageResult searchRecord(Record record, User user, Integer pageNum, Integer pageSize) {
        if(pageNum==null){
            pageNum=1;
        }
        if(pageSize==null){
            pageSize=10;
        }
        PageHelper.startPage(pageNum,pageSize);
        if(!"ADMIN".equals(user.getRole())){
            record.setBorrower(user.getName());
        }
        Page<Record> page = recordMapper.searchRecords(record);
        return new pageResult(page.getTotal(),page.getResult());
    }
}

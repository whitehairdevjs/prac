package com.example.prac.service;

import com.example.prac.entity.Batch;
import com.example.prac.mapper.BatchMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.HashMap;
import java.util.List;

@Service
public class BatchService {
    @Autowired
    private BatchMapper batchMapper;

    public List<Batch> selectBatchList(HashMap<String, Object> map) {
        return batchMapper.selectBatchList(map);
    }
}

package com.example.prac.mapper;

import com.example.prac.entity.Batch;
import com.example.prac.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface BatchMapper {
    List<Batch> selectBatchList(HashMap<String, Object> map);
}

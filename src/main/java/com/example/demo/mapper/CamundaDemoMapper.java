package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface CamundaDemoMapper {
    @Select("select * from demo_table")
    List<Map<String,Object>> testSelect();
}

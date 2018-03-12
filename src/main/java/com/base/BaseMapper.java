package com.base;

import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface BaseMapper<T extends BaseModel> {

    int deleteByPrimaryKey(String id);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);

    List<T> selectAll();

    Page<T> findByCondition(Map<String, Object> params);
}

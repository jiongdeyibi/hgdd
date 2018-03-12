package com.base;

public interface BaseService<T extends BaseModel> {

//    List<T> findByCondition(Map<String, Object> params);
//
//    PageInfo<T> findByPage(Map<String, Object> params);

    int deleteByPrimaryKey(String id);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);

}
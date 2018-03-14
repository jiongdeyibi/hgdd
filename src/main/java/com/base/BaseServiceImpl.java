package com.base;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class BaseServiceImpl<T extends BaseModel> implements BaseService<T> {
    protected final Logger logger = LogManager.getLogger(this.getClass());
    @Autowired
    private BaseMapper<T> mapper;

    @Override
    public List<T> findByCondition(Map<String, Object> params) {
        Page<T> list = mapper.findByCondition(params);
        logger.info("findByCondition:" + list);
        return list;
    }

    @Override
    public PageInfo<T> findByPage(Map<String, Object> params) {
        int pageNum = params.get("pageNum") == null ? 1 : Integer.parseInt((String) params.get("pageNum"));
        int pageSize = params.get("pageSize") == null ? 10 : Integer.parseInt((String) params.get("pageSize"));
        String orderBy = params.get("orderBy") == null ? "" : params.get("orderBy").toString();
        if (StringUtils.isNotEmpty(orderBy)) {
            PageHelper.startPage(pageNum, pageSize, orderBy);
        } else {
            PageHelper.startPage(pageNum, pageSize);
        }
        return new PageInfo<T>(mapper.findByCondition(params));
    }

    @Override
    @Transactional
    public int deleteByPrimaryKey(String id) {
        T record = mapper.selectByPrimaryKey(id);
        logger.info("deleteByPrimaryKey:" + record);
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional
    public int insert(T record) {
        logger.info("insert:" + record);
        return mapper.insert(record);
    }

    @Override
    @Transactional
    public int insertSelective(T record) {
        logger.info("insertSelective:" + record);
        return mapper.insertSelective(record);
    }

    @Override
    public T selectByPrimaryKey(String id) {
        T record = mapper.selectByPrimaryKey(id);
        logger.info("selectByPrimaryKey:" + record);
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public int updateByPrimaryKeySelective(T record) {
        logger.info("updateByPrimaryKeySelective:" + record);
        return mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional
    public int updateByPrimaryKey(T record) {
        logger.info("updateByPrimaryKey:" + record);
        return mapper.updateByPrimaryKey(record);
    }
}

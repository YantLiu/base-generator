package com.newrun.xiruo.xframework.service;

import com.newrun.xiruo.xframework.util.EntityUtils;
import com.newrun.xiruo.xframework.util.ReflectionUtils;
import com.newrun.xiruo.xframework.util.UUIDGenerate;
import com.newrun.xiruo.xframework.util.ValidateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;

import java.io.Serializable;
import java.util.List;

/**
 * Created by
 * Date: 17/1/13
 * Time: 15:13
 * Version 1.0.0
 */
public class BaseTkService<M extends Mapper<T>, T extends Serializable> {
    @Autowired
    public M mapper;

    public void setMapper(M mapper) {
        this.mapper = mapper;
    }

    public M getMapper() {
        return mapper;
    }

    public T selectOne(T entity) {
        return mapper.selectOne(entity);
    }

    public T selectById(Object id) {
        return mapper.selectByPrimaryKey(id);
    }

    public List<T> selectList(T entity) {
        return mapper.select(entity);
    }

    public List<T> selectListAll() {
        return mapper.selectAll();
    }

    public Long selectCount(T entity) {
        return Long.valueOf(mapper.selectCount(entity));
    }

    public boolean insert(T entity) {
        //没有id则注入id
        if(ValidateUtils.isObjectEmpty(ReflectionUtils.getFieldValue(entity, "id"))){
            ReflectionUtils.invokeSetter(entity, "id", UUIDGenerate.getNextId());
        }
        EntityUtils.setCreatAndUpdatInfo(entity);
        return 1 == mapper.insert(entity);
    }


    public boolean insertSelective(T entity) {
        //没有id则注入id
        if(ValidateUtils.isObjectEmpty(ReflectionUtils.getFieldValue(entity, "id"))){
            ReflectionUtils.invokeSetter(entity, "id", UUIDGenerate.getNextId());
        }
        EntityUtils.setCreatAndUpdatInfo(entity);
        return 1 == mapper.insertSelective(entity);
    }

    public boolean deleteById(Object id) {
        return 1 == mapper.deleteByPrimaryKey(id);
    }


    public boolean updateById(T entity) {
        EntityUtils.setUpdatedInfo(entity);
        return 1 == mapper.updateByPrimaryKey(entity);
    }


    public boolean updateSelectiveById(T entity) {
        EntityUtils.setUpdatedInfo(entity);
        return 1 == mapper.updateByPrimaryKeySelective(entity);
    }

    public List<T> selectByExample(Object example) {
        return mapper.selectByExample(example);
    }

    public int selectCountByExample(Object example) {
        return mapper.selectCountByExample(example);
    }
}

package com.fyj.dao;

import com.fyj.entity.Sessions;

public interface SessionDao {
    int deleteByPrimaryKey(Long id);

    int insert(Sessions record);

    int insertSelective(Sessions record);

    Sessions selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Sessions record);

    int updateByPrimaryKey(Sessions record);
}
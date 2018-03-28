package com.fyj.dao;

import java.util.List;

import com.fyj.dto.SysResourceVo;

public interface SysResourceDao {
  SysResourceVo selectById(Long id);
  
  List<SysResourceVo> findAll();
}

package com.fyj.service;

import java.util.Set;

import com.fyj.dto.SysResourceVo;

public interface SysResourceService {
   Set<String> findPerimissions(String userName);
   
   SysResourceVo selectById(Long id);
}

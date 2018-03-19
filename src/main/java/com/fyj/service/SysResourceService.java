package com.fyj.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.fyj.dto.SysResourceVo;

@Service("sysResourceService")
public interface SysResourceService {
   Set<String> findPerimissions(String userName);
   
   SysResourceVo selectById(Long id);
}

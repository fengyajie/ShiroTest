package com.fyj.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.fyj.dto.SysUserVo;
import com.fyj.service.SysUserService;

public class MybatisMapperTest{

	public static void main(String[] args) {
		InputStream input = null;
		SqlSession sqlSession = null;
		try {
			input = new FileInputStream("SysUserMapper.xml");
			SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
			SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(input);
			sqlSession = sqlSessionFactory.openSession();
			SysUserService  sysUserService = sqlSession.getMapper(SysUserService.class);
			List<SysUserVo>  list = sysUserService.selectList(null);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		

	}

}

package com.fyj.dao;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.Serializable;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;

import com.fyj.entity.Sessions;
import com.mchange.v2.ser.SerializableUtils;

public class MySessionDao extends CachingSessionDAO{

	@Autowired
	private SessionDao sessiondao;
	
	@Override
	protected void doUpdate(Session session) {

		//如果会话更新/停止没必要在更新
		if(session instanceof ValidatingSession && !((ValidatingSession)session).isValid()){
			return;
		}
		Sessions sessions = new Sessions();
		sessions.setId(Long.valueOf(session.getId().toString()));
		try {
			sessions.setSession(SerializableUtils.serializeToByteArray(sessions).toString());
		} catch (NotSerializableException e) {
			e.printStackTrace();
		}
		sessiondao.updateByPrimaryKey(sessions);
		
	}

	@Override
	protected void doDelete(Session session) {

		sessiondao.deleteByPrimaryKey(Long.valueOf(session.getId().toString()));
	}

	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId = generateSessionId(session);
		assignSessionId(session,sessionId);
		Sessions entity = new Sessions();
		entity.setId(Long.valueOf(sessionId.toString()));
		try {
			entity.setSession(SerializableUtils.serializeToByteArray(entity).toString());
		} catch (NotSerializableException e) {
			e.printStackTrace();
		}
		sessiondao.insert(entity);
		return session.getId();
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		Sessions sessions = sessiondao.selectByPrimaryKey(Long.valueOf(sessionId.toString()));
		if(sessions == null) return null;
		Session session = null;
		try {
			session = (Session) SerializableUtils.deserializeFromByteArray(sessions.getSession().getBytes());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return session;
	}
	

}

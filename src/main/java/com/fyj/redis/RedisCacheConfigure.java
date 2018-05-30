package com.fyj.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisCacheConfigure implements InitializingBean {
	
	private static Logger logger = LoggerFactory.getLogger(RedisCacheConfigure.class);
	
	//redis服务器ip
	private String addr;
	//redis端口号
	private int port;
	//访问密码
	private String auth;
	
	//可用链接实例的最大数目,默认值为8
	//如果赋值为-1，则表示不限制;如果pool已经分配了maxActive个jedis实例,则表示此时pool的状态为耗尽
	private int maxActive;
	//控制一个pool最多有多少个状态为idle（空闲）的jedis实例，默认也是8
	private int maxIdle;
    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException
	private int maxWait;
	
	private int timeOut;
	//在borrow一个jedis实例时，是否提前进行validate操作；如果是true，则得到的jedis实例均是可用的
	private boolean testOnBorrow;
	
	public Jedis jedis;
	
	public JedisPool jedisPool;
	
	private void initialPool() {
		//池基本配置
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(maxActive);
		config.setMaxIdle(maxIdle);
		config.setMaxWaitMillis(maxWait);
		config.setTestOnBorrow(testOnBorrow);
		new JedisPool(config,addr,port,timeOut,auth);
	}
	public void afterPropertiesSet() throws Exception {
		initialPool();
		jedis = jedisPool.getResource();
	}
	
	public JedisPool getJedisPool() {
		return jedisPool;
	}
	
	public void close(Jedis jedis) {
		jedisPool.returnResource(jedis);
	}
	public void cloaeEx(Jedis jedis) {
		jedisPool.returnBrokenResource(jedis);
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public int getMaxActive() {
		return maxActive;
	}
	public void setMaxActive(int maxActive) {
		this.maxActive = maxActive;
	}
	public int getMaxIdle() {
		return maxIdle;
	}
	public void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
	}
	public int getMaxWait() {
		return maxWait;
	}
	public void setMaxWait(int maxWait) {
		this.maxWait = maxWait;
	}
	public int getTimeOut() {
		return timeOut;
	}
	public void setTimeOut(int timeOut) {
		this.timeOut = timeOut;
	}
	public boolean isTestOnBorrow() {
		return testOnBorrow;
	}
	public void setTestOnBorrow(boolean testOnBorrow) {
		this.testOnBorrow = testOnBorrow;
	}
	

}

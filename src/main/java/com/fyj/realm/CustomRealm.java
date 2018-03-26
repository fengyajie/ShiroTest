package com.fyj.realm;

import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.fyj.dto.SysUserVo;
import com.fyj.service.SysResourceService;
import com.fyj.service.SysRoleService;
import com.fyj.service.SysUserService;
import com.fyj.util.Md5Util;

public class CustomRealm  extends AuthorizingRealm{

	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private SysRoleService sysRoleService;
	
	@Autowired
	private SysResourceService sysResourceService;
	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String userName = (String)principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		Set<String> roles = sysRoleService.findRoles(userName);
		Set<String> permissions = sysResourceService.findPerimissions(userName);
		authorizationInfo.setRoles(roles);
		authorizationInfo.setStringPermissions(permissions);
		return authorizationInfo;
	}

	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken userNamePassword = (UsernamePasswordToken)token;
		String userName = userNamePassword.getUsername().trim();
		String password = String.valueOf(userNamePassword.getPassword());
		SysUserVo sysUserVo = new SysUserVo();
		sysUserVo.setUserName(userName);
		List<SysUserVo> sysUserList = userLogin(sysUserVo);
		
		if(sysUserList == null || sysUserList.size() < 0){
			throw new UnknownAccountException();//没有此账号
		}
		
		SimpleAuthenticationInfo info = null;
		if(sysUserList != null && sysUserList.size() > 0 ){
			SysUserVo suVo = sysUserList.get(0);
			
			if("0".equals(suVo.getSalt())){
				throw new LockedAccountException();
			}
			String salt = suVo.getSalt();
			String passwordDigest = Md5Util.md5Degest(password+salt);
			if(!passwordDigest.equals(suVo.getPassword())) {
				throw new IncorrectCredentialsException();
			}
			info = new SimpleAuthenticationInfo(userName,password,this.getName());
		}
		
		return info;
	}
	public List<SysUserVo> userLogin(SysUserVo sysUserVo){
		List<SysUserVo> sysUserList = sysUserService.selectList(sysUserVo);
		return sysUserList;
	}

}

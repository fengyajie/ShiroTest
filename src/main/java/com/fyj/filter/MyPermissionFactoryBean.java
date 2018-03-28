package com.fyj.filter;

import java.util.List;

import org.apache.shiro.config.Ini;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.config.IniFilterChainResolverFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fyj.dto.SysResourceVo;
import com.fyj.service.SysResourceService;

public class MyPermissionFactoryBean extends ShiroFilterFactoryBean{

	/**
	 * ≈‰÷√÷–µƒπ˝¬À¡¥
	 */
	public static String definitions;
	
	@Autowired
	private SysResourceService sysResourceService; 
	
	@Override
	public void setFilterChainDefinitions(String definitions) {
		this.definitions = definitions;
		
		List<SysResourceVo> sysResourceVoList = sysResourceService.findAll();
		for(SysResourceVo sysResourceVo:sysResourceVoList) {
			definitions=definitions+sysResourceVo.getUrl()+" = " +"perms["+sysResourceVo.getPermission()+"]";
		}
		System.out.println("--------------------------------------------------"+definitions);
		Ini ini = new Ini();
        ini.load(definitions);
        //did they explicitly state a 'urls' section?  Not necessary, but just in case:
        Ini.Section section = ini.getSection(IniFilterChainResolverFactory.URLS);
        if (CollectionUtils.isEmpty(section)) {
            //no urls section.  Since this _is_ a urls chain definition property, just assume the
            //default section contains only the definitions:
            section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
        }
        setFilterChainDefinitionMap(section);
	}

}

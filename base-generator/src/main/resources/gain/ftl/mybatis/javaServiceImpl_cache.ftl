package ${packageName?replace('/','.')}.service.impl.${mypackageName};

import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ${packageName?replace('/','.')}.model.${mypackageName}.${voClassName};
import ${packageName?replace('/','.')}.dao.${mypackageName}.${voClassName}Dao;
import ${packageName?replace('/','.')}.service.${mypackageName}.${voClassName}Service;
import com.chexun.base.framework.core.entity.PageEntity;
import com.chexun.base.cache.QueryProvider;
/**
 * ${voClassName}管理接口
 * User:
 * Date: ${date?string("yyyy-MM-dd")}
 */
@Service("${parmString}Service")
public class ${voClassName}ServiceImpl implements ${voClassName}Service{

 	@Autowired
    private ${voClassName}Dao ${parmString}Dao;
    @Autowired
	private QueryProvider queryCache;
    /**
     * 添加${voClassName}
     * @param ${parmString} 要添加的${voClassName}
     * @return id
     */
    public Long add${voClassName}(${voClassName} ${parmString}){
    	Long res = ${parmString}Dao.add${voClassName}(${parmString});
    	if(res>0){
    		queryCache.setObj("Maintenance_${voClassName}",String.valueOf(${parmString}.getId()),${parmString});
    	}
    	return res;
    }
	public Long insert${voClassName}(${voClassName} ${parmString}){
		Long res = ${parmString}Dao.insert${voClassName}(${parmString});
		if(res>0){
    		queryCache.setObj("Maintenance_${voClassName}",String.valueOf(${parmString}.getId()),${parmString});
    	}
    	return res;
	}
    /**
     * 根据id删除一个${voClassName}
<#list columnList as column>
    <#if column.key>
     * @param ${column.propertyName} 要删除的id
    </#if>
</#list>
     */
    public Long delete${voClassName}ById(<#assign i=0><#list columnList as column><#if column.key><#assign i=i+1><#if (i>1)>,</#if>${column.propertyType} ${column.propertyName}</#if></#list>){
    	queryCache.deleteObj("Maintenance_${voClassName}",String.valueOf(<#assign i=0><#list columnList as column><#if column.key><#assign i=i+1><#if (i>1)></#if>${column.propertyName}</#if></#list>));
    	return ${parmString}Dao.delete${voClassName}ById(<#assign i=0><#list columnList as column><#if column.key><#assign i=i+1><#if (i>1)></#if>${column.propertyName}</#if></#list>);
    }
	public Long delete${voClassName}ByObj(${voClassName} ${parmString}){
    	queryCache.deleteObj("Maintenance_${voClassName}",String.valueOf(${parmString}.getId()));
        return ${parmString}Dao.delete${voClassName}ByObj(${parmString});
    }
    public Long delete${voClassName}ByIdList(List<<#assign i=0><#list columnList as column><#if column.key><#assign i=i+1><#if (i>1)>,</#if>${column.propertyType} </#if></#list>> ids){
    	for(<#assign i=0><#list columnList as column><#if column.key><#assign i=i+1><#if (i>1)>,</#if>${column.propertyType} </#if></#list> id:ids){
    		queryCache.deleteObj("Maintenance_${voClassName}",String.valueOf(id));
    	}
    	return ${parmString}Dao.delete${voClassName}ByIdList(ids);
    }
    /**
     * 修改${voClassName}
     * @param ${parmString} 要修改的${voClassName}
     */
    public Long update${voClassName}(${voClassName} ${parmString}){
    	queryCache.deleteObj("Maintenance_${voClassName}",String.valueOf(${parmString}.getId()));
     	return ${parmString}Dao.update${voClassName}(${parmString});
    }
    
    public Long update${voClassName}ByObj(${voClassName} ${parmString}){
    	queryCache.deleteObj("Maintenance_${voClassName}",String.valueOf(${parmString}.getId()));
    	return ${parmString}Dao.update${voClassName}ByObj(${parmString});
    }
    
    public Long update${voClassName}ByObjAndConditions(${voClassName} s,${voClassName} c){
    	queryCache.deleteObj("Maintenance_${voClassName}",String.valueOf(s.getId()));
    	return ${parmString}Dao.update${voClassName}ByObjAndConditions(s,c);
    }
	public void batchUpdate${voClassName}(List<${voClassName}> records){
		for(${voClassName} ${parmString}:records){
			queryCache.deleteObj("Maintenance_${voClassName}",String.valueOf(${parmString}.getId()));
		}
		${parmString}Dao.batchUpdate${voClassName}(records);
	}
    /**
     * 根据id获取单个${voClassName}对象
<#list columnList as column>
    <#if column.key>
     * @param ${column.propertyName} 要查询的id
    </#if>
</#list>
     * @return ${voClassName}
     */
    
    public Integer get${voClassName}CountByObj(${voClassName} ${parmString}){
    	return ${parmString}Dao.get${voClassName}CountByObj(${parmString});
    }
    

    public ${voClassName} get${voClassName}ById(<#assign i=0><#list columnList as column><#if column.key><#assign i=i+1><#if (i>1)>,</#if>${column.propertyType} ${column.propertyName}</#if></#list>){
    	${voClassName} ${parmString} =(${voClassName})queryCache.queryObj("Maintenance_${voClassName}", String.valueOf(<#assign i=0><#list columnList as column><#if column.key><#assign i=i+1><#if (i>1)>,</#if> ${column.propertyName}</#if></#list>));
    	if(${parmString}==null){
    		${parmString} =${parmString}Dao.get${voClassName}ById(<#assign i=0><#list columnList as column><#if column.key><#assign i=i+1><#if (i>1)>,</#if> ${column.propertyName}</#if></#list>);
    		if(${parmString}!=null){
    			queryCache.setObj("Maintenance_${voClassName}",String.valueOf(<#assign i=0><#list columnList as column><#if column.key><#assign i=i+1><#if (i>1)>,</#if> ${column.propertyName}</#if></#list>),${parmString});
    		}
    	}
    	return ${parmString};
    }
    public ${voClassName} get${voClassName}ByObj(${voClassName} ${parmString}) {
        Long id = ${parmString}.getId();
    	if(id==null){
    		id= ${parmString}Dao.get${voClassName}IdByObj(${parmString});
    	}
    	return this.get${voClassName}ById(id);
    }
    
    public List<${voClassName}> get${voClassName}ListByObj(${voClassName} ${parmString}){
        List<Long> ids =  ${parmString}Dao.get${voClassName}IdListByObj(${parmString});
        List<${voClassName}> list = new ArrayList<${voClassName}>();
    	if(ids!=null&&ids.size()>0){
    		for(Long id:ids){
    			list.add(this.get${voClassName}ById(id));
    		}
    	}
    	return list;
    }
    public List<${voClassName}> get${voClassName}ListPage(${voClassName} ${parmString},Integer offset,Integer limit){
        List<Long> ids =  ${parmString}Dao.get${voClassName}IdListPage(${parmString},offset,limit);
        List<${voClassName}> list = new ArrayList<${voClassName}>();
    	if(ids!=null&&ids.size()>0){
    		for(Long id:ids){
    			list.add(this.get${voClassName}ById(id));
    		}
    	}
    	return list;
    }
    public List<${voClassName}> get${voClassName}Page(${voClassName} ${parmString},PageEntity page) {
        List<Long> ids =  ${parmString}Dao.get${voClassName}IdPage(${parmString},page);
        List<${voClassName}> list = new ArrayList<${voClassName}>();
    	if(ids!=null&&ids.size()>0){
    		for(Long id:ids){
    			list.add(this.get${voClassName}ById(id));
    		}
    	}
    	return list;
    }
}
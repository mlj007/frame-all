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
    /**
     * 添加${voClassName}
     * @param ${parmString} 要添加的${voClassName}
     * @return id
     */
    public Long add${voClassName}(${voClassName} ${parmString}){
    	Long res = ${parmString}Dao.add${voClassName}(${parmString});
    	return res;
    }
	public Long insert${voClassName}(${voClassName} ${parmString}){
		Long res = ${parmString}Dao.insert${voClassName}(${parmString});
		
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
    	return ${parmString}Dao.delete${voClassName}ById(<#assign i=0><#list columnList as column><#if column.key><#assign i=i+1><#if (i>1)></#if>${column.propertyName}</#if></#list>);
    }
	public Long delete${voClassName}ByObj(${voClassName} ${parmString}){
        return ${parmString}Dao.delete${voClassName}ByObj(${parmString});
    }
    public Long delete${voClassName}ByIdList(List<<#assign i=0><#list columnList as column><#if column.key><#assign i=i+1><#if (i>1)>,</#if>${column.propertyType} </#if></#list>> ids){
    	
    	return ${parmString}Dao.delete${voClassName}ByIdList(ids);
    }
    /**
     * 修改${voClassName}
     * @param ${parmString} 要修改的${voClassName}
     */
    public Long update${voClassName}(${voClassName} ${parmString}){
     	return ${parmString}Dao.update${voClassName}(${parmString});
    }
    
    public Long update${voClassName}ByObj(${voClassName} ${parmString}){
    	return ${parmString}Dao.update${voClassName}ByObj(${parmString});
    }
    
    public Long update${voClassName}ByObjAndConditions(${voClassName} s,${voClassName} c){
    	return ${parmString}Dao.update${voClassName}ByObjAndConditions(s,c);
    }
	public void batchUpdate${voClassName}(List<${voClassName}> records){
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
    	return ${parmString}Dao.get${voClassName}ById(<#assign i=0><#list columnList as column><#if column.key><#assign i=i+1><#if (i>1)>,</#if> ${column.propertyName}</#if></#list>);
    }
    
     public ${voClassName} get${voClassName}ByObj(${voClassName} ${parmString}) {
        return ${parmString}Dao.get${voClassName}ByObj(${parmString});
    }


    
    public List<${voClassName}> get${voClassName}ListByObj(${voClassName} ${parmString}){
        return ${parmString}Dao.get${voClassName}ListByObj(${parmString});
    }
    public List<${voClassName}> get${voClassName}ListPage(${voClassName} ${parmString},Integer offset,Integer limit){
        return ${parmString}Dao.get${voClassName}ListPage(${parmString},offset,limit);
    }
    
    public List<${voClassName}> get${voClassName}Page(${voClassName} ${parmString},PageEntity page) {
        return ${parmString}Dao.get${voClassName}Page(${parmString},page);
    }
}
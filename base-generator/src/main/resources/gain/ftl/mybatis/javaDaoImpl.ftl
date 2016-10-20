package ${packageName?replace('/','.')}.dao.impl.${mypackageName};

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import ${packageName?replace('/','.')}.model.${mypackageName}.${voClassName};
import ${packageName?replace('/','.')}.dao.${mypackageName}.${voClassName}Dao;
import org.springframework.stereotype.Repository;
import com.chexun.base.framework.core.dao.impl.common.GenericDaoImpl;
import com.chexun.base.common.util.BeanMapConvertor;
import com.chexun.base.framework.core.entity.PageEntity;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
/**
 *
 * ${voClassName}
 * User:
 * Date: ${date?string("yyyy-MM-dd")}
 */
 @Repository("${parmString}Dao")
public class ${voClassName}DaoImpl extends GenericDaoImpl implements ${voClassName}Dao{

    public Long add${voClassName}(${voClassName} ${parmString}) {
        return this.insert("${packageName?replace('/','.')}.model.${mypackageName}.${voClassName}Mapper.create${voClassName}",${parmString});
    }
	public Long insert${voClassName}(${voClassName} ${parmString}){
		return this.insert("${packageName?replace('/','.')}.model.${mypackageName}.${voClassName}Mapper.insert${voClassName}",${parmString});
	}
    public Long delete${voClassName}ById(<#assign i=0><#list columnList as column><#if column.key><#assign i=i+1><#if (i>1)>,</#if>${column.propertyType} ${column.propertyName}</#if></#list>){
        <#if keys?size != 0 && keys?size != 1>
        java.util.Map keyMap = new java.util.HashMap();
            <#list columnList as column>
                <#if column.key>
        keyMap.put("${column.propertyName}",${column.propertyName});
                </#if>
            </#list>
        return this.delete("${packageName?replace('/','.')}.model.${mypackageName}.${voClassName}Mapper.delete${voClassName}ById",keyMap);
        <#else>
            <#list columnList as column>
                <#if column.key>
        return this.delete("${packageName?replace('/','.')}.model.${mypackageName}.${voClassName}Mapper.delete${voClassName}ById",${column.propertyName});
                </#if>
            </#list>
        </#if>
    }
    public Long delete${voClassName}ByObj(${voClassName} ${parmString}){
        return this.delete("${packageName?replace('/','.')}.model.${mypackageName}.${voClassName}Mapper.delete${voClassName}ByObj",${parmString});
    }
    
	public Long delete${voClassName}ByIdList(List<<#assign i=0><#list columnList as column><#if column.key><#assign i=i+1><#if (i>1)>,</#if>${column.propertyType} </#if></#list>> ids){
		return this.delete("${packageName?replace('/','.')}.model.${mypackageName}.${voClassName}Mapper.delete${voClassName}ByIdList",ids);
	}
    public Long update${voClassName}(${voClassName} ${parmString}) {
        return this.update("${packageName?replace('/','.')}.model.${mypackageName}.${voClassName}Mapper.update${voClassName}",${parmString});
    }
    
    public Long update${voClassName}ByObj(${voClassName} ${parmString}){
    	return this.update("${packageName?replace('/','.')}.model.${mypackageName}.${voClassName}Mapper.update${voClassName}ByObj",${parmString});
    }
    
    public Long update${voClassName}ByObjAndConditions(${voClassName} s,${voClassName} c){
    	Map<String,${voClassName}> map = new HashMap<String,${voClassName}>();
    	map.put("s",s);
    	map.put("c",c);
    	return this.update("${packageName?replace('/','.')}.model.${mypackageName}.${voClassName}Mapper.update${voClassName}ByObjAndConditions",map);
    }
	public void batchUpdate${voClassName}(List<${voClassName}> records){
		this.update("${packageName?replace('/','.')}.model.${mypackageName}.${voClassName}Mapper.batchUpdate${voClassName}",records);
	}
    public ${voClassName} get${voClassName}ById(<#assign i=0><#list columnList as column><#if column.key><#assign i=i+1><#if (i>1)>,</#if>${column.propertyType} ${column.propertyName}</#if></#list>) {
        <#if keys?size != 0 && keys?size != 1>
        java.util.Map keyMap = new java.util.HashMap();
            <#list columnList as column>
                <#if column.key>
        keyMap.put("${column.propertyName}",${column.propertyName});
                </#if>
            </#list>
        return this.selectOne("${packageName?replace('/','.')}.model.${mypackageName}.${voClassName}Mapper.get${voClassName}ById",keyMap);
        <#else>
            <#list columnList as column>
                <#if column.key>
        return this.selectOne("${packageName?replace('/','.')}.model.${mypackageName}.${voClassName}Mapper.get${voClassName}ById",${column.propertyName});
                </#if>
            </#list>
        </#if>
    }
    
    public ${voClassName} get${voClassName}ByObj(${voClassName} ${parmString}) {
        return this.selectOne("${packageName?replace('/','.')}.model.${mypackageName}.${voClassName}Mapper.get${voClassName}ByObj",${parmString});
    }

    
    public List<${voClassName}> get${voClassName}ListByObj(${voClassName} ${parmString}){
        return this.selectList("${packageName?replace('/','.')}.model.${mypackageName}.${voClassName}Mapper.get${voClassName}ListByObj",${parmString});
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<${voClassName}> get${voClassName}ListPage(${voClassName} ${parmString},Integer offset,Integer limit){
    	try {
			Map map = BeanMapConvertor.convertBean(${parmString});
			map.put("offset",offset);
    		map.put("limit",limit);
        	return this.selectList("${packageName?replace('/','.')}.model.${mypackageName}.${voClassName}Mapper.get${voClassName}ListByMap",map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return null;
    }
    public Integer get${voClassName}CountByObj(${voClassName} ${parmString}){
    	return this.selectOne("${packageName?replace('/','.')}.model.${mypackageName}.${voClassName}Mapper.get${voClassName}CountByObj", ${parmString});
    }
    
    public List<${voClassName}> get${voClassName}Page(${voClassName} ${parmString},PageEntity page) {
        Integer objectscount = get${voClassName}CountByObj(${parmString});
        if (objectscount == null || objectscount == 0) {
            page.setTotalResultSize(0);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return null;
        } else {
            page.setTotalResultSize(objectscount);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return get${voClassName}ListPage(${parmString},(page.getCurrentPage() - 1) * page.getPageSize(),page.getPageSize());
        }
    }
    
    
    
     /**
    *以下为缓存查询用
    */
    public Long get${voClassName}IdByObj(${voClassName} ${parmString}) {
        return this.selectOne("${packageName?replace('/','.')}.model.${mypackageName}.${voClassName}Mapper.get${voClassName}IdByObj",${parmString});
    }

    public List<Long> get${voClassName}IdList(${voClassName} ${parmString}) {
        return this.selectList("${packageName?replace('/','.')}.model.${mypackageName}.${voClassName}Mapper.get${voClassName}IdList",${parmString});
    }
    
    public List<Long> get${voClassName}IdListByObj(${voClassName} ${parmString}){
        return this.selectList("${packageName?replace('/','.')}.model.${mypackageName}.${voClassName}Mapper.get${voClassName}IdListByObj",${parmString});
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<Long> get${voClassName}IdListPage(${voClassName} ${parmString},Integer offset,Integer limit){
    	try {
			Map map = BeanMapConvertor.convertBean(${parmString});
			map.put("offset",offset);
    		map.put("limit",limit);
        	return this.selectList("${packageName?replace('/','.')}.model.${mypackageName}.${voClassName}Mapper.get${voClassName}IdListByMap",map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return null;
    }
    public List<Long> get${voClassName}IdPage(${voClassName} ${parmString},PageEntity page) {
        Integer objectscount = get${voClassName}CountByObj(${parmString});
        if (objectscount == null || objectscount == 0) {
            page.setTotalResultSize(0);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return null;
        } else {
            page.setTotalResultSize(objectscount);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return get${voClassName}IdListPage(${parmString},(page.getCurrentPage() - 1) * page.getPageSize(),page.getPageSize());
        }
    }
}

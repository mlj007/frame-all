package ${packageName?replace('/','.')}.dao.${mypackageName};
import java.util.List;
import ${packageName?replace('/','.')}.model.${mypackageName}.${voClassName};
import com.chexun.base.framework.core.entity.PageEntity;
/**
 * ${voClassName}管理接口
 * User: 
 * Date: ${date?string("yyyy-MM-dd")}
 */
public interface ${voClassName}Dao {

    /**
     * 添加${voClassName}
     * @param ${parmString} 要添加的${voClassName}
     * @return id
     */
    public Long add${voClassName}(${voClassName} ${parmString});
	public Long insert${voClassName}(${voClassName} ${parmString});
    /**
     * 根据id删除一个${voClassName}
<#list columnList as column>
    <#if column.key>
     * @param ${column.propertyName} 要删除的id
    </#if>
</#list>
     */
    public Long delete${voClassName}ById(<#assign i=0><#list columnList as column><#if column.key><#assign i=i+1><#if (i>1)>,</#if>${column.propertyType} ${column.propertyName}</#if></#list>);
    
    public Long delete${voClassName}ByObj(${voClassName} ${parmString});

	public Long delete${voClassName}ByIdList(List<<#assign i=0><#list columnList as column><#if column.key><#assign i=i+1><#if (i>1)>,</#if>${column.propertyType} </#if></#list>> ids);

    /**
     * 修改${voClassName}
     * @param ${parmString} 要修改的${voClassName}
     */
    public Long update${voClassName}(${voClassName} ${parmString});
    
    public Long update${voClassName}ByObj(${voClassName} ${parmString});
    
    public Long update${voClassName}ByObjAndConditions(${voClassName} s,${voClassName} c);
    public void batchUpdate${voClassName}(List<${voClassName}> records);

    /**
     * 根据id获取单个${voClassName}对象
<#list columnList as column>
    <#if column.key>
     * @param ${column.propertyName} 要查询的id
    </#if>
</#list>
     * @return ${voClassName}
     */
    public ${voClassName} get${voClassName}ById(<#assign i=0><#list columnList as column><#if column.key><#assign i=i+1><#if (i>1)>,</#if>${column.propertyType} ${column.propertyName}</#if></#list>);
	public ${voClassName} get${voClassName}ByObj(${voClassName} ${parmString});
    /**
     * 根据条件获取${voClassName}列表
     * @param ${parmString} 查询条件
     * @return List<${voClassName}>
     */
    public List<${voClassName}> get${voClassName}ListByObj(${voClassName} ${parmString});
    public List<${voClassName}> get${voClassName}ListPage(${voClassName} ${parmString},Integer offset,Integer limit);
    public Integer get${voClassName}CountByObj(${voClassName} ${parmString});
    public List<${voClassName}> get${voClassName}Page(${voClassName} ${parmString},PageEntity page);
    
    /**
    *以下为缓存查询用
    */
    public Long get${voClassName}IdByObj(${voClassName} ${parmString});
    /**
     * 根据条件获取${voClassName}列表
     * @param ${parmString} 查询条件
     * @return List<${voClassName}>
     */
    public List<Long> get${voClassName}IdList(${voClassName} ${parmString});
    public List<Long> get${voClassName}IdListByObj(${voClassName} ${parmString});
    public List<Long> get${voClassName}IdListPage(${voClassName} ${parmString},Integer offset,Integer limit);
    public List<Long> get${voClassName}IdPage(${voClassName} ${parmString},PageEntity page);
}
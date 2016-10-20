<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" 
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<#assign one = 1>
<mapper namespace="${packageName?replace('/','.')}.model.${mypackageName}.${voClassName}Mapper">
 	<!-- 以下两个<cache>标签二选一,第一个可以输出日志,第二个不输出日志 -->  
    <!--<cache type="org.mybatis.caches.ehcache.LoggingEhcache"/>  -->
    <!--<cache type="org.mybatis.caches.ehcache.EhcacheCache"/>   -->
    <resultMap id="${voClassName}Result" type="${packageName?replace('/','.')}.model.${mypackageName}.${voClassName}">
        <#list columnList as column>
        <result property="${column.propertyName}" column="${column.columnName}"/>
        </#list>
    </resultMap>

    <sql id="${tableName}_columns">
        <#list columnList as column>
        ${tableName}.${column.columnName}<#if column_index+1< columnList?size>,</#if>
        </#list>
    </sql>
    
    <sql id="${tableName}_where_conditions">
    	<trim prefix="where" prefixOverrides="and|or">
        <#list columnList as column>
        <#if column.propertyType=='String'>
        
        <if test="${column.propertyName} != null and ${column.propertyName} != ''">
        	and ${column.columnName}=#${"{"}${column.propertyName}${"}"} 
        </if>
        <#else>
        <if test="${column.propertyName} != null">
        	and ${column.columnName}=#${"{"}${column.propertyName}${"}"} 
        </if>
        </#if>
        </#list>
        </trim>
    </sql>
    
    <sql id="${tableName}_set_conditions">
    	<set>
        <#list columnList as column>
        <if test="${column.propertyName} != null">
        	${column.columnName}=#${"{"}${column.propertyName}${"}"},
        </if>
        </#list>
        <#if keys?size!=0>
        	<#assign i=0>
            <#list columnList as column><#if column.key><#assign i=i+1><#if (i>1)> , </#if>${column.columnName} = ${column.columnName}</#if></#list>
        </#if>
        </set>
    </sql>

    <sql id="${tableName}_properties">
        <#list columnList as column>
        #${"{"}${column.propertyName}${"}"}<#if column_index+1< columnList?size>,</#if>
        </#list>
    </sql>

    <insert id="create${voClassName}" parameterType="${packageName?replace('/','.')}.model.${mypackageName}.${voClassName}" keyColumn="id" keyProperty="id" useGeneratedKeys="true" >
        insert INTO ${tableName} (<include refid="${tableName}_columns"/>) VALUES (<include refid="${tableName}_properties"/>)
    </insert>
	
	<insert id="insert${voClassName}" parameterType="${packageName?replace('/','.')}.model.${mypackageName}.${voClassName}" keyColumn="id" keyProperty="id" useGeneratedKeys="true">
    	insert into ${tableName}
    	<trim prefix="(" suffix=")" suffixOverrides="," >
    		<#list columnList as column>
    			${'<if'} test="${column.propertyName} != null" >
        			${column.columnName}<#if column_index+1< columnList?size>,</#if>
    			${'</if>'}
    		</#list>
    	</trim>
    	<trim prefix="values (" suffix=")" suffixOverrides="," >
    		<#list columnList as column>
    			${'<if'} test="${column.propertyName} != null" >
        		${'#{'}${column.propertyName}${'}'}<#if column_index+1< columnList?size>,</#if>
    		${'</if>'}
    		</#list>
    	</trim>
  	</insert>
	
    <delete id="delete${voClassName}ById" parameterType="<#if keys?size != 0 && keys?size != 1>java.util.Map<#else><#list columnList as column><#if column.key>${column.propertyType}</#if></#list></#if>">
        delete from ${tableName}
        <#if keys?size!=0>
        where<#assign i=0>
            <#list columnList as column>
            	<#if column.key>
            		<#assign i=i+1>
            		<#if (i>1)> AND </#if>${column.columnName} = #${"{"}<#if keys?size != 1>
            			${column.propertyName}
            		<#else>
            			value
            		</#if>
            		${"}"}
            	</#if>
            </#list>
        </#if>
    </delete>
    
    <delete id="delete${voClassName}ByObj" parameterType="${packageName?replace('/','.')}.model.${mypackageName}.${voClassName}">
        delete from ${tableName}
        <include refid="${tableName}_where_conditions"/>
    </delete>
    
  	<delete id="delete${voClassName}ByIdList" parameterType="java.util.List">
    	delete from ${tableName} 
		<#if keys?size!=0>
        	where<#assign i=0>
            <#list columnList as column><#if column.key><#assign i=i+1><#if (i>1)> AND </#if>${column.columnName}</#if></#list>
    		IN 
    		${'<foreach'} item="item" index="index" collection="list" open="(" separator="," close=")">
				${'#{'}item${'}'}
			${'</foreach>'}
    	</#if> 
    </delete>

    <update id="update${voClassName}" parameterType="${packageName?replace('/','.')}.model.${mypackageName}.${voClassName}">
        update ${tableName} SET
        <#list columnList as column>
            ${column.columnName} = #${"{"}${column.propertyName}${"}"}<#if column_index+1< columnList?size>,</#if>
        </#list>
        <#if keys?size!=0>
        where<#assign i=0>
            <#list columnList as column><#if column.key><#assign i=i+1><#if (i>1)> AND </#if>${column.columnName} = #${"{"}${column.propertyName}${"}"}</#if></#list>
        </#if>
    </update>
    
    <update id="update${voClassName}ByObj" parameterType="${packageName?replace('/','.')}.model.${mypackageName}.${voClassName}">
        update ${tableName} 
        <include refid="${tableName}_set_conditions"/>
        <#if keys?size!=0>
        where<#assign i=0>
            <#list columnList as column><#if column.key><#assign i=i+1><#if (i>1)> AND </#if>${column.columnName} = #${"{"}${column.propertyName}${"}"}</#if></#list>
        </#if>
    </update>
    
    <update id="update${voClassName}ByObjAndConditions" parameterType="java.util.HashMap">
        update ${tableName} 
        <set>
        <#list columnList as column>
        <if test="s.${column.propertyName} != null">
        	${column.columnName}=#${"{"}s.${column.propertyName}${"}"},
        </if>
        </#list>
        </set>
        <where>
        <#list columnList as column>
        <if test="c.${column.propertyName} != null">
        	${column.columnName}=#${"{"}c.${column.propertyName}${"}"} AND 
        </if>
        </#list>
       (status!=2)
        </where>
    </update>
    
    <update id="batchUpdate${voClassName}" parameterType="java.util.List">
		update ${tableName}
		<trim prefix="set" suffixOverrides=",">
		 <#list columnList as column>
      	 	<trim prefix="${column.columnName}=CASE" suffix="end,">
				${'<foreach'} collection="list" item="i" index="index">
					${'<if'} test="i.${column.propertyName}!=null">
						WHEN <#if keys?size!=0>
        				<#assign i=0>
            			<#list columnList as column><#if column.key><#assign i=i+1><#if (i>1)> AND </#if>${column.columnName} = #${"{"}i.${column.propertyName}${"}"}</#if></#list>
        				</#if> THEN ${'#{'}i.${column.propertyName}${'}'}
					${'</if>'}
				${'</foreach>'}
			</trim>
    	</#list>
		</trim>
		where
		${'<foreach'} collection="list" separator="or" item="i" index="index">
			<#if keys?size!=0>
        		<#assign i=0>
            	(<#list columnList as column><#if column.key><#assign i=i+1><#if (i>1)> AND </#if>${column.columnName} = #${"{"}i.${column.propertyName}${"}"}</#if></#list>)
        	</#if>
		${'</foreach>'}
	</update>
    
    

    <select id="get${voClassName}ById" resultMap="${voClassName}Result" parameterType="<#if keys?size != 0 && keys?size != 1>java.util.Map<#else><#list columnList as column><#if column.key>${column.propertyType}</#if></#list></#if>">
        select
        <include refid="${tableName}_columns"/>
        from ${tableName}
       <#if keys?size!=0>
        where<#assign i=0>
            <#list columnList as column><#if column.key><#assign i=i+1><#if (i>1)> AND </#if>${column.columnName} = #${"{"}<#if keys?size != 1>${column.propertyName}<#else>value</#if>${"}"}</#if></#list>
        </#if>
    </select>
    
    <select id="get${voClassName}ByObj" resultMap="${voClassName}Result" parameterType="${packageName?replace('/','.')}.model.${mypackageName}.${voClassName}">
        select
        <include refid="${tableName}_columns"/>
        from ${tableName}
        <include refid="${tableName}_where_conditions"/>
    </select>
	
    
    <select id="get${voClassName}ListByObj" resultMap="${voClassName}Result"  parameterType="${packageName?replace('/','.')}.model.${mypackageName}.${voClassName}">
        select
        <include refid="${tableName}_columns"/>
        from ${tableName} 
        <include refid="${tableName}_where_conditions"/>
    </select>
    
    <select id="get${voClassName}ListByMap" resultMap="${voClassName}Result"  parameterType="java.util.HashMap">
        select
        <include refid="${tableName}_columns"/>
        from ${tableName} 
        <include refid="${tableName}_where_conditions"/>
        limit #${"{"}offset,jdbcType=INTEGER${"}"},#${"{"}limit,jdbcType=INTEGER${"}"} 
    </select>
    
    <select id="get${voClassName}CountByObj" resultType="int" parameterType="${packageName?replace('/','.')}.model.${mypackageName}.${voClassName}">
        select count(1) from ${tableName}
        <include refid="${tableName}_where_conditions"/>
    </select>
    
    
    
    <select id="get${voClassName}IdByObj" resultType="Long" parameterType="${packageName?replace('/','.')}.model.${mypackageName}.${voClassName}">
        select <#if keys?size!=0>  <#assign i=0>  <#list columnList as column> <#if column.key><#assign i=i+1> <#if (i>1)>,</#if>${column.columnName} </#if> </#list> </#if>
        from ${tableName}
        <include refid="${tableName}_where_conditions"/>
    </select>
	
    <select id="get${voClassName}IdList" resultType="Long" parameterType="${packageName?replace('/','.')}.model.${mypackageName}.${voClassName}">
       select <#if keys?size!=0>  <#assign i=0>  <#list columnList as column> <#if column.key><#assign i=i+1> <#if (i>1)>,</#if>${column.columnName} </#if> </#list> </#if> from ${tableName} where 1=1 
        <#assign i=0>
        <#list columnList as column><#if column.key><#assign i=i+1><#if (i>1)> AND </#if>${column.columnName} = #${"{"}<#if keys?size != 1>${column.propertyName}<#else>value</#if>${"}"}</#if></#list>
    </select>
    
    <select id="get${voClassName}IdListByObj" resultType="Long"  parameterType="${packageName?replace('/','.')}.model.${mypackageName}.${voClassName}">
       select <#if keys?size!=0>  <#assign i=0>  <#list columnList as column> <#if column.key><#assign i=i+1> <#if (i>1)>,</#if>${column.columnName} </#if> </#list> </#if> from ${tableName} 
        <include refid="${tableName}_where_conditions"/>
    </select>
    
    <select id="get${voClassName}IdListByMap" resultType="Long"  parameterType="java.util.HashMap">
        select <#if keys?size!=0>  <#assign i=0>  <#list columnList as column> <#if column.key><#assign i=i+1> <#if (i>1)>,</#if>${column.columnName} </#if> </#list> </#if> from ${tableName} 
        <include refid="${tableName}_where_conditions"/>
        limit #${"{"}offset,jdbcType=INTEGER${"}"},#${"{"}limit,jdbcType=INTEGER${"}"} 
    </select>
</mapper>
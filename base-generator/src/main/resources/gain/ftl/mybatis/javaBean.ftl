package ${packageName?replace('/','.')}.model.${mypackageName};

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ${voClassName} implements Serializable{
	public static final int DEFAULT_STATUS = 0;// 默认状态
	public static final int FREEZE_STATUS = 1;// 冻结
	public static final int DELETE_STATUS = 2;// 伪删除
    <#list columnList as column>
    private ${column.propertyType} ${column.propertyName};
    </#list>
}

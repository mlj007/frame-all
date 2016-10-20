package com.chexun.generation.gain;

import java.sql.Types;

public class ColumnVO {
    private String columnName;
    private int columnType;
    private boolean key = false;

    public String getColumnName() {
        return columnName.toLowerCase();
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public int getColumnType() {
        return columnType;
    }

    public void setColumnType(int columnType) {
        this.columnType = columnType;
    }

    public String getPropertyType() {
        String propertyType;
        switch (columnType) {
        case Types.BIGINT:
            propertyType = "Long";
            break;
        case Types.BIT:
            propertyType = "Integer";
            break;
        case Types.BOOLEAN:
            propertyType = "Boolean";
            break;
        case Types.CHAR:
            propertyType = "Character";
            break;
        case Types.DATE:
            propertyType = "java.util.Date";
            break;
        case Types.DECIMAL:
            propertyType = "java.math.BigDecimal";
            break;
        case Types.DOUBLE:
            propertyType = "Double";
            break;
        case Types.FLOAT:
            propertyType = "Float";
            break;
        case Types.INTEGER:
            propertyType = "Long";
            break;
        // case Types.LONGNVARCHAR:
        // propertyType = "String";
        // break;
        case Types.LONGVARCHAR:
            propertyType = "String";
            break;
        // case Types.NCHAR:
        // propertyType = "String";
        // break;
        case Types.NUMERIC:
            propertyType = "java.math.BigDecimal";
            break;
        // case Types.NVARCHAR:
        // propertyType = "String";
        // break;
        case Types.SMALLINT:
            propertyType = "Integer";
            break;
        case Types.TIME:
            propertyType = "java.util.Date";
            break;
        case Types.TIMESTAMP:
            propertyType = "java.util.Date";
            break;
        case Types.TINYINT:
            propertyType = "Integer";
            break;
        case Types.VARCHAR:
            propertyType = "String";
            break;
        default:
            propertyType = "java.lang.Object";
        }
        return propertyType;

    }

    public String getReturnType() {
        String propertyType;
        switch (columnType) {
        case Types.BIGINT:
            propertyType = "java.lang.Long";// java.lang.Integer
            break;
        case Types.BIT:
            propertyType = "java.lang.Integer";
            break;
        case Types.BOOLEAN:
            propertyType = "java.lang.Boolean";
            break;
        case Types.CHAR:
            propertyType = "java.lang.Character";
            break;
        case Types.DATE:
            propertyType = "java.util.Date";
            break;
        case Types.DECIMAL:
            propertyType = "java.math.BigDecimal";
            break;
        case Types.DOUBLE:
            propertyType = "java.math.BigDecimal";// java.lang.Double
            break;
        case Types.FLOAT:
            propertyType = "java.lang.Float";
            break;
        case Types.INTEGER:
            propertyType = "java.lang.Long";
            break;
        // case Types.LONGNVARCHAR:
        // propertyType = "java.lang.String";
        // break;
        case Types.LONGVARCHAR:
            propertyType = "java.lang.String";
            break;
        // case Types.NCHAR:
        // propertyType = "java.lang.String";
        // break;
        case Types.NUMERIC:
            propertyType = "java.math.BigDecimal";
            break;
        // case Types.NVARCHAR:
        // propertyType = "java.lang.String";
        // break;
        case Types.SMALLINT:
            propertyType = "java.lang.Integer";
            break;
        case Types.TIME:
            propertyType = "java.util.Date";
            break;
        case Types.TIMESTAMP:
            propertyType = "java.util.Date";
            break;
        case Types.TINYINT:
            propertyType = "java.lang.Integer";
            break;
        case Types.VARCHAR:
            propertyType = "java.lang.String";
            break;
        default:
            propertyType = "java.lang.Object";
        }
        return propertyType;

    }

    public String getPropertyName() {
        String cn = columnName.toLowerCase();
        String[] cn_split = cn.split("_");
        StringBuffer sbColumnName = new StringBuffer();
        for (String cn_sub : cn_split) {
            sbColumnName.append(cn_sub.substring(0, 1).toUpperCase()).append(
                    cn_sub.substring(1));
        }
        String proName = sbColumnName.toString();
        proName = proName.substring(0, 1).toLowerCase() + proName.substring(1);
        return proName;
    }

    public String getMethodName() {
        return getPropertyName().substring(0, 1).toUpperCase()
                + getPropertyName().substring(1);
    }

    public static String columnPropertyName(String columnName) {
        String cn = columnName.toLowerCase();
        String[] cn_split = cn.split("_");
        StringBuffer sbColumnName = new StringBuffer();
        for (String cn_sub : cn_split) {
            sbColumnName.append(cn_sub.substring(0, 1).toUpperCase()).append(
                    cn_sub.substring(1));
        }
        String proName = sbColumnName.toString();
        proName = proName.substring(0, 1).toLowerCase() + proName.substring(1);
        return proName;
    }

    public boolean isKey() {
        return key;
    }

    public void setKey(boolean key) {
        this.key = key;
    }
}

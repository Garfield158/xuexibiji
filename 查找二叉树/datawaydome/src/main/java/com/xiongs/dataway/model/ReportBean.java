package com.xiongs.dataway.model;

/**
 * @version v1.0
 * @description:
 * @author: xiong_s on 2020/5/11 17:40
 */
public class ReportBean {
    private String quotaCnName;
    private String dataType;
    private Object value;

    public String getQuotaCnName() {
        return quotaCnName;
    }

    public void setQuotaCnName(String quotaCnName) {
        this.quotaCnName = quotaCnName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}

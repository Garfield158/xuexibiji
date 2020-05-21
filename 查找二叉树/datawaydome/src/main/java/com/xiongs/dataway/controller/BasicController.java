package com.xiongs.dataway.controller;

import com.xiongs.dataway.simpledata.SimpleData;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Table;
import javax.sql.DataSource;

public class BasicController<T> {
    @Autowired
    DataSource dataSource;
    public T insert(T t){
        return t;
    }
    public T select(T t){
        Class<?> aClass = t.getClass();
        Table table = aClass.getAnnotation(Table.class);
        if (table!=null){
            String name = table.name();
        }
        String querySql = "";
        SimpleData<T> simpleData = new SimpleData<>();
        return simpleData.getData(querySql,t);
    }
}

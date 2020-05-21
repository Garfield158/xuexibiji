package com.xiongs.dataway.simpledata;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SimpleData<T> {
    private DataSource dataSource = null;

    public T getData(String querySql,T t) {
        try {
            Class<?> aClass = t.getClass();
            Field[] declaredFields = aClass.getDeclaredFields();
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(querySql);
            for (Field f : declaredFields) {
                String name = f.getName();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

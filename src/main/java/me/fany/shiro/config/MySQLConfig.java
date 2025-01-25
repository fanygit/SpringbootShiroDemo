package me.fany.shiro.config;

import org.hibernate.dialect.MySQL8Dialect;

public class MySQLConfig extends MySQL8Dialect {
    @Override
    public String getTableTypeString() {
        return "ENGINE=InnoDB DEFAULT CHARSET=utf8";
    }
}

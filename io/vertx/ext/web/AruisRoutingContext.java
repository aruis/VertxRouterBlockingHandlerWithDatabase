package io.vertx.ext.web;

import groovy.sql.Sql;

/**
 * Created by liurui on 2017/3/16.
 */
public interface AruisRoutingContext extends RoutingContext {
    //我这里用的Sql，是groovy封装的数据库访问类，在这里替换成自己业务需要使用的数据源即可
    void setDb(Sql db);

    Sql getDb();
}

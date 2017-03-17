package io.vertx.ext.web.impl;

import groovy.sql.Sql;
import io.vertx.ext.web.AruisRoutingContext;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.RoutingContext;

/**
 * Created by liurui on 2017/3/16.
 */
public class AruisRoutingContextDecorator extends RoutingContextDecorator implements AruisRoutingContext {

    //我这里用的Sql，是groovy封装的数据库访问类，在这里替换成自己业务需要使用的数据源即可
    private Sql db;

    AruisRoutingContextDecorator(Route currentRoute, RoutingContext context, Sql db) {
        super(currentRoute, context);
        this.db = db;
    }

    public void setDb(Sql db) {
        this.db = db;
    }

    public Sql getDb() {
        return db;
    }
}

package io.vertx.ext.web.impl;

import groovy.sql.Sql;
import io.vertx.core.Handler;
import io.vertx.ext.web.AruisRoutingContext;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.RoutingContext;

import java.util.Objects;

/**
 * Created by liurui on 2017/3/16.
 */
public class AruisBlockingHandlerDecorator implements Handler<RoutingContext> {

    private boolean ordered;
    private final Handler<AruisRoutingContext> decoratedHandler;

    AruisBlockingHandlerDecorator(Handler<AruisRoutingContext> decoratedHandler, boolean ordered) {
        Objects.requireNonNull(decoratedHandler);
        this.decoratedHandler = decoratedHandler;
        this.ordered = ordered;
    }

    @Override
    public void handle(RoutingContext context) {
        Route currentRoute = context.currentRoute();
        Sql db =  Sql.newInstance("jdbc:mysql://localhost:3306/test", "root",
                "xxxxxxx", "com.mysql.jdbc.Driver"); //我这里用的Sql，是groovy封装的数据库访问类，在这里替换成自己业务需要使用的数据源即可
        context.vertx().executeBlocking(fut -> {
            decoratedHandler.handle(new AruisRoutingContextDecorator(currentRoute, context, db));
            fut.complete();
        }, ordered, res -> {
            db.close();//当路由处理完成后，收回数据库资源
            if (res.failed()) {
                // This means an exception was thrown from the blocking handler
                context.fail(res.cause());
            }
        });
    }

}

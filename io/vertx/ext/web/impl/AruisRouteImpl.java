package io.vertx.ext.web.impl;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.AruisRoutingContext;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.RoutingContext;

/**
 * Created by liurui on 2017/3/16.
 */
class AruisRouteImpl extends RouteImpl implements io.vertx.ext.web.AruisRoute {


    AruisRouteImpl(RouterImpl router, int order, HttpMethod method, String path) {
        super(router, order, method, path);
    }

    @Override
    public Route dbBlockingHandler(Handler<AruisRoutingContext> contextHandler) {
        return super.handler(new AruisBlockingHandlerDecorator(contextHandler, false));//此处 ordered指定为false了，用以实现路由的并行响应。
    }


}

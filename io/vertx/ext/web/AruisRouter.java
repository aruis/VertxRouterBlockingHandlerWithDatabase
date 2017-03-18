package io.vertx.ext.web.impl;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Route;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by liurui on 2017/3/16.
 */
public class AruisRouterImpl extends RouterImpl implements io.vertx.ext.web.AruisRouter {


    private final AtomicInteger orderSequence = new AtomicInteger();

    public AruisRouterImpl(Vertx vertx) {
        super(vertx);
    }


    @Override
    public Route route() {
        return new RouteImpl(this, orderSequence.getAndIncrement());
    }

    @Override
    public AruisRouteImpl route(HttpMethod method, String path) {
        return new AruisRouteImpl(this, orderSequence.getAndIncrement(), method, path);
    }

    /*
    * 此处只Override了 get、post两个方法，如果需要更多的方法支持指定AruisRouteImpl，可以继续Override其他方法
    * */

    @Override
    public AruisRouteImpl get(String path) {
        return route(HttpMethod.GET, path);
    }

    @Override
    public AruisRouteImpl post(String path) {
        return route(HttpMethod.POST, path);
    }
}

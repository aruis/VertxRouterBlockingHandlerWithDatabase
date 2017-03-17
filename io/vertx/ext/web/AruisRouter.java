package io.vertx.ext.web;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.impl.AruisRouterImpl;

/**
 * Created by liurui on 2017/3/16.
 */
public interface AruisRouter extends Router {

    static AruisRouter router(Vertx vertx) {
        return new AruisRouterImpl(vertx);
    }

    @Override
    Route route(HttpMethod method, String path);

    /*
    * 此处只Override了 get、post两个方法，如果需要更多的方法支持指定AruisRouteImpl，可以继续Override其他方法
    * */

    @Override
    AruisRoute get(String path);

    @Override
    AruisRoute post(String path);
}

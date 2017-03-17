package io.vertx.ext.web;

import io.vertx.core.Handler;

/**
 * Created by liurui on 2017/3/16.
 */
public interface AruisRoute extends Route {
    Route dbBlockingHandler(Handler<AruisRoutingContext> requestHandler);
}

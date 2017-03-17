# VertxRouterBlockingHandlerWithDatabase
Vertx3.x Router BlockingHandlerWithDatabase

### 1.功能


在Vertx 现有Router类的基础上增加**dbBlockingHandler**方法，功能比原先blockingHandler方法做了两点改进

a.可以从routingContext中调用一个预先封装的**getDb**方法，用来做数据库相关的操作，同时路由使用之后，该资源可以自动释放。


b.底层调用blockingHandler时，默认第二参数**ordered**为**false**，以保证route可以被并行执行。

### 2.DEMO
```java
import groovy.json.JsonBuilder;
import groovy.sql.Sql;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.AruisRouter;

import java.sql.SQLException;
import java.util.Map;

/**
 * Created by liurui on 2017/3/16.
 */
public class Demo {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        HttpServer server = vertx.createHttpServer();


        AruisRouter router = AruisRouter.router(vertx);//使用自己封装的路由

        router.get("/a").dbBlockingHandler(routingContext -> {

            Sql db = routingContext.getDb();//从routingContext，中拿到自己的数据库访问类
            HttpServerResponse response = routingContext.response();
            response.putHeader("content-type", "text/plain");

            Map user = null;
            try {
                user = db.firstRow("select * from c_user;");//groovy Sql类功能，仅供参考
            } catch (SQLException e) {
                e.printStackTrace();
            }

            response.end(new JsonBuilder(user).toString());//groovy JsonBuilder类功能，仅供参考
        });

        server.requestHandler(router::accept).listen(8080);
    }
}

```


### 3.使用方法

直接把代码拷贝至自己的项目src相关目录下即可，可以根据代码中的注释，细化自己的数据库访问属性及方法


### 4.欢迎交流 Fork Star





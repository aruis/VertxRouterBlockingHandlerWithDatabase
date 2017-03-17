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

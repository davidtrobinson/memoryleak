package com.vertx.micrometer;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServer;

public class HttpVerticle extends AbstractVerticle {

  @Override
  public void start(Future<Void> startFuture) {

    HttpServer server = vertx.createHttpServer();

    server.requestHandler(request ->
        vertx.eventBus().<String>send("hello", "world", res ->
            request.response().end(res.result().body())
        )
    ).listen(8080);

    startFuture.complete();
  }
}

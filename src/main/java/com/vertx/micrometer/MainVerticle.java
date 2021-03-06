package com.vertx.micrometer;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class MainVerticle extends AbstractVerticle {

  @Override
  public void start(Future<Void> startFuture) {

    vertx.deployVerticle(HttpVerticle.class.getName());
    vertx.deployVerticle(DBVerticle.class.getName());

    startFuture.complete();
  }
}

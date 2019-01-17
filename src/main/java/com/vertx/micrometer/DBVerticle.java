package com.vertx.micrometer;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class DBVerticle extends AbstractVerticle {

  @Override
  public void start(Future<Void> startFuture) throws Exception {

    vertx.eventBus().<String>consumer("hello", message ->
        message.reply(String.format("Hello %s", message.body()))
    );

    startFuture.complete();
  }

}

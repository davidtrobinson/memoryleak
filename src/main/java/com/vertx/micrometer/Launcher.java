package com.vertx.micrometer;

import io.vertx.core.VertxOptions;
import io.vertx.micrometer.MicrometerMetricsOptions;
import io.vertx.micrometer.VertxInfluxDbOptions;

public class Launcher extends io.vertx.core.Launcher {

  public static void main(String... args) {

    new Launcher().dispatch(args);
  }

  /**
   * Called by vertx to allow us to configure options before verticles are started.
   *
   * @param options passed in by vertx
   */
  @Override
  public void beforeStartingVertx(VertxOptions options) {
    super.beforeStartingVertx(options);

    options.setMetricsOptions(new MicrometerMetricsOptions()
        .setInfluxDbOptions(new VertxInfluxDbOptions()
            .setEnabled(true))
        .setEnabled(true));
  }

}

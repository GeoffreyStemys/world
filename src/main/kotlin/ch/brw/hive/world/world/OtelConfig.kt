package ch.brw.hive.world.world

import io.opentelemetry.api.OpenTelemetry
import io.opentelemetry.api.metrics.Meter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OtelConfig(
  private val otel: OpenTelemetry
) {
  
  @Bean
  fun meter(): Meter = otel.getMeter("toto.custom")
  
}

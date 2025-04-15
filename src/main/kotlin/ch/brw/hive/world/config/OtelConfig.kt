package ch.brw.hive.world.config

import io.opentelemetry.api.OpenTelemetry
import io.opentelemetry.api.metrics.Meter
import io.opentelemetry.exporter.otlp.http.logs.OtlpHttpLogRecordExporter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.Base64

@Configuration
class OtelConfig(
  private val otel: OpenTelemetry
) {
  
  @Bean
  fun meter(): Meter = otel.getMeter("toto.custom")

}

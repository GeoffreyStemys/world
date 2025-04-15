package ch.brw.hive.world.world

import io.opentelemetry.api.OpenTelemetry
import io.opentelemetry.api.metrics.Meter
import io.opentelemetry.exporter.otlp.http.logs.OtlpHttpLogRecordExporter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import io.opentelemetry.sdk.logs.SdkLoggerProvider
import io.opentelemetry.sdk.logs.export.BatchLogRecordProcessor
import java.util.Base64

@Configuration
class OtelConfig(
  private val otel: OpenTelemetry
) {
  
  @Bean
  fun meter(): Meter = otel.getMeter("toto.custom")


  @Bean
  fun configureOtelWithBasicAuth(): SdkLoggerProvider {
    val username = "toto"
    val password = "toto"
    val basicAuthHeader = "Basic " + Base64.getEncoder().encodeToString("$username:$password".toByteArray())

    val logExporter = OtlpHttpLogRecordExporter.builder()
      .addHeader("Authorization", basicAuthHeader)
      .setEndpoint("https://plaz-monitoring-loki.sandbox.cloud.stemys.ch/otlp/v1/logs")
      .build()

    val logProcessor = BatchLogRecordProcessor.builder(logExporter).build()

    val loggerProvider = SdkLoggerProvider.builder()
      .addLogRecordProcessor(logProcessor)
      .build()
    return loggerProvider
  }
}

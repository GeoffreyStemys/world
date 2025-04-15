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


//  @Bean
//  fun configureOtelWithBasicAuth(): OtlpHttpLogRecordExporter {
//    val username = "toto"
//    val password = "toto"
//     val basicAuthHeader = "Basic " + Base64.getEncoder().encodeToString("$username:$password".toByteArray())
//    val basicAuthHeader = "Basic $username:$password"
//
//    val logExporter = OtlpHttpLogRecordExporter.builder()
//      .addHeader("Authorization", basicAuthHeader)
//      .setEndpoint("https://plaz-monitoring-loki.sandbox.cloud.stemys.ch/otlp/v1/logs")
//      .build()
//    return logExporter
//
//    val logProcessor = BatchLogRecordProcessor.builder(logExporter).build()
//
//    val loggerProvider = SdkLoggerProvider.builder()
//      .addLogRecordProcessor(logProcessor)
//      .build()
//    return loggerProvider
//  }

}

package ch.brw.hive.world.service

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.core.env.ConfigurableEnvironment
import org.springframework.http.ResponseEntity
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.lang.Thread.sleep
import java.util.concurrent.TimeUnit.SECONDS
import kotlin.random.Random


@RestController
@RequestMapping(value = ["/hello"])
class WorldController(
    val environment: ConfigurableEnvironment
) {

    private val logger = KotlinLogging.logger {}

    @GetMapping(value = ["/"])
    fun putConfigUpdate(): ResponseEntity<String> {
        val message = randomLogs()
        return ResponseEntity.ok(message + "\n")
    }

    // @EventListener(ApplicationReadyEvent::class)
    @Scheduled(initialDelay = 5, fixedDelay = 10, timeUnit = SECONDS)
    fun doStuffAfterStartup() {

        val management = environment
            .propertySources.map { it.source }
            .filterIsInstance<Map<String, Any>>()
            .flatMap { it.entries }
            .filter { it.key.contains("management") }

        val otel = environment
            .propertySources.map { it.source }
            .filterIsInstance<Map<String, Any>>()
            .flatMap { it.entries }
            .filter { it.key.contains("otel") }

        otel.firstOrNull { it.key.contains("logs.end") }?.value

        while (true) {
            randomLogs()
            sleep(1500)
        }

    }

    private fun randomLogs() = when (Random.nextInt(0, 3)) {
        2 -> "\uD83D\uDEA8 error plaz basic auth".also { logger.error { it } }
        1 -> "⚠\uFE0F warn plaz basic auth".also { logger.warn { it } }
        0 -> "\uD83E\uDD73 Congratulation plaz basic auth".also { logger.info { it } }
        else -> "\uD83E\uDEB2 bug plaz basic auth".also { logger.debug { it } }
    }

}

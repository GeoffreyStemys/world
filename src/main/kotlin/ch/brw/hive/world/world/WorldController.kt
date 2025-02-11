package ch.brw.hive.world.world

import io.github.oshai.kotlinlogging.KotlinLogging
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
class WorldController {

    private val logger = KotlinLogging.logger {}

    @GetMapping(value = ["/"])
    fun putConfigUpdate(): ResponseEntity<String> {
        val message = when (Random.nextInt(0, 3)) {
            2 -> "\uD83D\uDEA8 error world".also { logger.error { it }}
            1 ->  "⚠\uFE0F warn world".also { logger.warn { it }}
            0 ->  "\uD83E\uDD73 Congratulation world".also { logger.info { it }}
            else ->  "\uD83E\uDEB2 bug world".also { logger.debug { it }}
        }
        return ResponseEntity.ok(message + "\n")
    }

    // @EventListener(ApplicationReadyEvent::class)
    @Scheduled(initialDelay = 5, fixedDelay = 10, timeUnit = SECONDS)
    fun doStuffAfterStartup() {
        while (true) {
            when (Random.nextInt(0, 3)) {
                2 -> "\uD83D\uDEA8 error world".also { logger.error { it }}
                1 ->  "⚠\uFE0F warn world".also { logger.warn { it }}
                0 ->  "\uD83E\uDD73 congratulation world".also { logger.info { it }}
                else ->  "\uD83E\uDEB2 bug world".also { logger.debug { it }}
            }
            sleep(1500)
        }
    }

}

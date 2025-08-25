//package ch.brw.hive.world.service
//
//import org.springframework.stereotype.Component
//import org.springframework.jms.annotation.JmsListener
//import io.github.oshai.kotlinlogging.KotlinLogging
//import org.springframework.boot.context.event.ApplicationReadyEvent
//import org.springframework.context.event.EventListener
//
//
//@Component
//class EventDemo {
//
//  val logger = KotlinLogging.logger {}
//
//  // https://activemq.apache.org/components/artemis/documentation/latest/perf-tuning.html#tuning-the-vm
//  // https://activemq.apache.org/components/artemis/documentation/latest/paging.html#configuration-2
//
//  @EventListener(ApplicationReadyEvent::class)
//  fun setupConfigWhenAppReady() {
//    Fusesource.fusesource()
//  }
//
//  @JmsListener(
//    destination = "debug",
//    containerFactory = "containerFactory"
//  )
//  fun listener(message: String) {
//    logger.info{ "Received message: $message" }
//  }
//
//}

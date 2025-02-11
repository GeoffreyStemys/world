//package ch.brw.hive.hello.world
//
//import mu.KotlinLogging.logger
//import org.fusesource.mqtt.client.MQTT
//import org.fusesource.mqtt.client.QoS
//
//object Fusesource {
//
//    val logger = logger {}
//
//    fun fusesource() {
//        // Create a new MQTT connection to the broker
//        // We are not setting the client ID the broker will pick one for us
//        val mqtt = MQTT().apply {
////         setHost("ssl://debug-artemis.sandbox.cloud.stemys.ch:8883"); setUserName("admin"); setPassword("admin")
//            setHost("ssl://rabbitmq-service.sandbox.cloud.stemys.ch:8883"); setUserName("user"); setPassword("admin")
//        }
//        val connection = mqtt
//            // .futureConnection()
//            .blockingConnection()
//            .apply { connect() }
//        logger.info("Connected to Artemis")
//
//        // Subscribe to topics
//        // connection.subscribe(arrayOf(Topic(itemTopic, QoS.AT_LEAST_ONCE)))
//
//        logger.info("#####################")
//        logger.info("Sending messages ...")
//        repeat(100) {
//            connection.publish("toto", "This is message $it".toByteArray(), QoS.AT_MOST_ONCE, false)
//        }
//        logger.info("Sending messages over")
//        logger.info("#####################")
//        connection.disconnect()
////        logger.info(connection.receive(5, SECONDS).payload.toString())
////        logger.info(connection.receive(5, SECONDS).payload.toString())
////        logger.info(connection.receive(5, SECONDS).payload.toString())
//    }
//
//}
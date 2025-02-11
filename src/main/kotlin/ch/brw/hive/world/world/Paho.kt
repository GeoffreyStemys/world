//package ch.brw.hive.hello.world
//
//import mu.KotlinLogging.logger
//import org.eclipse.paho.client.mqttv3.MqttClient
//import org.eclipse.paho.client.mqttv3.MqttConnectOptions
//import org.eclipse.paho.client.mqttv3.MqttMessage
//import kotlin.random.Random
//
//
//object Paho {
//    val logger = logger {}
//
//    fun randomString(length: Int) = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
//        .let { str -> (0..length).map { str[Random.nextInt(str.length)] }.joinToString("") }
//    val number = randomString(10)
//    val birthTopic get() = "SYSTEM/DIRECTOR/${number}/MQTT/BIRTH"
//    val itemTopic get() = "DIRECTOR/${number}/director/AGENT/ITEM_VALUE_UPDATE"
//
//    data class Mqtt(val host: String, val username: String, val password: String)
//    val creds = Mqtt(host = "ssl://debug-artemis.sandbox.cloud.stemys.ch", username = "admin", password = "admin")
//    val SIMU = "simu"
//
//    fun connectOptions() = MqttConnectOptions().apply {
//        userName = creds.username
//        password = creds.password.toCharArray()
//        maxInflight = 5000
//        keepAliveInterval = 0
//        isAutomaticReconnect = true
//        isCleanSession = false
//    }
//
////  private val birthCertificate = run {
////    KuraBirthPayload
////      .KuraBirthPayloadBuilder()
////      .apply {
////        withModelId(SIMU)
////        withOs("Linux")
////        withOsArch("amd64")
////        withSerialNumber(SIMU)
////        withConnectionIp("0.0.0.0")
////        withDisplayName(number)
////      }
////      .build()
////      .let { CloudPayloadGZipEncoder(CloudPayloadProtoBufEncoderImpl(it)).bytes }
////      .let { MqttMessage(it).apply { qos = 0 } }
////  }
//
//    fun paho() {
//        logger.info { "#####################################" }
//        logger.info { "EventManager start emmit messages ..." }
//
//        val mqttClient = MqttClient(creds.host, number).let { it.connect(connectOptions()); it }
//        // mqttClient.publish(birthTopic, birthCertificate)
//
//        val message = """
//      {
//        "hello": "debug",
//        "event": "test"
//      }
//      """.trimIndent()
////      .let { MqttPayload(nowDate(), THING_MACHINE, it, STRING.name) }
////      .let { CloudPayloadGZipEncoder(CloudPayloadProtoBufEncoderImpl(it)).bytes }
//            .let { MqttMessage(it.toByteArray()).apply { qos = 0 } }
//
//        val numberOfMessages = 100000
//        repeat(numberOfMessages) { mqttClient.publish(itemTopic, message) }
//
//        logger.info { "emmit messages over" }
//        logger.info { "#####################################" }
//    }
//
//}

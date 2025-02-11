//package ch.brw.hive.hello.world
//
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.jms.config.DefaultJmsListenerContainerFactory
//import com.rabbitmq.jms.admin.RMQConnectionFactory
//
//@Configuration
//class JmsConfig {
//
//    @Bean
//    fun jmsConnectionFactory(): RMQConnectionFactory = RMQConnectionFactory().apply {
//        uri = "amqps://rabbitmq-service.sandbox.cloud.stemys.ch:5671"
//        username = "admin"
//        password = "admin"
//    }
//
//    @Bean("containerFactory")
//    fun containerFactory(connectionFactory: RMQConnectionFactory) =
//        DefaultJmsListenerContainerFactory()
//        .apply {
//            setConnectionFactory(connectionFactory)
//        }
//
//}

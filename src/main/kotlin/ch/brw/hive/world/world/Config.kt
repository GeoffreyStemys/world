package ch.brw.hive.world.world

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableScheduling
@EnableWebSecurity
@EnableAsync
@EnableMethodSecurity(prePostEnabled = true)
class Config {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain = http
        .authorizeHttpRequests { it.anyRequest().permitAll() }
//    .oauth2ResourceServer { it.jwt(Customizer.withDefaults()) }
        .csrf { it.disable() }
        .build()

}

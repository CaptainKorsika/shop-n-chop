package de.shopnchop.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService
import org.springframework.security.web.server.SecurityWebFilterChain

@Configuration
@EnableWebFluxSecurity
class SecurityConfig {

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    fun actuatorWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        return http.build()
    }

    @Bean
    fun generalWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain = http.build()

    @Bean
    fun userDetailsService(): MapReactiveUserDetailsService = MapReactiveUserDetailsService(mutableMapOf())
}
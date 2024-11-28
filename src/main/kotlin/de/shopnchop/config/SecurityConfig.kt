package de.shopnchop.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.reactive.CorsConfigurationSource
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebFluxSecurity
class SecurityConfig {

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    fun actuatorWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        return http.csrf{ it.disable() }.cors{ it.configurationSource(corsConfigurationSource()) }.build()
    }

    @Bean
    fun generalWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        return http.csrf{ it.disable() }.cors{ it.configurationSource(corsConfigurationSource()) }.build()
    }

    @Bean
    fun userDetailsService(): MapReactiveUserDetailsService = MapReactiveUserDetailsService(mutableMapOf())

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val corsConfiguration = CorsConfiguration()
        corsConfiguration.allowCredentials = false
        corsConfiguration.addAllowedOrigin("*")
        corsConfiguration.addAllowedHeader("*")
        corsConfiguration.allowedOrigins = listOf("*")
        corsConfiguration.allowedMethods = listOf("GET", "POST", "PUT", "DELETE")
        corsConfiguration.allowedHeaders = listOf("*")
        val source = UrlBasedCorsConfigurationSource()

        source.registerCorsConfiguration("/**", corsConfiguration)
        return source
    }


}
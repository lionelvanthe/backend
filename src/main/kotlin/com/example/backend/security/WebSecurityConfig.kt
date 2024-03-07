package com.example.backend.security

import com.example.backend.security.jwt.AuthEntryPointJwt
import com.example.backend.security.jwt.AuthTokenFilter
import com.example.backend.security.jwt.JwtUtils
import com.example.backend.security.services.UserDetailsServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer.AuthorizationManagerRequestMatcherRegistry
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration //@EnableWebSecurity
@EnableMethodSecurity //(securedEnabled = true,
//jsr250Enabled = true,
//prePostEnabled = true) // by default

class WebSecurityConfig(
    @Autowired
    var userDetailsService: UserDetailsServiceImpl,
    @Autowired
    private val unauthorizedHandler: AuthEntryPointJwt,
    @Autowired
    private val jwtUtils: JwtUtils) {

    @Bean
    fun authenticationJwtTokenFilter(): AuthTokenFilter {
        return AuthTokenFilter(jwtUtils, userDetailsService)
    }

    @Bean
    fun authenticationProvider(): DaoAuthenticationProvider {
        val authProvider = DaoAuthenticationProvider()
        authProvider.setUserDetailsService(userDetailsService)
        authProvider.setPasswordEncoder(passwordEncoder())
        return authProvider
    }

    @Bean
    @Throws(Exception::class)
    fun authenticationManager(authConfig: AuthenticationConfiguration): AuthenticationManager {
        return authConfig.authenticationManager
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf { obj: CsrfConfigurer<HttpSecurity> -> obj.disable() }
                .exceptionHandling { exception: ExceptionHandlingConfigurer<HttpSecurity?> ->
                    println(exception.toString())
                    exception.authenticationEntryPoint(unauthorizedHandler)
                }
                .sessionManagement { session: SessionManagementConfigurer<HttpSecurity?> -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
                .authorizeHttpRequests(Customizer { auth ->
                    auth
                            .requestMatchers("/api/auth/**").permitAll()
                            .requestMatchers("/api/test/**").permitAll()
                            .requestMatchers("/api/parent/**").permitAll()
                            .requestMatchers("/api/class/**").permitAll()
                            .anyRequest().authenticated()
                }
                )
        http.authenticationProvider(authenticationProvider())
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter::class.java)
        return http.build()
    }
}

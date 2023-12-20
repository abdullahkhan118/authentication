package com.horux.authentication.jwt.config

import com.horux.authentication.config.AuthenticationApplicationConfig
import com.horux.authentication.exceptions.UserNotFoundException
import com.horux.authentication.jwt.models.JwtAuthenticatedUser
import com.horux.authentication.jwt.repositories.JwtAuthenticateUserRepository
import com.horux.authentication.jwt.requestInterceptor.JwtAuthenticationInterceptor
import com.horux.authentication.jwt.services.JwtService
import com.horux.authentication.jwt.tokens.JwtAuthentication
import com.horux.authentication.repositories.AuthenticateUserRepository
import com.horux.authentication.requestInterceptor.AuthenticationInterceptor
import org.springframework.context.annotation.Bean
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import kotlin.jvm.Throws

@Component
interface JwtApplicationConfig<T : JwtAuthenticatedUser<ID>, ID> :
    AuthenticationApplicationConfig<T, ID> {

    override val authenticateUserRepository: JwtAuthenticateUserRepository<T,ID>

    @Bean
    @Throws(UserNotFoundException::class)
    override fun userDetailsService(): UserDetailsService = super.userDetailsService()

    @Bean
    fun passwordEncoder(): PasswordEncoder

}
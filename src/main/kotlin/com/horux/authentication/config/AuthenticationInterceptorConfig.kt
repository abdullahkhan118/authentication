package com.horux.authentication.config

import com.horux.authentication.models.AuthenticatedUser
import com.horux.authentication.requestInterceptor.AuthenticationInterceptor
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

interface AuthenticationInterceptorConfig<T : AuthenticatedUser<ID>,ID>: WebMvcConfigurer {

    val authenticationInterceptor: AuthenticationInterceptor<T,ID>

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(authenticationInterceptor)
    }
}
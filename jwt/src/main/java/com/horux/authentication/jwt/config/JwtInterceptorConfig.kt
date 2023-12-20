package com.horux.authentication.jwt.config

import com.horux.authentication.config.AuthenticationInterceptorConfig
import com.horux.authentication.jwt.models.JwtAuthenticatedUser
import com.horux.authentication.requestInterceptor.AuthenticationInterceptor
import com.horux.authentication.tokens.Authentication
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.lang.Exception
import java.util.UUID

interface JwtInterceptorConfig<T : JwtAuthenticatedUser<ID>, ID> : AuthenticationInterceptorConfig<T,ID>
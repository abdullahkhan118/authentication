package com.horux.authentication.requestInterceptor

import com.horux.authentication.models.AuthenticatedUser
import com.horux.authentication.tokens.Authentication
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import java.lang.Exception

interface AuthenticationInterceptor<T : AuthenticatedUser<ID>,ID> : HandlerInterceptor {

    val authentication: Authentication<T,ID>

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean

    override fun postHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any, modelAndView: ModelAndView?)

    override fun afterCompletion(request: HttpServletRequest, response: HttpServletResponse, handler: Any, ex: Exception?)
}
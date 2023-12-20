package com.horux.authentication.jwt.requestInterceptor

import com.horux.authentication.jwt.models.JwtAuthenticatedUser
import com.horux.authentication.jwt.tokens.JwtAuthentication
import com.horux.authentication.requestInterceptor.AuthenticationInterceptor
import com.horux.authentication.tokens.Authentication
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import java.lang.Exception

interface JwtAuthenticationInterceptor<T : JwtAuthenticatedUser<ID>, ID> :
    AuthenticationInterceptor<T, ID> {

    override val authentication: Authentication<T, ID>
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        return if (request.requestURI.contains("/auth/")) return true else authentication.isAuthenticated(
            request,
            response
        )
    }

    override fun postHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        modelAndView: ModelAndView?
    ) {

    }

    override fun afterCompletion(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        ex: Exception?
    ) {

    }
}
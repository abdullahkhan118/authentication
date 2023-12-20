package com.horux.authentication.jwt.filters

import com.horux.authentication.filters.AuthenticationFilter
import com.horux.authentication.jwt.models.JwtAuthenticatedUser
import com.horux.authentication.jwt.tokens.JwtAuthentication
import com.horux.authentication.tokens.Authentication
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.filter.OncePerRequestFilter

abstract class JwtAuthenticationFilter<T: JwtAuthenticatedUser<ID>,ID>(protected val jwtAuthentication: JwtAuthentication<T,ID>) :
        AuthenticationFilter<T,ID>(jwtAuthentication) {

    override fun doFilterInternal(
            request: HttpServletRequest,
            response: HttpServletResponse,
            filterChain: FilterChain
    ) {
        if (jwtAuthentication.isAuthenticated(request, response)) {
            try {
                filterChain.doFilter(request, response)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}
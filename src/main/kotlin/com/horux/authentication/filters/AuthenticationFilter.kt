package com.horux.authentication.filters

import com.horux.authentication.models.AuthenticatedUser
import com.horux.authentication.tokens.Authentication
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.filter.OncePerRequestFilter

abstract class AuthenticationFilter<T: AuthenticatedUser<ID>,ID>(protected val authentication: Authentication<T,ID>) :
        OncePerRequestFilter() {

    override fun doFilterInternal(
            request: HttpServletRequest,
            response: HttpServletResponse,
            filterChain: FilterChain
    ) {
        if (authentication.isAuthenticated(request, response)) {
            try {
                filterChain.doFilter(request, response)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}
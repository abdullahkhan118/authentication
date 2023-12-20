package com.horux.authentication.tokens

import com.horux.authentication.constants.Headers
import com.horux.authentication.models.AuthenticatedUser
import com.horux.authentication.services.AuthService
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource

interface Authentication<T : AuthenticatedUser<ID>,ID> {

    val authService: AuthService<T,ID>

    fun isAuthenticated(
            request: HttpServletRequest,
            response: HttpServletResponse
    ): Boolean {
        val bearer = "Bearer "
        val authHeader = request.getHeader(Headers.HEADER_AUTHORIZATION)
        if (!hasAuthHeader(bearer, authHeader, request, response)) {
            return false
        }
        val jwt = authHeader.substringAfter(bearer)
        val username = authService.extractUsername(jwt)
        if (SecurityContextHolder.getContext().authentication == null) {
            val userDetails = authService.loadUserByUsername(username)
            if (authService.isTokenValid(jwt, userDetails)) {
                val authToken = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
                authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authToken
            } else return false
        }
        return true
    }

    fun hasAuthHeader(
            bearer: String,
            authHeader: String?,
            request: HttpServletRequest,
            response: HttpServletResponse,
    ): Boolean {
        if (authHeader.isNullOrBlank()) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "${Headers.HEADER_AUTHORIZATION} must be provided")
            return false
        }
        if (!authHeader.startsWith(bearer)) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "${Headers.HEADER_AUTHORIZATION} must start with $bearer")
            return false
        }
        return true
    }

}
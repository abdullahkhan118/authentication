package com.horux.authentication.jwt.services

import com.horux.authentication.exceptions.ExpirationDateRequiredException
import com.fasterxml.jackson.databind.ObjectMapper
import com.horux.authentication.jwt.models.JwtAuthenticatedUser
import com.horux.authentication.jwt.models.JwtUser
import com.horux.authentication.jwt.repositories.JwtAuthenticateUserRepository
import com.horux.authentication.models.AuthenticatedUser
import com.horux.authentication.repositories.AuthenticateUserRepository
import com.horux.authentication.services.AuthService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import java.util.*
import javax.annotation.PropertyKey
import javax.crypto.SecretKey

open class JwtService<ID>(
    override val secretKey: String,
    override val userDetailsService: UserDetailsService,
    override val repository: JwtAuthenticateUserRepository<ID>
) : AuthService<ID> {

    override fun authenticateUser(username: String): Boolean {
        val authenticatedUser = super.authenticateUser(username)
        if (authenticatedUser) {
            with(repository.findByUsername(username).get()) {
                val user = if (expirationAt < System.currentTimeMillis()) repository.save(
                    copy(issueAt = System.currentTimeMillis())
                ) else this
                generateToken(user, user.expirationAt)
            }
        }
        return authenticatedUser
    }

    fun generateToken(user: UserDetails, expirationTime: Long?): String = Jwts
        .builder()
        .setSubject(user.username)
        .setExpiration(
            if (user is JwtUser) Date(user.expirationAt) else Date(
                expirationTime ?: throw ExpirationDateRequiredException()
            )
        )
        .setIssuedAt(if (user is JwtUser) Date(user.issueAt) else Date(System.currentTimeMillis()))
        .setClaims(ObjectMapper().convertValue(user, HashMap::class.java).mapKeys {
            it.value.toString()
        })
        .signWith(signingKey(), SignatureAlgorithm.HS256)
        .compact()

    fun isTokenValid(token: String, user: UserDetails) =
        extractUsername(token).contentEquals(user.username) && !isExpired(token)

    private fun <T> extractClaims(token: String, claimsResolver: (Claims) -> T): T =
        claimsResolver.invoke(extractAllClaims(token))

    fun extractUsername(token: String): String = extractClaims(token) {
        it.subject
    }

    fun extractExpirationDate(token: String): Date = extractClaims(token) {
        it.expiration
    }

    fun isExpired(token: String) = extractExpirationDate(token).before(Date())

    private fun extractAllClaims(token: String) = Jwts
        .parserBuilder()
        .setSigningKey(signingKey())
        .build()
        .parseClaimsJwt(token)
        .body

    fun signingKey(): SecretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey))
}
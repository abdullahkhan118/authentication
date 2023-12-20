package com.horux.authentication.services

import com.horux.authentication.exceptions.ExpirationDateRequiredException
import com.fasterxml.jackson.databind.ObjectMapper
import com.horux.authentication.exceptions.UserNotFoundException
import com.horux.authentication.models.AuthenticatedUser
import com.horux.authentication.repositories.AuthenticateUserRepository
import com.horux.common.services.CrudServices
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import java.util.*
import javax.crypto.SecretKey
import kotlin.jvm.optionals.getOrElse

interface AuthService<ID> {

    val secretKey: String
    val userDetailsService: UserDetailsService
    val repository: AuthenticateUserRepository<AuthenticatedUser<ID>,ID>

    fun authenticateUser(username: String): Boolean = !repository.findByUsername(username).isEmpty


    @Throws(UserNotFoundException::class)
    fun loadUserByUsername(username: String): UserDetails = userDetailsService.loadUserByUsername(username)
//
//    fun generateToken(user: UserDetails, expirationTime: Long? = null): String = Jwts
//        .builder()
//        .setSubject(user.username)
//        .setExpiration(
//            if (user is AuthenticatedUser<*>) Date(user.expirationAt) else Date(
//                expirationTime ?: throw ExpirationDateRequiredException()
//            )
//        )
//        .setIssuedAt(if (user is AuthenticatedUser<*>) Date(user.issueAt) else Date(System.currentTimeMillis()))
//        .setClaims(ObjectMapper().convertValue(user, HashMap::class.java).mapKeys {
//            it.value.toString()
//        })
//        .signWith(signingKey(), SignatureAlgorithm.HS256)
//        .compact()
//
//    fun isTokenValid(token: String, user: UserDetails) =
//        extractUsername(token).contentEquals(user.username) && !isExpired(token)
//
//    private fun <T> extractClaims(token: String, claimsResolver: (Claims) -> T): T =
//        claimsResolver.invoke(extractAllClaims(token))
//
//    fun extractUsername(token: String): String = extractClaims(token) {
//        it.subject
//    }
//
//    fun extractExpirationDate(token: String): Date = extractClaims(token) {
//        it.expiration
//    }
//
//    fun isExpired(token: String) = extractExpirationDate(token).before(Date())
//
//    private fun extractAllClaims(token: String) = Jwts
//        .parserBuilder()
//        .setSigningKey(signingKey())
//        .build()
//        .parseClaimsJwt(token)
//        .body
//
//    fun signingKey(): SecretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey))
}
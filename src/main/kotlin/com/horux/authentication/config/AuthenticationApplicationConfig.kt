package com.horux.authentication.config

import com.horux.authentication.exceptions.UserNotFoundException
import com.horux.authentication.models.AuthenticatedUser
import com.horux.authentication.repositories.AuthenticateUserRepository
import org.springframework.context.annotation.Bean
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import kotlin.jvm.Throws
import kotlin.jvm.optionals.getOrElse

interface AuthenticationApplicationConfig<T : AuthenticatedUser<ID>,ID> {

    val authenticateUserRepository: AuthenticateUserRepository<T,ID>

    @Throws(UserNotFoundException::class)
    fun userDetailsService(): UserDetailsService = UserDetailsService {
        println("userDetailsService")
        return@UserDetailsService authenticateUserRepository.findByUsername(it).getOrElse {
            throw UserNotFoundException()
        }
    }

}
package com.horux.authentication.repositories

import com.horux.authentication.models.AuthenticatedUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface AuthenticateUserRepository<ID> : JpaRepository<AuthenticatedUser<ID>,ID> {
    fun findByUsername(username: String): Optional<AuthenticatedUser<ID>>
}
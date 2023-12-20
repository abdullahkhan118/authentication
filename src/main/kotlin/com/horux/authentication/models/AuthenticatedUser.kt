package com.horux.authentication.models

import com.horux.common.models.daos.CommonModel
import jakarta.persistence.MappedSuperclass
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

@MappedSuperclass
abstract class AuthenticatedUser<ID>(
    override val id: ID,
    private val username: String,
    open val issueAt: Long,
    open val expirationAt: Long,
    open val role: Role = Role.ROLE_USER
) : UserDetails, CommonModel<ID>() {
    abstract fun copy(issueAt: Long): AuthenticatedUser<ID>
}
package com.horux.authentication.jwt.models

import com.horux.authentication.models.AuthenticatedUser
import com.horux.authentication.models.Role
import org.springframework.security.core.GrantedAuthority
import java.util.concurrent.TimeUnit

open class JwtAuthenticatedUser<ID>(
    override val id: ID,
    override val username: String = "",
    override val password: String = "",
    override val issueAt: Long = System.currentTimeMillis(),
    override val expirationAt: Long = issueAt + TimeUnit.DAYS.toMillis(1),
    override val role : Role = Role.ROLE_USER,
    override val isAccountNonExpired: Boolean = true,
    override val isAccountNonLocked: Boolean = true,
    override val isCredentialsNonExpired: Boolean = true,
    override val isEnabled: Boolean = true
): JwtUser, AuthenticatedUser<ID>(id, username, issueAt, expirationAt, role) {

    override val grantedAuthorities: MutableList<GrantedAuthority>
        get() = role.grantedAuthorities

    override fun copy(issueAt: Long): JwtAuthenticatedUser<ID> = copy(issueAt = issueAt)

}
package com.horux.authentication.jwt.models

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import java.io.ObjectInputStream.GetField

interface JwtUser: UserDetails {

    val username: String
    val password: String
    val issueAt: Long
    val expirationAt: Long
    val grantedAuthorities: MutableList<GrantedAuthority>
    val isAccountNonExpired: Boolean
    val isAccountNonLocked: Boolean
    val isCredentialsNonExpired: Boolean
    val isEnabled: Boolean

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = grantedAuthorities
    override fun getPassword(): String = password
    override fun getUsername(): String = username
    override fun isAccountNonExpired(): Boolean = isAccountNonExpired
    override fun isAccountNonLocked(): Boolean = isAccountNonLocked
    override fun isCredentialsNonExpired(): Boolean = isCredentialsNonExpired
    override fun isEnabled(): Boolean = isEnabled

}
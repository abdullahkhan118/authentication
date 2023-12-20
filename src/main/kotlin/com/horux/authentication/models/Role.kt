package com.horux.authentication.models

import org.springframework.security.core.GrantedAuthority

enum class Role(val grantedAuthorities: MutableList<GrantedAuthority>) {

    ROLE_USER(mutableListOf(Authorities.CreateAuthority, Authorities.ReadAuthority, Authorities.UpdateAuthority))

}


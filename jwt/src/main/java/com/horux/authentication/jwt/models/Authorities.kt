package com.horux.authentication.jwt.models

import org.springframework.security.core.GrantedAuthority

sealed class Authorities: GrantedAuthority {

    object ReadAuthority: Authorities() {
        override fun getAuthority(): String = "read"

    }
    object UpdateAuthority: Authorities() {
        override fun getAuthority(): String = "update"

    }
    object CreateAuthority: Authorities() {
        override fun getAuthority(): String = "create"

    }
    object DeleteAuthority: Authorities() {
        override fun getAuthority(): String = "delete"

    }

}
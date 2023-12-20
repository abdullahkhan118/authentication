package com.horux.authentication.jwt.tokens

import com.horux.authentication.constants.Headers
import com.horux.authentication.jwt.models.JwtAuthenticatedUser
import com.horux.authentication.tokens.Authentication

interface JwtAuthentication<T : JwtAuthenticatedUser<ID>, ID> :
    Authentication<T, ID>
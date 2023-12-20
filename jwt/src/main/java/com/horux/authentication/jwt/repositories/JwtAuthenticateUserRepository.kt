package com.horux.authentication.jwt.repositories

import com.horux.authentication.repositories.AuthenticateUserRepository
import org.springframework.stereotype.Repository

@Repository
interface JwtAuthenticateUserRepository<ID> : AuthenticateUserRepository<ID>
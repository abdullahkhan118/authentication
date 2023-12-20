package com.horux.authentication.controllers

import com.horux.authentication.models.AuthenticationModel
import com.horux.authentication.models.AuthenticatedUser
import com.horux.authentication.services.AuthService
import com.horux.authentication.repositories.AuthenticateUserRepository

interface AuthController<ID> {

    val service: AuthService<ID>

//    fun authenticateUser(body: AuthenticationModel.Request): AuthenticationModel.Response =
//        AuthenticationModel.Response()

}
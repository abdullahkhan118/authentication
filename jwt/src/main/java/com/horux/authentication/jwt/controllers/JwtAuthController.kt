package com.horux.authentication.jwt.controllers

import com.horux.authentication.controllers.AuthController
import com.horux.authentication.jwt.services.JwtService


open class JwtAuthController<ID>(override val service: JwtService<ID>) : AuthController<ID>
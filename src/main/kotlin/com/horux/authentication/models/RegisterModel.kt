package com.horux.authentication.models

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size


open class RegisterModel {

    open class Request(
            @get:NotNull(message = "Username can not be null")
            @get:NotBlank(message = "Username can not be blank")
            @get:Size(min = 10, max = 50, message = "Username length must be between 10 and 50")
            val username: String,
            @get:NotNull(message = "Password can not be null")
            @get:NotBlank(message = "Password can not be blank")
            @get:Size(min = 10, max = 50, message = "Password length must be between 10 and 50")
            val password: String,
            val role: Role = Role.ROLE_USER
    )
    data class Response(val message: String)

}
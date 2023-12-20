package com.horux.authentication.models

import com.horux.common.models.CommonResponse
import com.horux.common.models.daos.CommonModel
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

sealed class AuthenticationModel {

    data class Request(
            @get:NotBlank(message = "Username can not be blank")
            @get:NotNull(message = "Username can not be null")
            @get:Size(min = 10, max = 50, message = "Username length must be between 10 and 50")
            val username: String,
            @get:NotBlank(message = "Password can not be blank")
            @get:NotNull(message = "Password can not be null")
            @get:Size(min = 10, max = 50, message = "Password length must be between 10 and 50")
            val password: String,
    )
    data class Response(val token: String)

}

package com.horux.authentication.exceptions

open class UserNotFoundException(message: String = "No such user exist"): RuntimeException(message)
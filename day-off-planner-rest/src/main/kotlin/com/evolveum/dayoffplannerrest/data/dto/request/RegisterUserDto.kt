package com.evolveum.dayoffplannerrest.data.dto.request

data class RegisterUserDto(
        var firstName: String = "",
        var lastName: String = "",
        var email: String = "",
        var password: String = ""
)
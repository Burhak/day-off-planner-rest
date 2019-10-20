package com.evolveum.day_off_planner_rest.security

class SecurityConstants {
    companion object {
        const val SECRET = "n2r5u8x/A%D*G-KaPdSgVkYp3s6v9yB&E(H+MbQeThWmZq4t7w!z%C*F-J@NcR_f"
        const val EXPIRATION_TIME_SECONDS = 12L * 60 * 60 // 12 hours
        const val TOKEN_TYPE = "Bearer"
        const val TOKEN_PREFIX = "$TOKEN_TYPE "
        const val HEADER_AUTHORIZATION = "Authorization"
        const val HEADER_CONTENT_TYPE = "Content-Type"
        const val CONTENT_TYPE = "application/json;charset=UTF-8"
        const val AUTHORITIES_KEY = "roles"
    }
}
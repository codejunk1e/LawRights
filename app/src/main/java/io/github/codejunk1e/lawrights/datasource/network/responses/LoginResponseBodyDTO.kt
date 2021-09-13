package io.github.codejunk1e.lawrights.datasource.network.responses

data class LoginResponseBodyDTO(
    val access_token: String,
    val expires_in: Int,
    val token_type: String
)
package com.faezolfp.dripcontrol.core.domain.model

data class Users(
    val id: Int = 0,
    val username: String? = null,
    val fullname: String? = null,
    val email: String? = null,
    val pasword: String? = null,
)

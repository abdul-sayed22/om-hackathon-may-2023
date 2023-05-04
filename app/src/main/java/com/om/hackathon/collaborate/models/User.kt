package com.om.hackathon.collaborate.models

data class User(
    val id: Int,
    val username: String,
    val password: String,
    val skills: List<String>
)

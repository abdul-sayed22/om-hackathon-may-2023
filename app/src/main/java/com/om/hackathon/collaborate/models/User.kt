package com.om.hackathon.collaborate.models

data class User(
    val id: Int,
    val username: String,
    val password: String,
    val name: String,
    val surname: String,
    val skills: List<String>
)

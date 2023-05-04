package com.om.hackathon.collaborate.models

data class Requirement(
    val name: String,
    val skills: String,
    val assignedToId: Int? = null
)

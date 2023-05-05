package com.om.hackathon.collaborate.models

import androidx.annotation.DrawableRes
import java.math.BigDecimal

data class Hustle(
    val id: Int,
    val ownerId: Int,
    val name: String,
    val description: String,
    val fundingRequirement: BigDecimal,
    val fundingInPocket: BigDecimal,
    val requirements: List<Requirement>,
    @DrawableRes val imageId: Int
)

package com.om.hackathon.collaborate.data

import com.om.hackathon.collaborate.models.Hustle
import com.om.hackathon.collaborate.models.Requirement
import com.om.hackathon.collaborate.models.User
import java.math.BigDecimal

object HustleDatabase {

    val users: List<User> = listOf(
        User(id = 1, username = "allan", password = "achang", skills = listOf("Logistics Manager")),
        User(id = 2, username = "kyle", password = "kwood", skills = listOf("Marketer")),
        User(id = 3, username = "abdul", password = "asayed", skills = listOf("Blacksmith"))
    )

    val hustles: List<Hustle> = listOf(
        Hustle(
            id = 1,
            ownerId = 1,
            name = "Allan's Triangular Scheme",
            description = "Starting a business to sell triangles to orchestras around the country",
            fundingRequirement = BigDecimal(150000),
            fundingInPocket = BigDecimal(70000),
            requirements = listOf(
                Requirement(name = "Accountant", skills = "Must be able to count", assignedToId = null),
                Requirement(name = "Marketer", skills = "POWERPOINT!", assignedToId = 2),
                Requirement(name = "Blacksmith", skills = "Must know basic geometry", assignedToId = 3)
            )
        ),
        Hustle(
            id = 1,
            ownerId = 2,
            name = "CaddyCash",
            description = "Mobile app payment platform to pay caddies",
            fundingRequirement = BigDecimal(120000),
            fundingInPocket = BigDecimal(40000),
            requirements = listOf(
                Requirement(name = "Software Developer", skills = "Can make an app"),
                Requirement(name = "Marketer", skills = "POWERPOINT!")
            )
        ),
        Hustle(
            id = 1,
            ownerId = 3,
            name = "GOAT Seller",
            description = "Sells merchandise for Lewis Hamilton a.k.a THE GOAT",
            fundingRequirement = BigDecimal(100000),
            fundingInPocket = BigDecimal(27000),
            requirements = listOf(
                Requirement(name = "Logistics Manager", skills = "Must be able to move merch from one country to another - cheaply", assignedToId = 1),
                Requirement(name = "Marketer", skills = "POWERPOINT!", assignedToId = 2)
            )
        )
    )
}
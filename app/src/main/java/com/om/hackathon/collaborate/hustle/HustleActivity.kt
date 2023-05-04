package com.om.hackathon.collaborate.hustle

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.om.hackathon.collaborate.data.HustleDatabase
import com.om.hackathon.collaborate.hustle.components.Graph
import com.om.hackathon.collaborate.hustle.components.RequirementsList
import com.om.hackathon.collaborate.ui.theme.CollaborateTheme
import com.om.hackathon.collaborate.ui.theme.Primary
import com.om.hackathon.collaborate.ui.theme.SkyBlue

class HustleActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val hustleId = this.intent.getIntExtra("HUSTLE_ID", 1)

        println(hustleId)
        val hustle = HustleDatabase.hustles.first { it.id == hustleId }
        val owner = HustleDatabase.users.first { it.id == hustle.ownerId }
        val isOwner = HustleDatabase.currentLoggedInUserId == hustle.ownerId

        setContent {
            CollaborateTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(modifier = Modifier.padding(15.dp)) {
                        Text(
                            text = hustle.name,
                            color = SkyBlue,
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                        )
                        Text(
                            text = "Owned by ${owner.name} ${owner.surname}",
                            color = Primary,
                            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                        )
                        Spacer(modifier = Modifier.height(48.dp))
                        Text(
                            text = hustle.description,
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Spacer(modifier = Modifier.height(48.dp))
                        Graph(
                            fundingRequirement = hustle.fundingRequirement,
                            fundingInPocket = hustle.fundingInPocket,
                            isOwner = isOwner,
                            owner = owner.name
                        )
                        Spacer(modifier = Modifier.height(72.dp))
                        RequirementsList(requirements = hustle.requirements)
                    }
                }
            }
        }
    }
}
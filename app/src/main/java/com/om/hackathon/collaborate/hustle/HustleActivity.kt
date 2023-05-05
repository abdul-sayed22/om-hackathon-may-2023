package com.om.hackathon.collaborate.hustle

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.om.hackathon.collaborate.dashboard.DashboardActivity
import com.om.hackathon.collaborate.data.HustleDatabase
import com.om.hackathon.collaborate.hustle.components.GradientButton
import com.om.hackathon.collaborate.hustle.components.Graph
import com.om.hackathon.collaborate.hustle.components.RequirementsList
import com.om.hackathon.collaborate.ui.theme.CollaborateTheme
import com.om.hackathon.collaborate.ui.theme.Primary
import com.om.hackathon.collaborate.ui.theme.SkyBlue

class HustleActivity : ComponentActivity() {

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
                    Column(modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(15.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = hustle.name,
                                color = SkyBlue,
                                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                            )
                            if (isOwner) {
                                IconButton(
                                    onClick = {
                                        val intent = Intent(
                                            this@HustleActivity,
                                            EditActivity::class.java
                                        ).putExtra("HUSTLE_ID", hustleId)
                                        startActivity(intent)
                                    }
                                ) {
                                    Icon(
                                        Icons.Outlined.Edit,
                                        contentDescription = "Edit",
                                        tint = SkyBlue
                                    )
                                }
                            }
                        }
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

                        if (!isOwner) {
                            Spacer(modifier = Modifier.height(30.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                GradientButton(
                                    text = "Contribute",
                                    onClick = {
                                        val intent = Intent(
                                            this@HustleActivity,
                                            ContributeActivity::class.java
                                        )
                                        startActivity(intent)
                                    },
                                    modifier = Modifier.fillMaxWidth(0.8f)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(72.dp))
                        RequirementsList(requirements = hustle.requirements)
                    }
                }
            }
        }
    }
}
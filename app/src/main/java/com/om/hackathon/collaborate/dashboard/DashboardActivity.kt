package com.om.hackathon.collaborate.dashboard

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.om.hackathon.collaborate.dashboard.components.HustlesList
import com.om.hackathon.collaborate.hustle.HustleActivity
import com.om.hackathon.collaborate.ui.theme.CollaborateTheme

class DashboardActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CollaborateTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HustlesList { hustle ->
                        startActivity(
                            Intent(this, HustleActivity::class.java)
                                .apply { putExtra("HUSTLE_ID", hustle.id) }
                        )
                    }
                }
            }
        }
    }

}
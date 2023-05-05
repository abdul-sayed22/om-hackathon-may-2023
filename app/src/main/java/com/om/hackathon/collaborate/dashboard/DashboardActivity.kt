package com.om.hackathon.collaborate.dashboard

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.om.hackathon.collaborate.R
import com.om.hackathon.collaborate.dashboard.components.HustlesList
import com.om.hackathon.collaborate.dashboard.components.MyHustleCard
import com.om.hackathon.collaborate.data.HustleDatabase
import com.om.hackathon.collaborate.hustle.EditActivity
import com.om.hackathon.collaborate.hustle.HustleActivity
import com.om.hackathon.collaborate.hustle.components.GradientButton
import com.om.hackathon.collaborate.login.loginForm
import com.om.hackathon.collaborate.ui.theme.CollaborateTheme
import com.om.hackathon.collaborate.ui.theme.Primary
import com.om.hackathon.collaborate.ui.theme.SkyBlue

class DashboardActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DashboardScreen()
        }
    }

}

@Composable
fun DashboardScreen() {
    val context = LocalContext.current
    val myHustle =
        HustleDatabase.hustles.filter { it.ownerId == HustleDatabase.currentLoggedInUserId }.first()

    CollaborateTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier.padding(horizontal = 20.dp)
                ) {
                    Spacer(modifier = Modifier.size(25.dp))
                    Text(
                        text = "Hustles for you",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Spacer(modifier = Modifier.size(30.dp))
                    HustlesList()
                    Spacer(modifier = Modifier.size(35.dp))
                    Text(
                        text = "My Hustle",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Spacer(modifier = Modifier.size(15.dp))
                    MyHustleCard(myHustle)
                    Spacer(modifier = Modifier.size(20.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        GradientButton(
                            text = "Edit your Hustle",
                            textColor = Color.White,
                            gradient = Brush.horizontalGradient(listOf(SkyBlue, Primary)),
                            onClick = {
                                context.startActivity(
                                    Intent(
                                        context,
                                        EditActivity::class.java
                                    ).putExtra("HUSTLE_ID", myHustle.id)
                                )
                            },
                            modifier = Modifier.width(200.dp)
                        )
                    }

                }
                Spacer(modifier = Modifier.height(10.dp))
                Image(
                    modifier = Modifier.fillMaxWidth(1.25f),
                    painter = painterResource(id = R.drawable.ad2),
                    contentDescription = "",
                    contentScale = ContentScale.FillWidth
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardPreview() {
    DashboardScreen()
}

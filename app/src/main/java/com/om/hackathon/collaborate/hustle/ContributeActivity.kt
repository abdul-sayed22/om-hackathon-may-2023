package com.om.hackathon.collaborate.hustle

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.om.hackathon.collaborate.R
import com.om.hackathon.collaborate.data.HustleDatabase
import com.om.hackathon.collaborate.hustle.components.GradientButton
import com.om.hackathon.collaborate.ui.theme.CollaborateTheme
import com.om.hackathon.collaborate.ui.theme.Primary
import com.om.hackathon.collaborate.ui.theme.SkyBlue
import java.math.BigDecimal
import java.math.RoundingMode

class ContributeActivity : ComponentActivity() {
    
    private val contributionPresets: List<BigDecimal> = listOf(
        BigDecimal(100),
        BigDecimal(500),
        BigDecimal(1000),
        BigDecimal(5000),
        BigDecimal(10000)
    )

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CollaborateTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(modifier = Modifier.padding(vertical = 72.dp, horizontal = 15.dp)) {
                        var selectedOption by remember { mutableStateOf(contributionPresets.first()) }

                        Text(
                            text = "How much would you like to contribute?",
                            style = MaterialTheme.typography.titleLarge
                        )
                        Spacer(modifier = Modifier.height(32.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            for (preset in contributionPresets) {
                                InputChip(
                                    label = { Text("R$preset") },
                                    selected = selectedOption == preset,
                                    colors = InputChipDefaults.inputChipColors(
                                        selectedContainerColor = SkyBlue
                                    ),
                                    onClick = { selectedOption = preset }
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                            }
                        }

                        Spacer(modifier = Modifier.height(72.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            GradientButton(
                                text = "Make Contribution",
                                onClick = {
                                    Toast.makeText(
                                        this@ContributeActivity,
                                        "Thank you for your contribution!",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    finish()
                                },
                                modifier = Modifier.fillMaxWidth(0.8f)
                            )
                        }


                    }
                    Box(contentAlignment = Alignment.BottomCenter) {
                        Image(
                            painter = painterResource(id = R.drawable.ad1),
                            contentDescription = "",
                            contentScale = ContentScale.FillWidth
                        )
                    }
                }
            }
        }
    }
}
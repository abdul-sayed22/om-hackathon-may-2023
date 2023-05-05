package com.om.hackathon.collaborate.hustle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.om.hackathon.collaborate.data.HustleDatabase
import com.om.hackathon.collaborate.hustle.components.GradientButton
import com.om.hackathon.collaborate.ui.theme.CollaborateTheme
import com.om.hackathon.collaborate.ui.theme.SkyBlue
import java.text.NumberFormat
import java.util.Locale

class EditActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val hustleId = this.intent.getIntExtra("HUSTLE_ID", 1)

        println(hustleId)
        val hustle = HustleDatabase.hustles.first { it.id == hustleId }

        setContent {
            CollaborateTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(modifier = Modifier.padding(15.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "What's your hustle?",
                            color = SkyBlue,
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                        )
                        Spacer(modifier = Modifier.height(48.dp))
                        OutlinedTextField(value = hustle.name,
                            onValueChange = {},
                            label = { Text("Hustle") }, supportingText = {
                            }, modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(48.dp))
                        OutlinedTextField(value = hustle.description,
                            onValueChange = {},
                            label = { Text("Description") }, supportingText = {
                            }, modifier = Modifier.fillMaxWidth().height(200.dp)
                        )
                        Spacer(modifier = Modifier.height(48.dp))

                        Text(
                            text = "Funds required",
                            style = MaterialTheme.typography.bodyLarge
                        )

                        var sliderValue by rememberSaveable { mutableStateOf(hustle.fundingRequirement.toFloat()) }

                        Slider(
                            value = sliderValue,
                            onValueChange = { sliderValue_ ->
                                sliderValue = sliderValue_
                            },
                            onValueChangeFinished = {
                                // this is called when the user completed selecting the value
                            },
                            valueRange = 0f..200000f,
                            steps = 19
                        )

                        Text(text = "${NumberFormat.getCurrencyInstance(Locale("en", "ZA")).format(sliderValue.toInt())}",
                            color = SkyBlue,
                            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold))
                        Spacer(modifier = Modifier.height(48.dp))
                        GradientButton(
                            text = "Save",
                            onClick = {

                            },
                            modifier = Modifier.fillMaxWidth(0.8f)
                        )
                    }
                }
            }
        }
    }
}
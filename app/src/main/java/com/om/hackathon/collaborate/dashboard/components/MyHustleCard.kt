package com.om.hackathon.collaborate.dashboard.components

import android.content.Intent
import android.view.View
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.om.hackathon.collaborate.R
import com.om.hackathon.collaborate.data.HustleDatabase
import com.om.hackathon.collaborate.hustle.HustleActivity
import com.om.hackathon.collaborate.models.Hustle
import com.om.hackathon.collaborate.ui.theme.Primary
import com.om.hackathon.collaborate.ui.theme.SkyBlue
import java.math.BigDecimal

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun MyHustleCard(hustle: Hustle) {
    val context = LocalContext.current
    Card(
        onClick = {
            context.startActivity(
                Intent(context, HustleActivity::class.java)
                    .apply { putExtra("HUSTLE_ID", hustle.id) }
            )
        },
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier.height(280.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Brush.horizontalGradient(listOf(SkyBlue, Primary))),
        ) {
            Row(
                modifier = Modifier
                    .padding(vertical = 10.dp, horizontal = 20.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "${hustle.name}",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleSmall,
                    color = Color.White
                )
            }
            Row(
                modifier = Modifier
                    .height(80.dp)
                    .fillMaxWidth()
            ) {

                Column(
                    modifier = Modifier
                        .padding(vertical = 5.dp)
                        .fillMaxHeight()
                        .fillMaxWidth(0.51f), horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Funding",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    Text(
                        text = "${hustle.fundingInPocket.divide(BigDecimal(1000))} / ${
                            hustle.fundingRequirement.divide(
                                BigDecimal(1000)
                            )
                        } K",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White
                    )
                }
                Divider(
                    color = Color.White,
                    modifier = Modifier
                        .padding(vertical = 20.dp)
                        .fillMaxHeight()  //fill the max height
                        .width(2.dp)
                )
                Column(
                    modifier = Modifier
                        .padding(vertical = 5.dp)
                        .fillMaxHeight()
                        .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Skills",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    Text(
                        text = "${
                            hustle.requirements.filter { it.assignedToId != null }.count()
                        } / ${hustle.requirements.count()} ",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White
                    )
                }
            }
            Image(
                painter = painterResource(id = hustle.imageId),
                contentDescription = "",
                contentScale = ContentScale.FillWidth
            )

        }
    }
}
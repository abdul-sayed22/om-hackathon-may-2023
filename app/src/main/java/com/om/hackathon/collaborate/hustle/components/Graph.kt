package com.om.hackathon.collaborate.hustle.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.om.hackathon.collaborate.Greeting
import com.om.hackathon.collaborate.ui.theme.CollaborateTheme
import com.om.hackathon.collaborate.ui.theme.Primary
import com.om.hackathon.collaborate.ui.theme.SkyBlue
import java.math.BigDecimal
import java.math.RoundingMode

@Composable
fun Graph(fundingRequirement: BigDecimal, fundingInPocket: BigDecimal, isOwner: Boolean, owner: String, modifier: Modifier = Modifier) {
    val progress = fundingInPocket.divide(fundingRequirement, 2, RoundingMode.HALF_UP).toFloat()
    Column(
        Modifier
            .fillMaxWidth()) {
        Row {
            Text(text = if (isOwner) "You have " else "$owner has ", style = MaterialTheme.typography.titleMedium)
            Text(text = "R$fundingInPocket", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold))
            Text(text = " out of ", style = MaterialTheme.typography.titleMedium)
            Text(text = "R$fundingRequirement", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold))
        }
        Spacer(modifier = Modifier.size(15.dp))
        GradientProgressBar(startColor = SkyBlue, endColor = Primary, progress = progress, modifier = Modifier.clip(shape = RoundedCornerShape(15.dp)).height(30.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun GraphPreview() {
    CollaborateTheme {
        Graph(BigDecimal(50000), BigDecimal(25000), false, "Bob")
    }
}
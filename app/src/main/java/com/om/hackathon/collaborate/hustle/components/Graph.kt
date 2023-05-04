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
import java.math.BigDecimal

@Composable
fun Graph(fundingRequirement: BigDecimal, fundingInPocket: BigDecimal, modifier: Modifier = Modifier) {
    val progress = fundingInPocket.divide(fundingRequirement).toFloat()
    Column(
        Modifier
            .padding(horizontal = 15.dp)
            .width(IntrinsicSize.Max)) {
        Row {
            Text(text = "You have ", style = MaterialTheme.typography.headlineSmall)
            Text(text = "R$fundingInPocket", style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold))
            Text(text = " out of ", style = MaterialTheme.typography.headlineSmall)
            Text(text = "R$fundingRequirement", style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold))
        }
        Spacer(modifier = Modifier.size(15.dp))
        GradientProgressBar(startColor = MaterialTheme.colorScheme.primary, endColor = MaterialTheme.colorScheme.secondary, progress = progress, modifier = Modifier.clip(shape = RoundedCornerShape(15.dp)).height(30.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun GraphPreview() {
    CollaborateTheme {
        Graph(BigDecimal(50000), BigDecimal(25000))
    }
}
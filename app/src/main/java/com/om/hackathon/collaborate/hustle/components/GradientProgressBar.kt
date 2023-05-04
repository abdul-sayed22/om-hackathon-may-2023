package com.om.hackathon.collaborate.hustle.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.om.hackathon.collaborate.ui.theme.Secondary
import com.om.hackathon.collaborate.ui.theme.SurfaceBackground
import com.om.hackathon.collaborate.ui.theme.Tertiary

@Composable
fun GradientProgressBar(startColor: Color, endColor: Color, progress: Float, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(SurfaceBackground)
            .fillMaxWidth()
    ) {
        Box(
            modifier = modifier
                .background(Brush.horizontalGradient(colors = listOf(startColor, endColor)))
                .fillMaxWidth(fraction = progress)
        )
    }
}

@Preview
@Composable
fun GradientProgressBarPreview() {
    GradientProgressBar(startColor = Secondary, endColor = Tertiary, progress = 0.5f, modifier = Modifier.clip(shape = RoundedCornerShape(5.dp)).height(14.dp))
}
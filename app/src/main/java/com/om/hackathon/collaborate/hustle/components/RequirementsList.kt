package com.om.hackathon.collaborate.hustle.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.om.hackathon.collaborate.R
import com.om.hackathon.collaborate.models.Requirement
import com.om.hackathon.collaborate.ui.theme.Background
import com.om.hackathon.collaborate.ui.theme.Primary
import com.om.hackathon.collaborate.ui.theme.SkyBlue

@Composable
fun RequirementsList(requirements: List<Requirement>, modifier: Modifier = Modifier) {
    Column {
        Text(text = "Requirements for the hustle", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(15.dp))

        for (requirement in requirements) {
            RequirementItem(requirement = requirement)
            
            Spacer(modifier = Modifier.height(5.dp))
        }
    }
}

@Composable
fun RequirementItem(requirement: Requirement, modifier: Modifier = Modifier) {
    Card(
        modifier = Modifier
            .wrapContentSize(align = Alignment.TopStart)
            .fillMaxWidth()
    ) {
        Row(modifier = Modifier
            .background(Brush.horizontalGradient(listOf(SkyBlue, Primary)))
            .fillMaxWidth()
            .padding(10.dp)
        ) {
            Image(
                painterResource(id = if (requirement.assignedToId == null) R.drawable.unfulfilled else R.drawable.fulfilled),
                contentDescription = "Status",
                modifier = Modifier
                    .size(32.dp, 32.dp)
                    .align(alignment = CenterVertically)
            )

            Spacer(modifier = Modifier.width(15.dp))

            Column {
                Text(text = requirement.name, color = Background, style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold))
                Text(text = requirement.skills, color = Background)
            }
        }
    }
}
package com.om.hackathon.collaborate.dashboard.components

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.om.hackathon.collaborate.data.HustleDatabase
import com.om.hackathon.collaborate.hustle.HustleActivity

@Preview
@Composable
fun HustlesList() {
    Column(
        modifier = Modifier
            .padding(all = 4.dp), // inside padding
        verticalArrangement = Arrangement.spacedBy(
            space = 4.dp,
            alignment = Alignment.CenterVertically
        )
    ) {
        BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
            // LazyRow to display your items horizontally
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                state = rememberLazyListState()
            ) {
                itemsIndexed(HustleDatabase.hustles.filterNot { it.ownerId == HustleDatabase.currentLoggedInUserId }) { _, it ->
                    HustleCard(it)
                    Spacer(modifier = Modifier.size(10.dp))
                }
            }
        }

    }
}
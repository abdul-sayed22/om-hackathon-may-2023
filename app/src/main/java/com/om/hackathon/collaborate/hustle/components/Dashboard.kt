package com.om.hackathon.collaborate.hustle.components

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.om.hackathon.collaborate.MainActivity
import com.om.hackathon.collaborate.data.HustleDatabase
import com.om.hackathon.collaborate.hustle.HustleActivity

@Composable
fun HustlesScreen(hustleDB: HustleDatabase, activity: ComponentActivity) {
    Column(
        modifier = Modifier
            .padding(all = 4.dp), // inside padding
        verticalArrangement = Arrangement.spacedBy(
            space = 4.dp,
            alignment = Alignment.CenterVertically
        )
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            text = "Hustles",
            fontSize = 30.sp,
            color = Color.White
        )
        LazyColumn {
            itemsIndexed(hustleDB.hustles) { _, it ->
                HustleCard(
                    id = it.id,
                    name = it.name,
                    owner = hustleDB.lookupOwner(it.ownerId).username,
                    imageId = it.imageID,
                    activity
                )
            }
        }
    }
}

@Composable
fun HustleCard(id: Int, name: String, owner: String, @DrawableRes imageId: Int, activity: ComponentActivity) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(20.dp)
    ) {
        Card(
            modifier = Modifier
                .clickable{
                    activity.startActivity(Intent(activity, HustleActivity::class.java).putExtra("HUSTLE_ID", id))
                    activity.finish()
                },
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Image(painter = painterResource(id = imageId), contentDescription = null)
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            ) {
                Text(name, fontWeight = FontWeight.W700, color = Color.White)
                Text(owner, color = Color.White)
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Icon(
                        Icons.Filled.Favorite,
                        contentDescription = "Favorite",
                        tint = Color.Red
                    )
                    Icon(
                        Icons.Filled.Call,
                        contentDescription = "Call",
                        tint = Color.Black
                    )
                }
            }
        }
    }
}
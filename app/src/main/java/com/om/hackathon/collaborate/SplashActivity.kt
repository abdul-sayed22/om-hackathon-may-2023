package com.om.hackathon.collaborate

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.om.hackathon.collaborate.dashboard.DashboardActivity

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this@SplashActivity, DashboardActivity::class.java))
        finish()
    }
}


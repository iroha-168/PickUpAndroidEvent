package com.mitchan.pickupandroidevent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mitchan.pickupandroidevent.ui.screen.top.PickUpAndroidEventApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PickUpAndroidEventApp()
        }
    }
}

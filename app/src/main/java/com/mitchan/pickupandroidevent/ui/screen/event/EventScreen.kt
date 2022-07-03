package com.mitchan.pickupandroidevent.ui.screen.event

import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.mitchan.pickupandroidevent.ui.component.EventCard

@Composable
fun EventScreen() {
    LazyColumn(modifier = Modifier.background(Color.Black)) {
        items(10) { index ->
            EventCard(
                month = "6",
                date = "20",
                day = "月",
                eventTitle = "$index あいうえお",
                isFavorite = index % 2 == 0,
                startTime = "19:00",
                location = "online"
            )
        }
    }
}
package com.mitchan.pickupandroidevent.ui.screen.favorite

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.mitchan.pickupandroidevent.ui.component.EventCard

@Composable
fun FavoriteScreen() {
    LazyColumn {
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
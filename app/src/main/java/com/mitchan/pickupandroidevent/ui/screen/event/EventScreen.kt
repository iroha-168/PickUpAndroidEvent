package com.mitchan.pickupandroidevent.ui.screen.event

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.mitchan.pickupandroidevent.ui.component.EventCard
import java.util.*

@Composable
fun EventScreen(viewModel: EventViewModel = hiltViewModel()) {
    val uiState = viewModel.uiState.collectAsState()
    LazyColumn(modifier = Modifier.background(Color.Black)) {
        Log.d("UISTATE", uiState.value.toString())

        items(uiState.value.eventList) { itemUiState ->
            EventCard(
                month = itemUiState.month,
                date = itemUiState.date,
                day = itemUiState.day,
                eventTitle = itemUiState.event.title,
                isFavorite = itemUiState.isFavorite,
                startTime = itemUiState.startTime,
                location = itemUiState.event.place.orEmpty()
            )
        }
    }
}
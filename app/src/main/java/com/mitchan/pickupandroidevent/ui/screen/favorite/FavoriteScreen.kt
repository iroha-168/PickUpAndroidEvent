package com.mitchan.pickupandroidevent.ui.screen.favorite

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import com.mitchan.pickupandroidevent.ui.component.EventCard
import com.mitchan.pickupandroidevent.ui.screen.EventUiState

@Composable
fun FavoriteScreen(viewModel: FavoriteViewModel = hiltViewModel()) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val flowLifecycleAware = remember(key1 = viewModel, key2 = lifecycleOwner) {
        viewModel.uiState.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    val uiState: EventUiState by flowLifecycleAware.collectAsState(initial = EventUiState())

    LazyColumn {
        items(uiState.eventList) { itemUiState ->
            EventCard(
                month = itemUiState.month,
                date = itemUiState.date,
                day = itemUiState.day,
                eventTitle = itemUiState.event.title,
                isFavorite = itemUiState.event.isFavorite,
                startTime = itemUiState.startTime,
                location = itemUiState.event.place.orEmpty(),
                eventId = itemUiState.event.eventId,
                onFavoriteButtonClick = viewModel::onFavoriteButtonClick
            )
        }
    }
}


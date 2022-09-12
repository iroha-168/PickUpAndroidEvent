package com.mitchan.pickupandroidevent.ui.screen

import com.mitchan.pickupandroidevent.data.repository.EventRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

interface OnFavoriteButtonClickDelegate {
    val coroutineScope: CoroutineScope
    val eventRepository: EventRepository
    val uiState: StateFlow<EventUiState>
    fun onFavoriteButtonClick(eventId: Long) {
        val selectedEvent = uiState.value.eventList.find {
            it.event.eventId == eventId
        } ?: return

        coroutineScope.launch {
            eventRepository.updateEvent(
                selectedEvent.event.copy(isFavorite = !selectedEvent.event.isFavorite)
            )
        }
    }
}
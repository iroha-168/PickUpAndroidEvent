package com.mitchan.pickupandroidevent.ui.screen.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mitchan.pickupandroidevent.data.repository.EventRepository
import com.mitchan.pickupandroidevent.ui.screen.EventItemUiState
import com.mitchan.pickupandroidevent.ui.screen.EventUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val eventRepository: EventRepository
) : ViewModel() {
    val uiState: StateFlow<EventUiState> = eventRepository.favoriteEvents.map { list ->
        val itemUiState = list.map {
            EventItemUiState(it)
        }
        EventUiState(itemUiState)
    }.stateIn(viewModelScope, SharingStarted.Eagerly, EventUiState())

    fun onFavoriteButtonClick(eventId: Long) {
        val selectedEvent = uiState.value.eventList.find {
            it.event.eventId == eventId
        } ?: return

        viewModelScope.launch {
            eventRepository.updateEvent(
                selectedEvent.event.copy(isFavorite = !selectedEvent.event.isFavorite)
            )
        }
    }
}
package com.mitchan.pickupandroidevent.ui.screen.event

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mitchan.pickupandroidevent.data.db.EventDto
import com.mitchan.pickupandroidevent.data.repository.EventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor (
    private val eventRepository: EventRepository
) : ViewModel() {

    val uiState: StateFlow<EventUiState> = eventRepository.events.map { eventList ->
        val itemUiState = eventList.map { event ->
            EventItemUiState(event)
        }
        EventUiState(itemUiState)
    }.stateIn(viewModelScope, SharingStarted.Eagerly, EventUiState())

    init {
        refresh()
    }

    private fun refresh() {
        viewModelScope.launch {
            runCatching {
                eventRepository.refresh()
            }.onFailure {
                Log.e("throwable", it.toString())
            }
        }
    }


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

    data class EventUiState(
        val eventList: List<EventItemUiState> = emptyList()
    )

    data class EventItemUiState(
        val event: EventDto,
    ) {
        private val calendar = Calendar.getInstance().also {
            it.time = event.startedAt?.date
        }
        val month = calendar.get(Calendar.MONTH).toString() + "月"
        val date = calendar.get(Calendar.DATE).toString()
        val startTime = calendar.get(Calendar.HOUR_OF_DAY).toString() +
                ":" +
                calendar.get(Calendar.MINUTE).toString().let {
                    if (it == "0") "00" else it
                }
        val day = when(calendar.get(Calendar.DAY_OF_WEEK)) {
            Calendar.MONDAY -> "月"
            Calendar.TUESDAY -> "火"
            Calendar.WEDNESDAY -> "水"
            Calendar.THURSDAY -> "木"
            Calendar.FRIDAY -> "金"
            Calendar.SATURDAY -> "土"
            Calendar.SUNDAY -> "日"
            else -> ""
        }
    }
}

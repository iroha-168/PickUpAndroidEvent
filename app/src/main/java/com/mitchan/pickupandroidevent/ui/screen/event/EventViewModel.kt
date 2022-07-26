package com.mitchan.pickupandroidevent.ui.screen.event

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mitchan.pickupandroidevent.data.entity.Event
import com.mitchan.pickupandroidevent.data.repository.EventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor (
    private val eventRepository: EventRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(EventUiState())
    val uiState: StateFlow<EventUiState> = _uiState.asStateFlow()

    init {
        refresh()
    }

    private fun refresh() {
        viewModelScope.launch {
            runCatching {
                eventRepository.getEvent()
            }.onSuccess { eventList ->
                _uiState.update {
                    it.copy(eventList = eventList.map { event ->
                        EventItemUiState(event, false)
                    })
                }
            }.onFailure {
                Log.d("throwable", it.toString())
            }
        }
    }

    data class EventUiState(
        val eventList: List<EventItemUiState> = emptyList()
    )

    data class EventItemUiState(
        val event: Event,
        val isFavorite: Boolean
    ) {
        private val calendar = Calendar.getInstance().also {
            it.time = event.startedAt.date
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

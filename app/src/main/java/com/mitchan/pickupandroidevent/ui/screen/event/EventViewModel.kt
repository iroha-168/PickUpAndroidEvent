package com.mitchan.pickupandroidevent.ui.screen.event

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mitchan.pickupandroidevent.data.db.EventDto
import com.mitchan.pickupandroidevent.data.repository.EventRepository
import com.mitchan.pickupandroidevent.ui.screen.EventItemUiState
import com.mitchan.pickupandroidevent.ui.screen.EventUiState
import com.mitchan.pickupandroidevent.ui.screen.OnFavoriteButtonClickDelegate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor (
    override val eventRepository: EventRepository
) : ViewModel(), OnFavoriteButtonClickDelegate {

    override val coroutineScope: CoroutineScope
        get() = viewModelScope
    override val uiState: StateFlow<EventUiState> = eventRepository.events.map { eventList ->
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

    // 本来ならDelegateせずにFavoriteViewModelと同じように、ここにonFavoriteButtonClickを実装してておk
    // Delegateがどういうものか勉強したいからわざわざdelegateを実装しました
}

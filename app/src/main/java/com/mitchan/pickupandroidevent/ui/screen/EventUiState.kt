package com.mitchan.pickupandroidevent.ui.screen

import com.mitchan.pickupandroidevent.data.db.EventDto
import java.util.*

class EventUiState(
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
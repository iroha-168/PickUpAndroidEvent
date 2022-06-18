package com.mitchan.pickupandroidevent.ui.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mitchan.pickupandroidevent.R

@Composable
fun EventCard(
    month: String,
    date: String,
    day: String,
    eventTitle: String,
    isFavorite: Boolean,
    startTime: String,
    location: String,
) {
    Card(
        modifier = androidx.compose.ui.Modifier
            .padding(16.dp)
    ) {
        Row {
            // イベント開催　日付
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(top = 12.dp, start = 12.dp, bottom = 12.dp)
                    .align(CenterVertically)
            ) {
                Text(text = month, style = TextStyle(fontWeight = FontWeight.Bold))
                Text(text = date, style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 32.sp))
                Text(text = day)
            }

            Spacer(modifier = Modifier.width(16.dp))

            // タイトル、時間、場所、お気に入りボタン
            Column(Modifier.weight(1f)) {
                // イベントタイトル　お気に入りボタン
                Row() {
                    Text(
                        text = eventTitle,
                        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 24.sp),
                        modifier = Modifier
                            .weight(1f)
                            .padding(top = 12.dp),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    IconToggleButton(
                        checked = isFavorite,
                        onCheckedChange = { /* do something */ }) {
                        val tint by animateColorAsState(if (isFavorite) Color.Red else Color.Gray)
                        Icon(Icons.Filled.Favorite, contentDescription = "お気に入り", tint = tint)
                    }
                }
                // 時間　場所
                Row(
                    modifier = Modifier.padding(bottom = 12.dp, end = 12.dp, top = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_access_time),
                        contentDescription = null
                    )
                    Text(text = startTime)
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.ic_location),
                        contentDescription = null
                    )
                    Text(text = location, maxLines = 1, overflow = TextOverflow.Ellipsis)
                }
            }
        }
    }
}

@Preview
@Composable
fun EventCardPreview_isFavorite() {
    EventCard(
        month = "6月",
        date = "15",
        day = "水",
        eventTitle = "potatotips",
        isFavorite = true,
        startTime = "19:30",
        location = "online"
    )
}

@Preview
@Composable
fun EventCardPreview_isNotFavorite() {
    EventCard(
        month = "6月",
        date = "15",
        day = "水",
        eventTitle = "potatotips",
        isFavorite = false,
        startTime = "19:30",
        location = "online"
    )
}

@Preview
@Composable
fun EventCardPreview_longTitle() {
    EventCard(
        month = "6月",
        date = "15",
        day = "水",
        eventTitle = "potatotips potatotips potatotips potatotips potatotips",
        isFavorite = true,
        startTime = "19:30",
        location = "online online online online online online"
    )
}

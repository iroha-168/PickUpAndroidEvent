package com.mitchan.pickupandroidevent.ui.screen.top

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.mitchan.pickupandroidevent.R
import com.mitchan.pickupandroidevent.ui.screen.event.EventScreen
import com.mitchan.pickupandroidevent.ui.theme.PickUpAndroidEventTheme

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PickUpAndroidEventApp() {
    PickUpAndroidEventTheme {
        Scaffold(
            topBar = { MyTopAppBar() }
        ) {
            Column {
                TextTabs()
                HorizontalPager(count = 2) {
                    EventScreen()
                }
            }
        }
    }
}

@Composable
fun MyTopAppBar() {
    TopAppBar(title = {
        Text(text = stringResource(id = R.string.app_name))
    })
}

@Composable
fun TextTabs() {
    var tabIndex by remember { mutableStateOf(0) }
    val tabData = listOf(
        R.string.tab_name_event,
        R.string.tab_name_favorite,
    )
    TabRow(selectedTabIndex = tabIndex) {
        tabData.forEachIndexed { index, titleResId ->
            Tab(
                selected = tabIndex == index,
                onClick = { tabIndex = index },
                text = { Text(text = stringResource(id = titleResId)) }
            )
        }
    }
}

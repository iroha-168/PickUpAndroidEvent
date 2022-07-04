package com.mitchan.pickupandroidevent.ui.screen.top

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mitchan.pickupandroidevent.R
import com.mitchan.pickupandroidevent.ui.screen.event.EventScreen
import com.mitchan.pickupandroidevent.ui.screen.favorite.FavoriteScreen
import com.mitchan.pickupandroidevent.ui.theme.PickUpAndroidEventTheme

// NavGraphは画面の数だけ増える
sealed class NavGraph(val destination: String) {
    object Event: NavGraph("event")
    object Favorite: NavGraph("favorite")
}

@Composable
fun PickUpAndroidEventApp() {
    val navController = rememberNavController()
    var selectedTabType by remember { mutableStateOf(TabType.Event) }
    PickUpAndroidEventTheme {
        Scaffold(
            topBar = { MyTopAppBar() }
        ) {
            Column {
                // onClick = {} のラムダの中：子のonClick呼ばれたタイミングでここが起動する
                TextTabs(
                    selectedTabType,
                    onClick = {
                        selectedTabType = it
                        when(it) {
                            TabType.Event -> {
                                // Navigateする
                                navController.navigate(NavGraph.Event.destination)
                            }
                            TabType.Favorite -> {
                                navController.navigate(NavGraph.Favorite.destination)
                            }
                        }
                    }
                )
                NavHost(navController = navController, startDestination = NavGraph.Event.destination) {
                    composable(NavGraph.Event.destination) { EventScreen() }
                    composable(NavGraph.Favorite.destination) { FavoriteScreen() }
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
fun TextTabs(selectedTabType: TabType, onClick: (TabType) -> Unit) {
    val tabData = TabType.values()
    TabRow(selectedTabIndex = tabData.indexOf(selectedTabType)) {
        tabData.forEach { tabType ->
            Tab(
                selected = selectedTabType == tabType,
                onClick = { onClick(tabType) },
                text = { Text(text = stringResource(id = tabType.labelResId)) }
            )
        }
    }
}

// TabTypeはタブの数だけ増える（画面の数だけではない）
enum class TabType(@StringRes val labelResId: Int) {
    Event(R.string.tab_name_event),
    Favorite(R.string.tab_name_favorite)
}

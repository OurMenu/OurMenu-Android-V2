package com.kuit.ourmenu.ui.navigator

import androidx.compose.runtime.Composable
import com.kuit.ourmenu.R

enum class MainTab(
    val iconResId: Int,
    val selectedIconResId: Int,
    internal val contentDescription: String,
    val label: String,
    val route: MainTabRoute,
) {
    HOME(
        iconResId = R.drawable.ic_navigation_bar_home,
        selectedIconResId = R.drawable.ic_navigation_bar_home_selected,
        contentDescription = "Home Icon",
        label = "홈",
        route = MainTabRoute.Home,
    ),
    MAP(
        iconResId = R.drawable.ic_navigation_bar_map,
        selectedIconResId = R.drawable.ic_navigation_bar_map_selected,
        contentDescription = "Map Icon",
        label = "지도",
        route = MainTabRoute.Map,
    ),
    MENU_FOLDER(
        iconResId = R.drawable.ic_navigation_bar_folder,
        selectedIconResId = R.drawable.ic_navigation_bar_folder_selected,
        contentDescription = "Menu Folder Icon",
        label = "메뉴판",
        route = MainTabRoute.MenuFolder,
    ),
    MY(
        iconResId = R.drawable.ic_navigation_bar_my,
        selectedIconResId = R.drawable.ic_navigation_bar_my_selected,
        contentDescription = "My Icon",
        label = "마이",
        route = MainTabRoute.My,
    )
    ;

    companion object {

        @Composable
        fun contains(predicate: @Composable (Routes) -> Boolean): Boolean {
            return entries.map { it.route }.any { predicate(it) }
        }
    }
}
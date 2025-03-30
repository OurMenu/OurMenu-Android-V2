package com.kuit.ourmenu.ui.navigator.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.ui.navigator.MainTab
import com.kuit.ourmenu.ui.theme.NeutralWhite
import com.kuit.ourmenu.ui.theme.OurMenuTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
internal fun MainBottomBar(
    modifier: Modifier = Modifier,
    visible: Boolean,
    tabs: ImmutableList<MainTab>,
    currentTab: MainTab?,
    onTabSelected: (MainTab) -> Unit,
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn() + slideIn { IntOffset(0, it.height) },
        exit = fadeOut() + slideOut { IntOffset(0, it.height) },
    ) {
        Row(
            modifier = modifier
                .shadow(
                    elevation = 4.dp,
                    spotColor = Color(0x1F000000),
                    ambientColor = Color(0x1F000000)
                )
                .background(color = NeutralWhite)
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .height(76.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            tabs.forEach { tab ->
                MainBottomBarItem(
                    tab = tab,
                    selected = tab == currentTab,
                    onClick = {
                        if (tab != currentTab) onTabSelected(tab)
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainBottomBarPreview() {
    OurMenuTheme {
        MainBottomBar(
            visible = true,
            tabs = MainTab.entries.toImmutableList(),
            currentTab = MainTab.HOME,
            onTabSelected = {}
        )
    }
}
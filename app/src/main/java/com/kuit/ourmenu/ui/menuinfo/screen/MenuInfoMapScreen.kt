package com.kuit.ourmenu.ui.menuinfo.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.ui.common.GoToMapButton
import com.kuit.ourmenu.ui.common.bottomsheet.BottomSheetDragHandle
import com.kuit.ourmenu.ui.common.topappbar.OurMenuAddButtonTopAppBar
import com.kuit.ourmenu.ui.menuinfo.component.map.MenuInfoMapBottomSheetContent
import com.kuit.ourmenu.ui.theme.NeutralWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuInfoMapScreen(modifier: Modifier = Modifier) {
    val scaffoldState = rememberBottomSheetScaffoldState()
    var dragHandleHeight by remember { mutableStateOf(0.dp) }
    var bottomSheetContentHeight by remember { mutableStateOf(0.dp) }

    val density = LocalDensity.current

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContainerColor = NeutralWhite,
        sheetSwipeEnabled = false,
        topBar = { OurMenuAddButtonTopAppBar() },
        sheetDragHandle = {
            BottomSheetDragHandle(
                modifier = Modifier.onGloballyPositioned { coordinates ->
                    dragHandleHeight = density.run { coordinates.size.height.toDp() }
                }
            )
        },
        sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        sheetContent = {
            MenuInfoMapBottomSheetContent(
                modifier = Modifier.onGloballyPositioned { coordinates ->
                    val heightPx = coordinates.size.height
                    bottomSheetContentHeight = density.run {
                        heightPx.toDp() + dragHandleHeight
                    }
                }
            )
        },
        sheetPeekHeight = bottomSheetContentHeight,
    ) { innerPaddings ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(bottom = 16.dp, end = 20.dp)
                .padding(innerPaddings)
        ) {
            // TODO : Map SDK
            GoToMapButton(
                modifier = Modifier.align(Alignment.BottomEnd),
                onClick = { /* TODO : Go To Map Button Click Event */ },
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun MenuInfoMapScreenPreview() {
    MenuInfoMapScreen()
}
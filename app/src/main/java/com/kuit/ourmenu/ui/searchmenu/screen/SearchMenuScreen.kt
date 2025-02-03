package com.kuit.ourmenu.ui.searchmenu.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.common.CustomTextField
import com.kuit.ourmenu.ui.common.SearchTextField
import com.kuit.ourmenu.ui.common.bottomsheet.BottomSheetDragHandle
import com.kuit.ourmenu.ui.common.topappbar.OurMenuAddButtonTopAppBar
import com.kuit.ourmenu.ui.theme.Neutral300
import com.kuit.ourmenu.ui.theme.NeutralWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchMenuScreen(modifier: Modifier = Modifier) {

    val scaffoldState = rememberBottomSheetScaffoldState()
    var dragHandleHeight by remember { mutableStateOf(0.dp) }
    val bottomSheetContentHeight by remember { mutableStateOf(0.dp) }

    val density = LocalDensity.current

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxWidth(),
        topBar = { OurMenuAddButtonTopAppBar() },
        sheetContent = { },
        sheetContainerColor = NeutralWhite,
        sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        sheetDragHandle = {
            BottomSheetDragHandle(
                modifier = Modifier.onGloballyPositioned { coordinates ->
                    dragHandleHeight = density.run { coordinates.size.height.toDp() }
                }
            )
        },
        sheetPeekHeight = bottomSheetContentHeight,
        containerColor = NeutralWhite,
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column {
                SearchTextField(
                    text = "",
                    onTextChange = {},
                    onSearch = {},
                    borderColor = Neutral300,
                    placeHolder = R.string.search_place_holder,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .padding(top = 12.dp)
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun SearchMenuScreenPreview() {
    SearchMenuScreen()
}
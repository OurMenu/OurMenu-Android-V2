package com.kuit.ourmenu.ui.addmenu.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.ui.common.SearchBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMenuScreen(modifier: Modifier = Modifier) {
    val scaffoldState = rememberBottomSheetScaffoldState()
    val showBottomSheet by remember { mutableStateOf(true) }
    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text("OURMENU") },
                navigationIcon = {
                    IconButton(onClick = { TODO() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        sheetContent = {
            Column {
                Text("Bottom Sheet")
            }
        },
        //조건 만족하면 bottom sheet 보여주고, 아니면 화면에 안보이도록 처리
        sheetPeekHeight = if(showBottomSheet) 100.dp else 0.dp
    ) {
        //전체 화면 구성
        Column {
            SearchBar(placeholder = "placeholder", onSearch = { })
            Text("Add Menu Screen")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AddMenuScreenPreview() {
    AddMenuScreen()
}
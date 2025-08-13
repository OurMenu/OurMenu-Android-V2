package com.kuit.ourmenu.ui.dummy

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kuit.ourmenu.ui.dummy.viewmodel.DummyViewModel

@Composable
fun DummyScreen(
    modifier: Modifier = Modifier,
    viewModel: DummyViewModel = hiltViewModel()
) {

    val uiState by viewModel.dummyUiState.collectAsStateWithLifecycle() // 이 때 init { } 에서 getDummyData 호출됨.

}
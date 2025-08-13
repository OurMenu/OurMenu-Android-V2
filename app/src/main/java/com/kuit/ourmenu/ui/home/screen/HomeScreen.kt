package com.kuit.ourmenu.ui.home.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.kuit.ourmenu.ui.common.topappbar.OurMenuAddButtonTopAppBar
import com.kuit.ourmenu.ui.home.component.dialog.HomePopUpDialog
import com.kuit.ourmenu.ui.home.component.recommendation.main.HomeMainRecommendation
import com.kuit.ourmenu.ui.home.component.recommendation.sub.HomeSubRecommendation
import com.kuit.ourmenu.ui.home.viewmodel.HomeViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    padding: PaddingValues,
    // TODO: navagation 연결
    viewModel: HomeViewModel = hiltViewModel()
) {

    val scrollState = rememberScrollState()

    val homeData by viewModel.home.collectAsStateWithLifecycle()
    val questionData by viewModel.questionState.collectAsStateWithLifecycle()
    val showDialog by viewModel.showDialog.collectAsStateWithLifecycle()

    val answerImgUrl = homeData.answerImgUrl
    val answerRecommendMenus = homeData.answerRecommendMenus
    val tagRecommendImgUrl = homeData.tagRecommendImgUrl
    val tagRecommendMenus = homeData.tagRecommendMenus
    val otherRecommendImgUrl = homeData.otherRecommendImgUrl
    val otherRecommendMenus = homeData.otherRecommendMenus

    if (showDialog && questionData != null) {
        HomePopUpDialog(
            questionData = questionData!!,
            onAnswerSelected = { selectedAnswer ->
                viewModel.selectAnswer(selectedAnswer)
            },
            onDismissRequest = {
                viewModel.onDialogDismiss()
            },
            onDiceClick = {
                viewModel.refreshQuestion()
            }
        )
    }

    Scaffold(
        topBar = {
            OurMenuAddButtonTopAppBar()
        }
    ) {
        Column(
            modifier = Modifier
                .padding(padding)
//                .padding(innerPadding)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.Start
        ) {

            HomeMainRecommendation(
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 29.dp),
                imgUrl = answerImgUrl,
                homeMainDataList = answerRecommendMenus
            )


            HomeSubRecommendation(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(bottom = 25.dp),
                imgUrl = tagRecommendImgUrl,
                homeSubDataList = tagRecommendMenus
            )

            HomeSubRecommendation(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(bottom = 25.dp),
                imgUrl = otherRecommendImgUrl,
                homeSubDataList = otherRecommendMenus
            )
        }
    }
}

@Preview(
    widthDp = 360,
    heightDp = 800
)
@Composable
private fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(
        padding = PaddingValues(0.dp),
//        viewModel = hiltViewModel(navController = navController)
    )
}
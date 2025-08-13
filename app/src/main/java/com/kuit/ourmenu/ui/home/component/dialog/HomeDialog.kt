package com.kuit.ourmenu.ui.home.component.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil3.compose.SubcomposeAsyncImage
import com.kuit.ourmenu.R
import com.kuit.ourmenu.data.model.home.response.Answer
import com.kuit.ourmenu.data.model.home.response.HomeQuestionResponse
import com.kuit.ourmenu.ui.theme.Neutral500
import com.kuit.ourmenu.ui.theme.Neutral900
import com.kuit.ourmenu.ui.theme.NeutralWhite
import com.kuit.ourmenu.ui.theme.Primary500Main
import com.kuit.ourmenu.ui.theme.ourMenuTypography

@Composable
fun HomePopUpDialog(
    questionData: HomeQuestionResponse,
    onDismissRequest: () -> Unit,
    onAnswerSelected: (String) -> Unit,
    onDiceClick: () -> Unit,
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            modifier = Modifier
                .shadow(elevation = 8.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .background(
                    color = NeutralWhite,
                    shape = RoundedCornerShape(size = 12.dp)
                )
                .clip(RoundedCornerShape(12.dp))
        ) {
            Column(
                modifier = Modifier
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 16.dp,
                        bottom = 24.dp
                    )
                    .fillMaxWidth()
                    .wrapContentHeight()

            ) {
                HomeDialogAssets(
                    onDiceClick = onDiceClick,
                    onCloseClick = onDismissRequest
                )
                Text(
                    modifier = Modifier
                        .padding(top = 13.dp)
                        .fillMaxWidth(),
                    text = stringResource(R.string.hello) + questionData.question,
                    style = ourMenuTypography().pretendard_700_20.copy(
                        color = Neutral900
                    ),
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier
                        .padding(top = 13.dp, bottom = 19.dp)
                        .fillMaxWidth(),
                    text = stringResource(R.string.home_recommend),
                    style = ourMenuTypography().pretendard_500_14.copy(
                        fontWeight = FontWeight(500),
                        color = Neutral500
                    ),
                    textAlign = TextAlign.Center
                )
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    questionData.answers.forEach { answer ->
                        SubcomposeAsyncImage(
                            model = answer.answerImgUrl,
                            contentDescription = answer.answer,
                            modifier = Modifier
                                .width(248.dp)
                                .height(48.dp)
                                .clickable { onAnswerSelected(answer.answer) },
                            loading = {
                                Button(
                                    modifier = Modifier
                                        .width(248.dp)
                                        .height(48.dp),
                                    shape = RoundedCornerShape(8.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Primary500Main,
                                        contentColor = NeutralWhite
                                    ),
                                    onClick = {}
                                ) {

                                }
                            },
                        )
                    }
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0x111111
)
@Composable
private fun HomePopUpDialogPreview() {
    HomePopUpDialog(
        questionData = HomeQuestionResponse(
            question = "스트레스 받을 때는 어떤 음식을 드시나요?",
            answers = listOf(
                Answer(
                    "SWEET",
                    "https://ourmenu-s3-default.s3.ap-northeast-2.amazonaws.com/answers/SWEET.svg"
                ),
                Answer(
                    "SPICY",
                    "https://ourmenu-s3-default.s3.ap-northeast-2.amazonaws.com/answers/SPICY.svg"
                ),
            )
        ),
        onDismissRequest = {},
        onAnswerSelected = {},
        onDiceClick = {}
    )
}
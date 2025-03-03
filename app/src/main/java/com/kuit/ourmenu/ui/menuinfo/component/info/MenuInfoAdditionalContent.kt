package com.kuit.ourmenu.ui.menuinfo.component.info

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.theme.Neutral100
import com.kuit.ourmenu.ui.theme.Neutral500
import com.kuit.ourmenu.ui.theme.Neutral700
import com.kuit.ourmenu.ui.theme.ourMenuTypography

@Composable
fun MenuInfoAdditionalContent(
    address: String,
    memoTitle: String,
    memoContent: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(top = 12.dp)
    ) {
        Text(
            text = stringResource(R.string.info),
            style = ourMenuTypography().pretendard_600_32.copy(
                fontSize = 12.sp,
                lineHeight = 12.sp,
                color = Neutral500
            ),
            modifier = Modifier.padding(bottom = 6.dp)
        )
        Row {
            Icon(
                painter = painterResource(id = R.drawable.ic_fill_map_20),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Text(
                text = address,
                style = ourMenuTypography().pretendard_400_14.copy(
                    lineHeight = 22.sp,
                    color = Neutral700
                ),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 8.dp)
            )
        }

        Text(
            text = stringResource(R.string.memo_eng),
            style = ourMenuTypography().pretendard_600_32.copy(
                fontSize = 12.sp,
                lineHeight = 12.sp,
                color = Neutral500
            ),
            modifier = Modifier.padding(
                top = 13.dp, bottom = 6.dp
            )
        )

        Column(
            modifier = Modifier
                .width(320.dp)
                .background(color = Neutral100, shape = RoundedCornerShape(size = 12.dp))
                .padding(start = 8.dp, top = 12.dp, end = 8.dp, bottom = 12.dp)
        ) {
            Row {
                Icon(
                    painter = painterResource(id = R.drawable.ic_menu_info_meno_20),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.align(Alignment.Top)
                )
                Column(
                    modifier = Modifier.padding(start = 10.dp)
                ) {
                    Text(
                        text = memoTitle,
                        style = ourMenuTypography().pretendard_600_14.copy(
                            lineHeight = 18.sp,
                            color = Neutral700
                        ),
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Text(
                        text = memoContent,
                        style = ourMenuTypography().pretendard_600_12.copy(
                            lineHeight = 18.sp,
                            color = Neutral700
                        ),
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MenuInfoAdditionalContentPreview() {
    MenuInfoAdditionalContent(
        address = "서울 마포구 와우산로 112",
        memoTitle = "매운 맛 선택 가능!",
        memoContent = "* 덕유산 / ㅇㅇ산 / 한라산 \n" +
                "기본도 정말 매우니 조심하기"
    )
}
package com.kuit.ourmenu.ui.signup.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.common.CustomTextField
import com.kuit.ourmenu.ui.theme.Neutral100
import com.kuit.ourmenu.ui.theme.Neutral300
import com.kuit.ourmenu.ui.theme.Neutral500
import com.kuit.ourmenu.ui.theme.Neutral700
import com.kuit.ourmenu.ui.theme.Primary500Main
import com.kuit.ourmenu.ui.theme.ourMenuTypography
import com.kuit.ourmenu.utils.ViewUtil.noRippleClickable

@Composable
fun EmailSpinner(
    modifier: Modifier = Modifier,
    domain : String = "",
    onDomainChange : (String) -> Unit = {}
) {
    var expanded by remember { mutableStateOf(false) }
    var enable by remember { mutableStateOf(false) }
    val domains =
        listOf(
            stringResource(R.string.email_custom),
            stringResource(R.string.email_daum),
            stringResource(R.string.email_gmail),
            stringResource(R.string.email_kakao),
            stringResource(R.string.email_nate),
            stringResource(R.string.email_naver),
        )
    var isFocused by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .width(122.dp)
            .clip(RoundedCornerShape(8.dp))
    ) {
        Column {
            CustomTextField(
                enabled = enable,
                containerColor = Neutral100,
                modifier = modifier
                    .fillMaxWidth()
                    .height(44.dp)
                    .border(
                        width = 1.dp,
                        color = if (isFocused) Neutral500 else Neutral300,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clip(RoundedCornerShape(8.dp))
                    .clickable { expanded = !expanded }
                    .onFocusChanged { focusState ->
                        isFocused = focusState.isFocused
                    },
                paddingValues = PaddingValues(start = 17.5.dp),
                text = domain,
                textStyle = ourMenuTypography().pretendard_700_12.copy(color = Neutral700),
                onTextChange = onDomainChange,
                shape = RoundedCornerShape(8.dp),
                placeHolder = {
                    Text(
                        text = stringResource(R.string.placeholder_email_domain),
                        style = ourMenuTypography().pretendard_700_12.copy(color = Neutral500)
                    )
                },
                cursorColor = Primary500Main
            )
            if (expanded) {
                LazyColumn(
                    modifier = Modifier
                        .width(122.dp)
                        .padding(top = 8.dp)
                        .height(44.dp * domains.size)
                        .clip(RoundedCornerShape(8.dp))
                        .border(1.dp, Neutral300, RoundedCornerShape(8.dp))
                        .background(Neutral100),
                    verticalArrangement = Arrangement.spacedBy(0.dp),
                ) {
                    items(domains.size) { index ->
                        Column {
                            DomainItem(
                                modifier = Modifier
                                    .width(122.dp)
                                    .height(44.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .clickable {
                                        if (index == 0) {
                                            enable = true
                                            onDomainChange("")
                                        } else {
                                            enable = false
                                            onDomainChange(domains[index])
                                        }
                                        expanded = false
                                    }
                                    .padding(horizontal = 17.5.dp),
                                domain = domains[index]
                            )
                        }
                    }
                }
            }
        }
        if (!enable || domain.isEmpty())
            Icon(
                painter =
                if (expanded) painterResource(R.drawable.ic_expand_up)
                else painterResource(R.drawable.ic_expand_down),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(end = 17.5.dp, top = 16.dp)
                    .noRippleClickable { expanded = !expanded },
                tint = Neutral500
            )
    }
}

@Composable
fun DomainItem(
    modifier: Modifier = Modifier,
    domain: String,
) {
    Column(
        modifier = modifier,
    ) {
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 11.5.dp),
            color = Neutral300,
            thickness = 1.dp
        )

        Text(
            text = domain,
            textAlign = TextAlign.Justify,
            lineHeight = 18.sp,
            style = ourMenuTypography().pretendard_700_12.copy(color = Neutral500)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun EmailSpinnerPreview() {
    Box(
        modifier = Modifier
            .width(375.dp)
            .height(812.dp)
    ) {
        EmailSpinner(
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}
package com.kuit.ourmenu.ui.addmenu.component.bottomsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.addmenu.component.item.IconItem
import com.kuit.ourmenu.ui.theme.ourMenuTypography

@Composable
fun IconSelectBottomSheet(modifier: Modifier = Modifier) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 20.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = stringResource(R.string.icon),
                style = ourMenuTypography().pretendard_700_16
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        //아이콘들 추가
        IconItem(
            iconId = R.drawable.ic_tag_rice,
            isSelected = true
        ) {

        }

    }
}

@Preview(showBackground = true)
@Composable
private fun IconSelectBottomSheetPreview() {
    IconSelectBottomSheet()
}
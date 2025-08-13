package com.kuit.ourmenu.ui.addmenu.component.bottomsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.common.BottomHalfWidthButton
import com.kuit.ourmenu.ui.common.IconItemGroup
import com.kuit.ourmenu.ui.theme.Neutral400
import com.kuit.ourmenu.ui.theme.NeutralWhite
import com.kuit.ourmenu.ui.theme.Primary500Main

@Composable
fun IconSelectBottomSheet(
    modifier: Modifier = Modifier,
    iconList: List<Int>,
    onCancel: () -> Unit,
    onConfirm: () -> Unit,
    onIconClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconItemGroup(
            modifier = modifier.fillMaxWidth(),
            groupLabel = "아이콘",
            icons = iconList,
            onIconSelect = onIconClick
        )

        Spacer(modifier = modifier.height(28.dp))

        Row(
            modifier = modifier
                .fillMaxWidth()
            ,
            horizontalArrangement = Arrangement.Center
        ) {
            BottomHalfWidthButton(
                modifier = modifier.weight(1f),
                containerColor = Neutral400,
                contentColor = NeutralWhite,
                text = stringResource(R.string.cancel)
            ) {
                onCancel()
            }
            Spacer(modifier = modifier.width(12.dp))
            BottomHalfWidthButton(
                modifier = modifier.weight(1f),
                containerColor = Primary500Main,
                contentColor = NeutralWhite,
                text = stringResource(R.string.apply)
            ) {
                onConfirm()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun IconSelectBottomSheetPreview() {
    val iconList = listOf(
        R.drawable.ic_tag_rice,
        R.drawable.ic_tag_rice,
        R.drawable.ic_tag_rice,
        R.drawable.ic_tag_rice,
        R.drawable.ic_tag_rice,
        R.drawable.ic_tag_rice,
        R.drawable.ic_tag_rice,
        R.drawable.ic_tag_rice,
        R.drawable.ic_tag_rice,
        R.drawable.ic_tag_rice,
        R.drawable.ic_tag_rice,
        R.drawable.ic_tag_rice,
        R.drawable.ic_tag_rice,
        R.drawable.ic_tag_rice,
        R.drawable.ic_tag_rice,
        R.drawable.ic_tag_rice,
        R.drawable.ic_tag_rice,
        R.drawable.ic_tag_rice,
        R.drawable.ic_tag_rice,
    )
    IconSelectBottomSheet(
        iconList = iconList,
        onCancel = {},
        onConfirm = {}
    ){
        // 아이콘 선택시 동작
    }
}
package com.kuit.ourmenu.ui.menuinfo.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.theme.NeutralWhite

@Composable
fun MenuInfoTopIcons(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_top_bar_back),
            contentDescription = null,
            tint = NeutralWhite,
            modifier = Modifier.align(Alignment.CenterStart)
        )

        Icon(
            painter = painterResource(R.drawable.icon_vert_white_24),
            contentDescription = null,
            tint = NeutralWhite,
            modifier = Modifier.align(Alignment.CenterEnd)
        )
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
private fun MenuInfoPreview() {
    MenuInfoTopIcons(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .height(28.dp)
    )
}
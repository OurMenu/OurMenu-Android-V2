package com.kuit.ourmenu.ui.menuinfo.component.info

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
fun MenuInfoTopIcons(
    onBackClick : () -> Unit,
    onVertClick : () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .height(52.dp)
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_top_bar_back),
            contentDescription = null,
            tint = NeutralWhite,
            modifier = Modifier
                .padding(top = 18.dp)
                .clickable { onBackClick() }
        )

        Icon(
            painter = painterResource(R.drawable.icon_vert_white_24),
            contentDescription = null,
            tint = NeutralWhite,
            modifier = Modifier
                .padding(top = 18.dp)
                .align(Alignment.TopEnd)
                .clickable { onVertClick() }
        )
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
private fun MenuInfoPreview() {
    MenuInfoTopIcons(
        onBackClick = {},
        onVertClick = {}
    )
}
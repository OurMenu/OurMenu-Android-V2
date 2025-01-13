package com.kuit.ourmenu.ui.home.component

import android.widget.ImageButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar(modifier: Modifier = Modifier) {
    // 기본
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth(),
        title = {
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.ic_ourmenu_text_logo),
                contentDescription = "Top App Bar Logo",
                modifier = Modifier.padding(start = 8.dp)
            )
        },
        actions = {
            IconButton(
                onClick = { /* TODO : Add Menu Button Click Event */ },
                modifier = Modifier.padding(end = 20.dp)
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_home_plus),
                    contentDescription = "Top App Bar Add Menu Button"
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun HomeTopAppBarPreview() {
    HomeTopAppBar()
}
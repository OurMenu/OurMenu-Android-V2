package com.kuit.ourmenu.ui.addmenu.component.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.theme.NeutralWhite
import com.kuit.ourmenu.ui.theme.Primary500Main
import com.kuit.ourmenu.ui.theme.ourMenuTypography

@Composable
fun AddMenuAddedImageItem(
    modifier: Modifier = Modifier,
    img: Int,
    isFirstItem: Boolean,
    onDelete: () -> Unit
) {
    Card(
        modifier = modifier
            .size(88.dp, 72.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Box(modifier = modifier.fillMaxSize()) {
            Image(
                modifier = modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(12.dp)),
                painter = painterResource(img),
                contentDescription = "menu image",
                contentScale = ContentScale.Crop
            )

            if (isFirstItem){
                Surface(
                    modifier = modifier
                        .align(Alignment.TopStart)
                        .padding(top = 4.dp, start = 6.dp)
                        .size(40.dp, 24.dp),
                    shape = RoundedCornerShape(8.dp),
                    color = NeutralWhite,
                ) {
                    Box(modifier = modifier.fillMaxSize()) {
                        Text(
                            text = "대표",
                            color = Primary500Main,
                            style = ourMenuTypography().pretendard_600_14,
                            modifier = modifier.align(Alignment.Center)
                        )
                    }
                }
            }

            Image(
                modifier = modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 7.dp, end = 6.dp),
                painter = painterResource(R.drawable.ic_addmenu_x),
                contentDescription = "menu image",
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AddMenuAddedImageItemPreview() {
    Column {
        AddMenuAddedImageItem(img = R.drawable.img_dummy_pizza, isFirstItem = true) {

        }
        AddMenuAddedImageItem(img = R.drawable.img_dummy_pizza, isFirstItem = false) {

        }
    }
}
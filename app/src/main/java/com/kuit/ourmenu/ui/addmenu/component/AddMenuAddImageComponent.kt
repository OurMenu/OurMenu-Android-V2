package com.kuit.ourmenu.ui.addmenu.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.addmenu.component.item.AddMenuAddedImageItem
import com.kuit.ourmenu.ui.theme.Neutral300
import com.kuit.ourmenu.ui.theme.Neutral500
import com.kuit.ourmenu.ui.theme.ourMenuTypography

@Composable
fun AddMenuAddImageComponent(modifier: Modifier = Modifier, imgList: List<Int>, onDelete: () -> Unit) {
    Row(modifier = modifier
        .fillMaxWidth()
    ) {
        Button(
            onClick = { /*TODO*/ },
            modifier = modifier.size(88.dp, 72.dp),
            shape = RoundedCornerShape(12.dp),
            contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Neutral300
            )
        ) {
            Column(
                modifier = modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_addmenu_add_photo),
                    contentDescription = "add photo",
                    tint = Color.Unspecified
                )
                Text(
                    text = "0/5",
                    style = ourMenuTypography().pretendard_500_12,
                    color = Neutral500
                )
            }
        }
        Spacer(modifier = modifier.size(8.dp))
        LazyRow(modifier = modifier.fillMaxWidth()) {
            itemsIndexed(imgList) { index, item ->
                if (index == 0){
                    AddMenuAddedImageItem(img = item, isFirstItem = true) {
                        //onDelete
                    }
                }else{
                    AddMenuAddedImageItem(img = item, isFirstItem = false) {
                        //onDelete
                    }
                }
                Spacer(modifier = modifier.size(8.dp))
            }

        }

    }
}

@Preview(showBackground = true)
@Composable
private fun AddMenuAddImageComponentPreview() {
    val imgList = listOf(
        R.drawable.img_dummy_pizza,
        R.drawable.img_dummy_pizza,
        R.drawable.img_dummy_pizza,
        R.drawable.img_dummy_pizza,
        R.drawable.img_dummy_pizza,
    )
    AddMenuAddImageComponent(imgList = imgList){
        //onDelete
    }
}
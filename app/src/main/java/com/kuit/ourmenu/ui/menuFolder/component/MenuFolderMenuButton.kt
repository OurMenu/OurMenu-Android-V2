package com.kuit.ourmenu.ui.menuFolder.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.theme.Neutral300
import com.kuit.ourmenu.ui.theme.Neutral700
import com.kuit.ourmenu.ui.theme.Neutral900
import com.kuit.ourmenu.ui.theme.ourMenuTypography

@Composable
fun MenuFolderMenuButton(modifier: Modifier = Modifier) {
    val menuPrice = 12000

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(114.dp)
            .padding(horizontal = 20.dp, vertical = 12.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_dummy_pizza),
            contentDescription = "Menu Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxHeight()
                .width(120.dp)
                .clip(RoundedCornerShape(12.dp))
        )

        Spacer(modifier = Modifier.width(16.dp))


        Column {
            Box(
                modifier = Modifier.height(24.dp),
                Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.menu_name),
                    style = ourMenuTypography().pretendard_700_16,
                    color = Neutral900,
                )
            }

            Box(
                modifier = Modifier.height(20.dp),
                Alignment.Center
            ) {
                Row {
                    Text(
                        text = stringResource(R.string.store_name),
                        style = ourMenuTypography().pretendard_500_14,
                        color = Neutral700
                    )

                    Spacer(modifier = Modifier.width(5.dp))

                    Text(
                        text = "·",
                        style = ourMenuTypography().pretendard_500_14,
                        color = Neutral700
                    )

                    Spacer(modifier = Modifier.width(5.dp))

                    Text(
                        text = stringResource(R.string.resaturant_address),
                        style = ourMenuTypography().pretendard_500_14,
                        color = Neutral700
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = String.format(stringResource(R.string.menu_price_won), menuPrice),
                    style = ourMenuTypography().pretendard_500_14,
                    color = Neutral700
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_to_map),
                    contentDescription = "To Map",
                )
            }
        }
    }

    HorizontalDivider(
        thickness = 1.dp,
        color = Neutral300,
        modifier = Modifier
            .fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
private fun MenuFolderMenuButtonPreview() {
    MenuFolderMenuButton()
}
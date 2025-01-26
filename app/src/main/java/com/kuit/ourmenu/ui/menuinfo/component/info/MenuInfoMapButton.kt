package com.kuit.ourmenu.ui.menuinfo.component.info

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.theme.NeutralWhite
import com.kuit.ourmenu.ui.theme.Primary500Main
import com.kuit.ourmenu.ui.theme.ourMenuTypography

@Composable
fun MenuInfoMapButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .shadow(
                elevation = 8.dp,
                spotColor = Color(0x29000000),
                ambientColor = Color(0x29000000)
            )
            .width(124.dp)
            .height(40.dp),
        shape = RoundedCornerShape(20.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = NeutralWhite,
            contentColor = Primary500Main
        ),
        contentPadding = PaddingValues()
    ) {
        Row {
            Text(
                text = stringResource(R.string.menu_info_goto_map),
                style = ourMenuTypography().pretendard_600_16,
                modifier = Modifier.align(CenterVertically)
            )
            Icon(
                painter = painterResource(R.drawable.ic_menu_info_nav_24),
                contentDescription = null,
                tint = Primary500Main,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .align(CenterVertically)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MenuInfoMapButtonPreview() {
    MenuInfoMapButton { }
}
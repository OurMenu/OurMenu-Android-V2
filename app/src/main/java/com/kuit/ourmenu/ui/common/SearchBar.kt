package com.kuit.ourmenu.ui.common

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchBar(
    placeholder: String,
    onSearch: () -> Unit,
) {
    var text by rememberSaveable { mutableStateOf("") }
    Card(
        modifier = Modifier
            .shadow(elevation = 4.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp)
    ) {
        TextField(
            value = text,
            onValueChange = { text = it },
            textStyle = TextStyle(fontSize = 14.sp),
            placeholder = { Text(placeholder, fontSize = 14.sp) },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "SearchIcon"
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .border(0.8.dp, Color.Gray, RoundedCornerShape(8.dp))
                .clip(RoundedCornerShape(8.dp)),
            colors = TextFieldDefaults.colors(
                //cursor 색상, 이후에 색상 지정시 변경하려고 일부로 다른 색 지정
                cursorColor = Color.Blue,
                //text field 색상
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                //indicator 색상
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                //placeholder 색상
                focusedPlaceholderColor = Color.Gray,
                unfocusedPlaceholderColor = Color.Gray,
                //검색 icon 색상
                focusedTrailingIconColor = Color.Gray,
                unfocusedTrailingIconColor = Color.Gray
            ),
            singleLine = true,
        )
    }

}

@Preview(showBackground = true)
@Composable
private fun SearchBarPreview() {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        SearchBar(
            placeholder = "Placeholder",
            onSearch = { }
        )
    }
}
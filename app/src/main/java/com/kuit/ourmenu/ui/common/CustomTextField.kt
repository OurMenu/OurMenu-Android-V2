package com.kuit.ourmenu.ui.common

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


/**
 * @param modifier modifier를 통해 전체 컴포넌트의 크기, border 등을 설정한다.
 * modifier에서 정의한 border는 배경이 아닌 외부 테두리 선에만 영향을 준다
 *
 * @param visualTransformation 입력된 텍스트의 값은 유지하면서, 화면에 보여지는 방식을 변경시킬 때 사용하는 파라미터이다.
 * 예를 들어서 비밀번호 입력 필드로 사용한다면,
 * PasswordVisualTransformation()을 인자로 주어 실제 값 대신 마스킹 된 값을 보여주도록 설정할 수 있다.
 * VisualTransformation을 직접 정의하여 커스텀할 수도 있다. (숫자에서 3자리 단위로 , 를 찍는 것과 같은 포맷팅 등)
 *
 * @param enabled 텍스트 필드의 활성화 여부를 나타내는 파라미터이고, true인 경우에는 입력이 가능하다
 *
 * @param singleLine 입력된 텍스트를 한 줄로만 표시할지의 여부를 나타낸다. 기본값은 true
 *
 * @param shape  배경, 내부영역을 포함한 컴포넌트의 전체적인 모양 처리
 * ex) RoundedCornerShape(8.dp) 등
 *
 * @param trailingIcon 컴포넌트의 우측 끝에 위치하게 할 Icon 객체를 lambda를 통해 넘겨준다
 *
 * @param leadingIcon 컴포넌트의 왼쪽 끝에 위치하게 할 Icon 객체를 lambda를 통해 넘겨준다
 *
 * @param placeHolder placeholder로 사용할 Text 객체를 lambda를 통해 넘겨준다
 *
 * @param textStyle 입력한 텍스트를 보여줄 스타일에 대한 TextStyle 객체를 통해 정의한다
 *
 * @param paddingValues 컴포넌트의 텍스트 부분에 대한 padding 값을 PaddingValues 객체를 통해 정의한다
 *
 * @param containerColor 컴포넌트의 색상을 Color 객체를 통해 지정한다
 * 기본 값으로는 focused, unfocused에 대한 값을 동일하게 Color.White로 지정
 *
 * @param cursorColor 커서에 대한 색상을 Color 객체를 통해 지정한다
 * 기본 값으로는 Color.Black으로 지정
 *
 * @param keyboardOptions 키보드에 대한 설정을 KeyboardOptions를 통해 지정한다
 *
 * @param keyboardActions 키보드에서 특정 버튼을 눌렀을 때의 작업을 keyboardActions를 통해 지정한다
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    enabled: Boolean = true,
    singleLine: Boolean = true,
    shape: Shape = TextFieldDefaults.shape,
    trailingIcon: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    placeHolder: @Composable (() -> Unit)? = null,
    textStyle: TextStyle = TextStyle.Default,
    paddingValues: PaddingValues = PaddingValues(0.dp),
    containerColor: Color = Color.White,
    cursorColor: Color = Color.Black,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    //Basic TextField의 상호작용 상태를 추적하려면 사용 가능
    val interactionSource = remember {
        MutableInteractionSource()
    }

    //입력된 text 값을 저장하는 state
    var text by rememberSaveable { mutableStateOf("") }

    BasicTextField(
        value = text,
        onValueChange = { text = it },
        modifier = modifier,
        visualTransformation = visualTransformation,
        interactionSource = interactionSource,
        enabled = enabled,
        singleLine = singleLine,
        textStyle = textStyle,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions
    ) { innerTextField ->

        TextFieldDefaults.DecorationBox(
            value = text,
            visualTransformation = visualTransformation,
            innerTextField = innerTextField,
            singleLine = singleLine,
            enabled = enabled,
            interactionSource = interactionSource,
            contentPadding = paddingValues,
            trailingIcon = trailingIcon,
            placeholder = placeHolder,
            leadingIcon = leadingIcon,
            shape = shape,
            colors = TextFieldDefaults.colors(
                /*
                * 이곳에서 enabled 여부, focus 여부 등에 따른 색상, indicator(밑줄)의 색상 등을 따로 지정할 수 있다.
                * */
                focusedContainerColor = containerColor,
                unfocusedContainerColor = containerColor,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = cursorColor
            )

        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CustomTextFieldPreview() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomTextField(
            modifier = Modifier
                .fillMaxWidth()
                .border(0.8.dp, Color.Gray, RoundedCornerShape(8.dp))
            ,
            shape = RoundedCornerShape(8.dp),
            placeHolder = { Text("placeholder", fontSize = 20.sp) },
            textStyle = TextStyle(fontSize = 20.sp, color = Color.Black),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "SearchIcon"
                )
            },
            paddingValues = PaddingValues(20.dp, 0.dp)
        )
    }
}

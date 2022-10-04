package com.emmanull.ibstest.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.emmanull.ibstest.ui.theme.LightPrimaryColor

@Composable
fun IbsTextField(
    modifier: Modifier = Modifier,
    onTextChanged: (String) -> Unit,
    onKeyboardOptionsDone: (() -> Unit?)? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    endIcon: @Composable (() -> Unit)? = null,
    isPasswordIconClicked: Boolean = false,
    textColor: Color = Color.Black,
    placeholder: @Composable (() -> Unit)? = null,
    isError: Boolean = false
) {
    val textValue = remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val visualTransformation = if (isPasswordIconClicked) {
        PasswordVisualTransformation()
    } else {
        VisualTransformation.None
    }

    OutlinedTextField(
        modifier = modifier,
        shape = RoundedCornerShape(20),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            disabledBorderColor = LightPrimaryColor,
            textColor = textColor,
            focusedBorderColor = LightPrimaryColor,
            focusedLabelColor = LightPrimaryColor,
            cursorColor = LightPrimaryColor,
            backgroundColor = LightPrimaryColor,
            errorBorderColor = Color.Red
        ),
        isError = isError,
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType
            = keyboardType, imeAction = ImeAction.Done
        ),
        maxLines = 1,
        value = textValue.value,
        onValueChange = { valueChanged ->
            textValue.value = valueChanged
            onTextChanged(valueChanged)
        },
        trailingIcon = endIcon,
        keyboardActions = KeyboardActions(onDone = {
            onKeyboardOptionsDone?.invoke()
            focusManager.clearFocus()
        }),
        placeholder = placeholder
    )
}
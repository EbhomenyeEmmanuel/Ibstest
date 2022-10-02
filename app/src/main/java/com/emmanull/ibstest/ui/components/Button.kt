package com.emmanull.ibstest.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun IbsButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
    color: Color = Color.Black, textColor: Color = Color.White
) {
    Button(
        onClick = { onClick() },
        shape = RoundedCornerShape(20),
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(20.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = color)
    ) {
        Text(
            text = text,
            color = textColor,
            style = MaterialTheme.typography.button,
        )
    }
}


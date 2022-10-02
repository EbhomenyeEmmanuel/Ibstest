package com.emmanull.ibstest.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.emmanull.ibstest.R
import com.emmanull.ibstest.ui.components.IbsButton
import com.emmanull.ibstest.ui.components.IbsTextField

@Composable
fun HomeScreenRoute(
    onNavigate: (String) -> Unit,
    homeViewModel: HomeViewModel = viewModel()
) {

    HomeScreen()

}

@Composable
private fun HomeScreen() {
    val focusManager = LocalFocusManager.current
    var searchKey by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .background(Color.White)
            .wrapContentSize()
            .padding(32.dp)
            .clickable { focusManager.clearFocus() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = stringResource(id = R.string.search),
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center,
            color = Color.Black,
            modifier = Modifier
                .wrapContentSize()
                .padding(bottom = 16.dp)
        )

        IbsTextField(
            onTextChanged = { searchKey = it },
            placeholder = { Text(text = stringResource(R.string.search_hint)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 20.dp),
        )

    }
}
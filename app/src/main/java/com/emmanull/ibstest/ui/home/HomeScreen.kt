package com.emmanull.ibstest.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.emmanull.ibstest.R
import com.emmanull.ibstest.ui.components.IbsTextField

@Composable
fun HomeScreenRoute(
    onNavigate: (String) -> Unit,
    homeViewModel: HomeViewModel = viewModel()
) {
    val list = homeViewModel.homeList
    HomeScreen(list) {
        homeViewModel.onSearchHomeItems()
    }

}

@Composable
private fun HomeScreen(list: List<HomeItem>, onSearch: (String) -> Unit) {
    val focusManager = LocalFocusManager.current
    var searchKey by rememberSaveable { mutableStateOf("") }

    LazyColumn(
        modifier = Modifier
            .background(Color.White)
            .wrapContentSize()
            .padding(32.dp)
            .clickable { focusManager.clearFocus() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        item {
            Text(
                text = stringResource(id = R.string.search),
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.Black,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(bottom = 8.dp)
            )
        }

        item {
            IbsTextField(
                onTextChanged = { searchKey = it },
                placeholder = { Text(text = stringResource(R.string.search_hint)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 20.dp),
                onKeyboardOptionsDone = {
                    onSearch(searchKey)
                }
            )
        }

        item {
            list.forEach {
                HomeContentItem(homeItem = it)
            }
        }

    }
}

@Composable
private fun HomeContentItem(homeItem: HomeItem) {
    with(homeItem) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 8.dp)
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                color = Color.Black,
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Account No. $accountNumber",
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Start,
                color = Color.Black,
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Phone No. $phone",
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Start,
                color = Color.Black,
            )
            Spacer(modifier = Modifier.height(6.dp))

            Divider(color = Color.Black, thickness = 1.dp)

        }
    }
}
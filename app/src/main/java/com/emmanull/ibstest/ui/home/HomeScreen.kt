package com.emmanull.ibstest.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.emmanull.ibstest.R
import com.emmanull.ibstest.domain.model.HomeUiState
import com.emmanull.ibstest.navigation.Route
import com.emmanull.ibstest.ui.components.IbsTextField
import com.emmanull.ibstest.utils.shortToast
import com.skydoves.landscapist.glide.GlideImage

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun HomeScreenRoute(
    onNavigate: (String) -> Unit,
    homeViewModel: HomeViewModel = viewModel()
) {
    val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()

    HomeScreen(uiState, onSearch = { homeViewModel.search(it) }, onNavigate = {
        onNavigate(it)
        homeViewModel.onErrorShown()
    })

}

@Composable
private fun HomeScreen(
    homeUiState: HomeUiState,
    onSearch: (String) -> Unit,
    onNavigate: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current
    var searchKey by rememberSaveable { mutableStateOf("") }
    val context = LocalContext.current

    homeUiState.errorMessage?.let {
        DisposableEffect(key1 = it) {
            context.shortToast(it)
            //Chose to navigate when error message is shown because list is in memory not cached
            onNavigate(Route.LoginRoute.route)
            onDispose {
            }
        }
    }

    if (homeUiState.isLoading) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(50.dp)
                    .fillMaxSize()
            )
        }
    } else if (homeUiState.homeList?.isEmpty() == false) {
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
                    onTextChanged = {
                        searchKey = it
                        onSearch(it)
                    },
                    placeholder = { Text(text = stringResource(R.string.search_hint)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, bottom = 20.dp),
                    onKeyboardOptionsDone = {
                        onSearch(searchKey)
                    }
                )
            }

            items(homeUiState.homeList!!) {
                HomeContentItem(homeItem = it)
            }
        }
    }
}

@Composable
private fun HomeContentItem(homeItem: HomeItem) {
    with(homeItem) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
            ) {

                GlideImage(
                    imageModel = icon,
                    loading = {
                        CircularProgressIndicator()
                    },
                    failure = {
                        CircularProgressIndicator(
                            color = Color.Red,
                        )
                    }
                )
            }

            Spacer(modifier = Modifier.width(5.dp))

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
                    text = "Email No. $email",
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
}
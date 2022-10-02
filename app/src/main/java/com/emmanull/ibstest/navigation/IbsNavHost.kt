package com.emmanull.ibstest.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.emmanull.ibstest.ui.auth.LoginScreenRoute
import com.emmanull.ibstest.ui.home.HomeScreenRoute

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun IbsNavHost(
//windowSizeClass: WindowSizeClass,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Route.LoginRoute.route
) {

    NavHost(
        navController = navController, startDestination = startDestination,
        modifier = modifier
    ) {

        composable(Route.LoginRoute.route) {
            LoginScreenRoute(onNavigate = {
                navController.navigate(it)
            })
        }

        composable(Route.HomeRoute.route) {
            HomeScreenRoute(onNavigate = {
                navController.navigate(it)
            })
        }
    }
}
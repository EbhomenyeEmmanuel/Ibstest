package com.emmanull.ibstest.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

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


    }
}
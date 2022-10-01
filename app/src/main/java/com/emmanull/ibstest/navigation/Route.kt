package com.emmanull.ibstest.navigation

sealed class Route(val route: String){

    object LoginRoute: Route("login")

    object HomeRoute: Route("home")
}
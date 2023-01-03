package furkan.hello_compose

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun MyAppNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ScreenNames.MAIN_PAGE.name) {
        composable(ScreenNames.MAIN_PAGE.name) {
            HelloCompose(navController)
        }
        composable(
            "${ScreenNames.RESULT_PAGE.name}/{${NavigationKeys.RESULT.code}}",
            arguments = listOf(navArgument(name = NavigationKeys.RESULT.code) {
                type = NavType.StringType
            })
        ) {
            val result = it.arguments?.getString(NavigationKeys.RESULT.code)
            ResultScreen(navController, result.toString())
        }
    }
}
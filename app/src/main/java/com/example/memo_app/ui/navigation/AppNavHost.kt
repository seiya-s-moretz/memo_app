package com.example.memo_app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.memo_app.ui.screen.NoteEditContainer
import com.example.memo_app.ui.screen.NoteListContainer

/**
 * AppNavHost
 * Navigation定義
 */

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = NavRoutes.LIST
    ) {
        composable(NavRoutes.LIST) {
            NoteListContainer(
                onNavigationToEdit = { id ->
                    val route =
                        if (id != null) "${NavRoutes.EDIT}?id=$id" else "${NavRoutes.EDIT}?id=-1"
                    navController.navigate(route)
                }
            )
        }
        composable(
            route = "${NavRoutes.EDIT}?id={id}",
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
                defaultValue = -1
            })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id")
                ?.takeIf { it != -1 }
            NoteEditContainer(
                noteId = id,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}
package com.an9ar.jetsimpsons

import android.content.Context
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.an9ar.jetsimpsons.repositories.SimpsonsRepository
import com.an9ar.jetsimpsons.screens.EpisodeCardScreen
import com.an9ar.jetsimpsons.screens.EpisodesListScreen
import com.an9ar.jetsimpsons.screens.SplashScreen
import com.an9ar.jetsimpsons.ui.DSTheme

@Composable
fun JetSimpsonsApp(context: Context, repository: SimpsonsRepository) {
    DSTheme {
        Surface(color = DSTheme.colors.background) {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "splash") {
                composable("splash") {
                    SplashScreen(
                            navController = navController,
                            assets = context.assets,
                            repository = repository
                    )
                }
                composable("seriesList") {
                    EpisodesListScreen(navController = navController, repository = repository)
                }
                composable(
                        "episode/{episodeId}",
                        arguments = listOf(navArgument("episodeId") { type = NavType.LongType })
                ) { backStackEntry ->
                    backStackEntry.arguments?.getLong("episodeId")?.let { id ->
                        EpisodeCardScreen(navController = navController, repository = repository, id)
                    }
                }
            }
        }
    }
}

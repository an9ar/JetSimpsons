package com.an9ar.jetsimpsons

import android.content.res.AssetManager
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.an9ar.jetsimpsons.screens.EpisodeCardScreen
import com.an9ar.jetsimpsons.screens.EpisodesListScreen
import com.an9ar.jetsimpsons.screens.SplashScreen
import com.an9ar.jetsimpsons.theme.DSTheme
import com.an9ar.jetsimpsons.viewmodels.EpisodesViewModel

@Composable
fun JetSimpsonsApp(assets: AssetManager, episodesViewModel: EpisodesViewModel) {
    DSTheme {
        Surface(color = DSTheme.colors.background) {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "splash") {
                composable("splash") {
                    SplashScreen(
                            navHostController = navController,
                            assets = assets,
                            episodesViewModel = episodesViewModel
                    )
                }
                composable("seriesList") {
                    EpisodesListScreen(navHostController = navController, episodesViewModel = episodesViewModel)
                }
                composable(
                        "episode/{episodeId}",
                        arguments = listOf(navArgument("episodeId") { type = NavType.LongType })
                ) { backStackEntry ->
                    backStackEntry.arguments?.getLong("episodeId")?.let { id ->
                        EpisodeCardScreen(navHostController = navController, episodesViewModel = episodesViewModel, episodeId = id)
                    }
                }
            }
        }
    }
}

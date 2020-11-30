package com.an9ar.jetsimpsons

import androidx.compose.material.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.an9ar.jetsimpsons.repositories.SimpsonsRepository
import com.an9ar.jetsimpsons.ui.DSTheme
import dev.chrisbanes.accompanist.glide.GlideImage

@Composable
fun EpisodesListScreen(
        navController: NavHostController,
        repository: SimpsonsRepository
) {
    val episodes = repository.getEpisodesList()
    EpisodesGridList(items = episodes, navController = navController)
}

@Composable
fun EpisodesGridList(
        items: List<Episode>,
        navController: NavHostController
) {
    LazyGridFor(
            items = items,
            rows = 2,
            hPadding = 8
    ) { episode, index ->
        EpisodeCard(navController = navController, episode = episode)
    }
}

@Composable
fun EpisodeCard(
        navController: NavHostController,
        episode: Episode
) {
    Card(
            backgroundColor = DSTheme.colors.card,
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.clickable(onClick = { navController.navigate("episode/${episode.id}") }).fillMaxWidth()
    ) {
        ConstraintLayout {
            val (simpsonNameRef, simpsonPhotoRef) = createRefs()
            GlideImage(
                    data = episode.image_url,
                    fadeIn = true,
                    contentScale = ContentScale.FillBounds,
                    loading = {
                        Box(Modifier.fillMaxSize()) {
                            CircularProgressIndicator(Modifier.align(Alignment.Center))
                        }
                    },
                    modifier = Modifier.height(120.dp).constrainAs(simpsonPhotoRef) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
            Text(
                    text = episode.title,
                    style = DSTheme.typography.textSmallBold,
                    color = DSTheme.colors.text,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp).constrainAs(simpsonNameRef) {
                        top.linkTo(simpsonPhotoRef.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
        }
    }
}
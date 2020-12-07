package com.an9ar.jetsimpsons.screens

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
import com.an9ar.jetsimpsons.LazyGridFor
import com.an9ar.jetsimpsons.data.Episode
import com.an9ar.jetsimpsons.repositories.SimpsonsRepository
import com.an9ar.jetsimpsons.theme.DSTheme
import dev.chrisbanes.accompanist.glide.GlideImage

@Composable
fun EpisodesListScreen(
        navHostController: NavHostController,
        repository: SimpsonsRepository
) {
    val episodes = repository.getEpisodesList()
    EpisodesGridList(items = episodes, navHostController = navHostController)
}

@Composable
fun EpisodesGridList(
        items: List<Episode>,
        navHostController: NavHostController
) {
    LazyGridFor(
            items = items,
            rows = 2,
            hPadding = 8
    ) { episode, index ->
        EpisodeCard(navHostController = navHostController, episode = episode)
    }
}

@Composable
fun EpisodeCard(
        navHostController: NavHostController,
        episode: Episode
) {
    Card(
            backgroundColor = DSTheme.colors.card,
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.clickable(onClick = { navHostController.navigate("episode/${episode.id}") }).fillMaxWidth()
    ) {
        ConstraintLayout {
            val (simpsonNameRef, simpsonPhotoRef, imdbRating) = createRefs()
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
            Text(
                    text = "IMDB ${episode.imdb_rating}",
                    style = DSTheme.typography.textSmallBold,
                    color = DSTheme.colors.light,
                    modifier = Modifier.constrainAs(imdbRating) {
                        bottom.linkTo(simpsonPhotoRef.bottom, 4.dp)
                        end.linkTo(simpsonPhotoRef.end, 4.dp)
                    }
            )
        }
    }
}
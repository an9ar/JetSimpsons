package com.an9ar.jetsimpsons.screens

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.material.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.an9ar.jetsimpsons.data.Episode
import com.an9ar.jetsimpsons.theme.DSTheme
import com.an9ar.jetsimpsons.viewmodels.EpisodesViewModel
import dev.chrisbanes.accompanist.glide.GlideImage

@Composable
fun EpisodeCardScreen(
        navHostController: NavHostController,
        episodesViewModel: EpisodesViewModel,
        episodeId: Long
) {
    Scaffold(
            topBar = {
                TopAppBar(
                        title = {
                            Text(
                                    text = "Back to others",
                                    style = DSTheme.typography.textMediumBold,
                                    color = DSTheme.colors.text
                            )
                        },
                        navigationIcon = {
                            IconButton(onClick = { navHostController.navigateUp() }) {
                                Icon(Icons.Filled.ArrowBack)
                            }
                        },
                        backgroundColor = DSTheme.colors.toolbar
                )
            },
            bodyContent = { innerPadding ->
                val modifier = Modifier.padding(innerPadding)
                val targetEpisode = episodesViewModel.getEpisodeById(episodeId)
                targetEpisode?.let { EpisodeCardContent(it, modifier) }
            }
    )

}

@Composable
fun EpisodeCardContent(
        episode: Episode,
        modifier: Modifier
) {
    ScrollableColumn(modifier = modifier.background(DSTheme.colors.background).fillMaxSize()) {
        EpisodeImage(url = episode.image_url)
        Spacer(modifier = Modifier.preferredHeight(16.dp))
        EpisodeInfo(episode = episode)
    }
}

@Composable
fun EpisodeImage(url: String) {
    Card(
            shape = RoundedCornerShape(bottomLeft = 32.dp, bottomRight = 32.dp),
            backgroundColor = DSTheme.colors.card,
            modifier = Modifier.fillMaxWidth().height(240.dp)
    ) {
        GlideImage(
                data = url,
                fadeIn = true,
                contentScale = ContentScale.FillBounds,
                loading = {
                    Box(Modifier.fillMaxSize()) {
                        CircularProgressIndicator(Modifier.align(Alignment.Center))
                    }
                },
                modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun EpisodeInfo(episode: Episode) {
    Card(
            shape = RoundedCornerShape(size = 32.dp),
            backgroundColor = DSTheme.colors.card,
            modifier = Modifier.fillMaxSize()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            EpisodeNameTitle(episodeTitle = episode.title)
            Divider(color = DSTheme.colors.background)
            Spacer(modifier = Modifier.preferredHeight(8.dp))
            EpisodeDescriptionItem("IMDb rating", episode.imdb_rating.toString())
            EpisodeDescriptionItem("IMDb votes:", episode.imdb_votes.toString())
            EpisodeDescriptionItem("Number in season:", episode.number_in_season.toString())
            EpisodeDescriptionItem("Number in series:", episode.number_in_series.toString())
            EpisodeDescriptionItem("Air date:", episode.original_air_date)
            EpisodeDescriptionItem("Air year:", episode.original_air_year.toString())
            EpisodeDescriptionItem("Production code:", episode.production_code)
            EpisodeDescriptionItem("Season:", episode.season.toString())
            EpisodeDescriptionItem("Views:", episode.views.toString())
            Spacer(modifier = Modifier.preferredHeight(8.dp))
        }
    }
}

@Composable
fun EpisodeNameTitle(episodeTitle: String) {
    Text(
            text = episodeTitle,
            style = DSTheme.typography.textLargeBold,
            color = DSTheme.colors.text,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 32.dp).fillMaxWidth()
    )
}

@Composable
fun EpisodeDescriptionItem(
        descriptionParameter: String,
        descriptionValue: String
) {
    Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
                text = descriptionParameter,
                style = DSTheme.typography.textMediumBold,
                color = DSTheme.colors.text
        )
        Text(
                text = descriptionValue,
                style = DSTheme.typography.textMedium,
                color = DSTheme.colors.text,
                modifier = Modifier.padding(start = 16.dp)
        )
    }
}
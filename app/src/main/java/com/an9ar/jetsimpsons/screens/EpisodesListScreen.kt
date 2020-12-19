package com.an9ar.jetsimpsons.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.an9ar.jetsimpsons.ui.LazyGridFor
import com.an9ar.jetsimpsons.data.Episode
import com.an9ar.jetsimpsons.theme.DSTheme
import com.an9ar.jetsimpsons.ui.AnimatedListTypeButton
import com.an9ar.jetsimpsons.models.ListType
import com.an9ar.jetsimpsons.viewmodels.EpisodesViewModel
import dev.chrisbanes.accompanist.glide.GlideImage

@Composable
fun EpisodesListScreen(
        navHostController: NavHostController,
        episodesViewModel: EpisodesViewModel
) {
    val episodes = episodesViewModel.episodesList.observeAsState()
    episodes.value?.let { EpisodesListContent(items = it, navHostController = navHostController, episodesViewModel = episodesViewModel) }
}

@Composable
fun EpisodesListContent(
        items: List<Episode>,
        navHostController: NavHostController,
        episodesViewModel: EpisodesViewModel
) {
    val listItemType = episodesViewModel.episodesListType.observeAsState(ListType.NONE)
    Scaffold(
            topBar = {
                TopAppBar(backgroundColor = DSTheme.colors.toolbar) {
                    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                        val (screenTitle, itemSortingType) = createRefs()
                        Text(
                                text = "Simpsons episodes",
                                style = DSTheme.typography.textMediumBold,
                                color = DSTheme.colors.text,
                                modifier = Modifier.constrainAs(screenTitle) {
                                    top.linkTo(parent.top)
                                    bottom.linkTo(parent.bottom)
                                    start.linkTo(parent.start, 12.dp)
                                }
                        )
                        AnimatedListTypeButton(
                                listTypeState = listItemType.value,
                                onListTypeChanged = { listItemType ->
                                    episodesViewModel.setEpisodesListType(listItemType)
                                },
                                modifier = Modifier.constrainAs(itemSortingType) {
                                    top.linkTo(parent.top)
                                    bottom.linkTo(parent.bottom)
                                    end.linkTo(parent.end, 24.dp)
                                }
                        )
                    }
                }
            }
    ) {
        Surface(color = DSTheme.colors.background) {
            when (listItemType.value) {
                ListType.GRID -> EpisodesGridList(items = items, navHostController = navHostController)
                ListType.LINEAR -> EpisodesLinearList(items = items, navHostController = navHostController)
                ListType.NONE -> EpisodesGridList(items = items, navHostController = navHostController)
            }
        }
    }
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
            modifier = Modifier
                    .clickable(onClick = {
                        navHostController.navigate("episode/${episode.id}")
                    })
                    .fillMaxWidth()
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

@Composable
fun EpisodesLinearList(
        items: List<Episode>,
        navHostController: NavHostController
) {
    LazyColumn {
        items(items) { episode ->
            EpisodeListItem(navHostController = navHostController, episode = episode)
        }
    }
}

@Composable
fun EpisodeListItem(
        navHostController: NavHostController,
        episode: Episode
) {
    Card(
            backgroundColor = DSTheme.colors.card,
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                    .padding(4.dp)
                    .clickable(onClick = {
                        navHostController.navigate("episode/${episode.id}")
                    })
                    .fillMaxWidth()
    ) {
        Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
            GlideImage(
                    data = episode.image_url,
                    fadeIn = true,
                    contentScale = ContentScale.FillBounds,
                    loading = {
                        Box(Modifier.fillMaxSize()) {
                            CircularProgressIndicator(Modifier.align(Alignment.Center))
                        }
                    },
                    modifier = Modifier.height(90.dp).width(120.dp)
            )
            Text(
                    text = episode.title,
                    style = DSTheme.typography.textSmallBold,
                    color = DSTheme.colors.text,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxSize()
            )
        }
    }
}
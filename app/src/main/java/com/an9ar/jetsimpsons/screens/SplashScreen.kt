package com.an9ar.jetsimpsons.screens

import android.content.res.AssetManager
import androidx.compose.foundation.background
import androidx.compose.material.*
import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.font
import androidx.compose.ui.text.font.fontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.an9ar.jetsimpsons.R
import com.an9ar.jetsimpsons.data.getEpisodesList
import com.an9ar.jetsimpsons.repositories.SimpsonsRepository
import com.an9ar.jetsimpsons.ui.DSTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
        navHostController: NavHostController,
        assets: AssetManager,
        repository: SimpsonsRepository
) {
    SplashContent()
    LaunchedEffect(subject = Dispatchers.IO) {
        val listOfEpisodes = getEpisodesList(assets = assets)
        repository.setEpisodesList(listOfEpisodes)
        delay(1500)
        navHostController.navigate("seriesList")
    }
}

@Composable
fun SplashContent() {
    ConstraintLayout(
            modifier = Modifier
                    .background(color = DSTheme.colors.background)
                    .fillMaxSize()
    ) {
        val (appLogoRef, loadingRef, authorSignatureRef) = createRefs()

        OutlinedText(
                value = "The JetSimpsons",
                textColor = DSTheme.colors.textAccent,
                textSize = 32,
                modifier = Modifier.constrainAs(appLogoRef) {
                    linkTo(
                            top = parent.top,
                            bottom = parent.bottom,
                            start = parent.start,
                            end = parent.end
                    )
                }
        )

        OutlinedCircularProgress(
                progressColor = DSTheme.colors.textAccent,
                modifier = Modifier.constrainAs(loadingRef) {
                    top.linkTo(appLogoRef.bottom, 32.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        OutlinedText(
                value = "by Grigoriev Dmitriy",
                textColor = DSTheme.colors.textAccent,
                textSize = 16,
                modifier = Modifier.constrainAs(authorSignatureRef) {
                    bottom.linkTo(parent.bottom, 16.dp)
                    end.linkTo(parent.end, 16.dp)
                }
        )
    }
}

@Composable
fun OutlinedText(value: String, textColor: Color, textSize: Int, modifier: Modifier) {
    ConstraintLayout(modifier = modifier) {
        val (text, outline) = createRefs()
        Text(
                color = DSTheme.colors.dark,
                text = value,
                style = TextStyle(
                        fontFamily = fontFamily(font(R.font.simpsons_font)),
                        fontWeight = FontWeight.W900,
                        fontSize = textSize.sp
                ),
                modifier = Modifier.constrainAs(outline) {}
        )
        Text(
                color = textColor,
                text = value,
                style = TextStyle(
                        fontFamily = fontFamily(font(R.font.simpsons_font)),
                        fontWeight = FontWeight.W900,
                        fontSize = textSize.sp
                ),
                modifier = Modifier.constrainAs(text) {
                    top.linkTo(outline.top, 1.dp)
                    bottom.linkTo(outline.bottom, 4.dp)
                    start.linkTo(outline.start, 4.dp)
                    end.linkTo(outline.end, 1.dp)
                }
        )
    }
}

@Composable
fun OutlinedCircularProgress(progressColor: Color, modifier: Modifier) {
    ConstraintLayout(modifier = modifier) {
        val (progress, outline) = createRefs()
        CircularProgressIndicator(
                color = progressColor,
                strokeWidth = 3.dp,
                modifier = Modifier.constrainAs(progress) {}
        )
        CircularProgressIndicator(
                color = DSTheme.colors.dark,
                strokeWidth = 1.dp,
                modifier = Modifier.constrainAs(outline) {
                    top.linkTo(progress.top)
                    bottom.linkTo(progress.bottom)
                    start.linkTo(progress.start)
                    end.linkTo(progress.end)
                }
        )
    }
}
package com.an9ar.jetsimpsons

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.an9ar.jetsimpsons.viewmodels.EpisodesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val episodesViewModel: EpisodesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetSimpsonsApp(
                    assets = applicationContext.assets,
                    episodesViewModel = episodesViewModel
            )
        }
    }
}
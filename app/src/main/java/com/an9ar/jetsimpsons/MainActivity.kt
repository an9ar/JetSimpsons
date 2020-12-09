package com.an9ar.jetsimpsons

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import com.an9ar.jetsimpsons.repositories.SimpsonsRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var simpsonsRepository: SimpsonsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetSimpsonsApp(
                    context = applicationContext,
                    repository = simpsonsRepository
            )
        }
    }
}
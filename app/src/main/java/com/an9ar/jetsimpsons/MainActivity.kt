package com.an9ar.jetsimpsons

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import com.an9ar.jetsimpsons.repositories.SimpsonsRepository
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var simpsonsRepository: SimpsonsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
        setContent {
            JetSimpsonsApp(
                    context = applicationContext,
                    repository = simpsonsRepository
            )
        }
    }

    fun inject() {
        App.appComponent.inject(this)
    }
}
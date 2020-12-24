package com.an9ar.jetsimpsons.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.runtime.staticAmbientOf

@Composable
fun DSTheme(
        darkTheme: Boolean = isSystemInDarkTheme(),
        typography: DSTypography = DSTypography(),
        content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) dsDarkColorPalette() else dsLightColorPalette()
    Providers(
            AmbientDSColor provides colors,
            AmbientDSTypography provides typography,
    ) {
        MaterialTheme(
                colors = colors.materialColors,
                typography = typography.materialTypography
        ) {
            content()
        }
    }
}

object DSTheme {
    @Composable
    val colors: DSColorPalette
        get() = AmbientDSColor.current

    @Composable
    val typography: DSTypography
        get() = AmbientDSTypography.current

    @Composable
    val sizes: DSSizes
        get() = DSSizes()
}

internal val AmbientDSColor = staticAmbientOf { dsLightColorPalette() }
internal val AmbientDSTypography = staticAmbientOf { DSTypography() }
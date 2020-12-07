package com.an9ar.jetsimpsons.ui.theme

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
            DSColorAmbient provides colors,
            DSTypographyAmbient provides typography,
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
        get() = DSColorAmbient.current

    @Composable
    val typography: DSTypography
        get() = DSTypographyAmbient.current

    @Composable
    val sizes: DSSizes
        get() = DSSizes()
}

internal val DSColorAmbient = staticAmbientOf { dsLightColorPalette() }
internal val DSTypographyAmbient = staticAmbientOf { DSTypography() }
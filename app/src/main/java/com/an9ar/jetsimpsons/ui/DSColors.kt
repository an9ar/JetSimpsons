package com.an9ar.jetsimpsons.ui

import androidx.compose.material.Colors
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

object DSColors {
    val primary = Color(0xFF3366FF)
    val background = Color(0xFFE7E7E7)
    val backgroundReverse = Color(0xFF141414)
    val card = Color(0xFFFFFFFF)
    val cardReverse = Color(0xFF252525)
    val textAccent = Color(0xFFFeFB1F)
    val text = Color(0xFF192038)
    val textReverse = Color(0xFFEEEEEE)
    val success = Color(0xFF00E096)
    val warning = Color(0xFFFFAA00)
    val error = Color(0xFFFF3D71)
    val light = Color(0xFFEEEEEE)
    val dark = Color(0xFF1E1E1E)
}

interface DSColorPalette {
    val primary: Color
    val background: Color
    val textAccent: Color
    val text: Color
    val card: Color
    val success: Color
    val warning: Color
    val error: Color
    val light: Color
    val dark: Color

    val materialColors: Colors
}

fun dsLightColorPalette(): DSColorPalette = object : DSColorPalette {
    override val primary: Color = DSColors.primary
    override val background: Color = DSColors.background
    override val textAccent: Color = DSColors.textAccent
    override val text: Color = DSColors.text
    override val card: Color = DSColors.card
    override val success: Color = DSColors.success
    override val warning: Color = DSColors.warning
    override val error: Color = DSColors.error
    override val light: Color = DSColors.light
    override val dark: Color = DSColors.dark

    override val materialColors: Colors = lightColors(
            primary = DSColors.primary,
            surface = DSColors.backgroundReverse,
            onSurface = DSColors.textReverse
    )
}

fun dsDarkColorPalette(): DSColorPalette = object : DSColorPalette {
    override val primary: Color = DSColors.primary
    override val background: Color = DSColors.backgroundReverse
    override val textAccent: Color = DSColors.textAccent
    override val text: Color = DSColors.textReverse
    override val card: Color = DSColors.cardReverse
    override val success: Color = DSColors.success
    override val warning: Color = DSColors.warning
    override val error: Color = DSColors.error
    override val light: Color = DSColors.light
    override val dark: Color = DSColors.dark

    override val materialColors: Colors = darkColors(
            primary = DSColors.primary,
            surface = DSColors.background,
            onSurface = DSColors.textReverse
    )
}
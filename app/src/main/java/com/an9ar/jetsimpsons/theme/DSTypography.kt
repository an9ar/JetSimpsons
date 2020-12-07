package com.an9ar.jetsimpsons.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.font
import androidx.compose.ui.text.font.fontFamily
import androidx.compose.ui.unit.sp
import com.an9ar.jetsimpsons.R

data class DSTypography internal constructor(
        val paragraph1: TextStyle = TextStyle(
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 20.sp
        ),
        val textSmallLight: TextStyle = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Light,
                lineHeight = 20.sp,
                fontFamily = fontFamily(font(R.font.simpsons_font))
        ),
        val textSmall: TextStyle = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 20.sp,
                fontFamily = fontFamily(font(R.font.simpsons_font))
        ),
        val textSmallBold: TextStyle = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 20.sp,
                fontFamily = fontFamily(font(R.font.simpsons_font))
        ),
        val textMediumLight: TextStyle = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Light,
                lineHeight = 20.sp,
                fontFamily = fontFamily(font(R.font.simpsons_font))
        ),
        val textMedium: TextStyle = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 20.sp,
                fontFamily = fontFamily(font(R.font.simpsons_font))
        ),
        val textMediumBold: TextStyle = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 20.sp,
                fontFamily = fontFamily(font(R.font.simpsons_font))
        ),
        val textLargeLight: TextStyle = TextStyle(
                fontSize = 22.sp,
                fontWeight = FontWeight.Light,
                lineHeight = 20.sp,
                fontFamily = fontFamily(font(R.font.simpsons_font))
        ),
        val textLarge: TextStyle = TextStyle(
                fontSize = 22.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 20.sp,
                fontFamily = fontFamily(font(R.font.simpsons_font))
        ),
        val textLargeBold: TextStyle = TextStyle(
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 20.sp,
                fontFamily = fontFamily(font(R.font.simpsons_font))
        ),
        val materialTypography: Typography = Typography(
                body1 = paragraph1
        )
)
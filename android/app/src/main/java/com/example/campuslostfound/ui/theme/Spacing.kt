package com.example.campuslostfound.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Spacing(
    val none: Dp = 0.dp,
    val extraSmall: Dp = 4.dp,
    val small: Dp = 8.dp,
    val mediumSmall: Dp = 12.dp,
    val medium: Dp = 16.dp,
    val large: Dp = 24.dp,
    val extraLarge: Dp = 32.dp,
    val huge: Dp = 48.dp
)

object Dimensions {
    val IconSmall: Dp = 16.dp
    val IconMedium: Dp = 24.dp
    val IconLarge: Dp = 32.dp

    val ButtonHeight: Dp = 48.dp
    val InputFieldHeight: Dp = 56.dp
    val ItemCardHeight: Dp = 120.dp
    val ImageThumbnailSize: Dp = 80.dp

    val CardElevation: Dp = 2.dp
    val DialogElevation: Dp = 6.dp
}

val LocalSpacing = staticCompositionLocalOf { Spacing() }

val MaterialTheme.spacing: Spacing
    @Composable
    @ReadOnlyComposable
    get() = LocalSpacing.current

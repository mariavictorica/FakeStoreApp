package com.pjsoft.fakestoreapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = EvergreenShadow,
    onPrimary = AlmondCream,
    secondary = ForestTint,
    background = EvergreenShadow,
    surface = ForestTint,
    onBackground = AlmondCream,
    onSurface = AlmondCream
)

private val LightColorScheme = lightColorScheme(
    primary = EvergreenShadow,
    onPrimary = AlmondCream,
    secondary = ForestTint,
    background = AlmondCream,
    surface = MistyBeige,
    onBackground = SoftBlack,
    onSurface = SoftBlack
)

@Composable
fun FakeStoreAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {

        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = MontserratTypography,
        content = content
    )
}
package cz.mendelu.xadamek5.project.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val PrimaryColor = Color(0xFFD0BCFF)
val PrimaryDarkColor = Color(0xFFCCC2DC)
val SecondaryColor = Color(0xFFEFB8C8)

// Dark mode primary colors
val PrimaryColorDark = Color(0xFF6650a4)
val PrimaryDarkColorDark = Color(0xFF625b71)
val SecondaryColorDark = Color(0xFF7D5260)


// Basic colors
val LightTextColor = Color(0xFF000000)
val DarkTextColor = Color(0xFFFFFFFF)


@Composable
fun getTintColor() = if (isSystemInDarkTheme()) Color.White else Color.Black

@Composable
fun getBasicTextColor(): Color = if (isSystemInDarkTheme()) DarkTextColor else LightTextColor

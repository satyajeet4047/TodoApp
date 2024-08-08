package com.satyajeetmohalkar.todocompose.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

// Priority Colors
val LowPriorityColor =  Color(0xFF00C980)
val MediumPriorityColor = Color(0xFFFFC114)
val HighPriorityColor = Color(0xFFFF4646)
val NonePriorityColor = Color(0xFF9C9C9C)


val lightGray = Color(0xFFFCFCFC)
val mediumGray = Color(0xFF9C9C9C)
val darkGray = Color(0xFF141414)


val Colors.topAppBarContentColor
@Composable
get() = if(isLight) Color.White else lightGray

val Colors.topAppBarBackgroundColor
    @Composable
    get() = if(isLight) primary else Color.Black
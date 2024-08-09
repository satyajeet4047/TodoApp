package com.satyajeetmohalkar.todocompose.ui.components.todoappbar

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

/**
 *  Centrally used Title composable used for TopAppBars in multiple screens.
 *  Extracting this to a new composable helps to gain more control and customise it centrally.
 */
@Composable
fun TopAppBarTitle(title : String) {
    Text(text = title)
}
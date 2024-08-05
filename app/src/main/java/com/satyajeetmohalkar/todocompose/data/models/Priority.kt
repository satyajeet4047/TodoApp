package com.satyajeetmohalkar.todocompose.data.models

import androidx.compose.ui.graphics.Color
import com.satyajeetmohalkar.todocompose.ui.theme.HighPriorityColor
import com.satyajeetmohalkar.todocompose.ui.theme.LowPriorityColor
import com.satyajeetmohalkar.todocompose.ui.theme.MediumPriorityColor
import com.satyajeetmohalkar.todocompose.ui.theme.NonePriorityColor

enum class Priority(val color : Color) {
    HIGH(HighPriorityColor),
    MEDIUM(MediumPriorityColor),
    LOW(LowPriorityColor),
    NONE(NonePriorityColor)
}

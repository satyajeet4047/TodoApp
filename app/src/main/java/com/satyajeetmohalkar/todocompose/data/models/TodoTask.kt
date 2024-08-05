package com.satyajeetmohalkar.todocompose.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.satyajeetmohalkar.todocompose.utils.Constants.TODO_TABLE_NAME

@Entity(tableName = TODO_TABLE_NAME)
data class TodoTask(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val title : String,
    val description : String,
    val priority : Priority
)

package com.route.todoappc39g_mon_wed.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

// Android -> OOP

@Entity(tableName = "Todos")
data class Task(
    @PrimaryKey(autoGenerate = true) // unique Value
    var id: Int? = null,
    @ColumnInfo(name = "name")
    val title: String? = null,
    val description: String? = null,
    val date: Date? = null,
    var isDone: Boolean? = false,
)

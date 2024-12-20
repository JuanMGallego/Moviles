package com.example.pruebaroom.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_entity")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0,
    var name:String = "",
    var tfno:String = "",
    var isMale:Boolean = false,
    var isDone:Boolean = false

)


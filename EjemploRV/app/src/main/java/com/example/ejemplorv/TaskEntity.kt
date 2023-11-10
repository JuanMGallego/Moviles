package com.example.ejemplorv

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_entity")
data class TaskEntity (
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0, // Id de la tarea
    var nombre:String = "", // Nombre de la tarea
    var tfno:String = "", // Teléfono de la tarea
    var isMale:Boolean = false, // Sexo de la tarea
    var isDone:Boolean = false // Booleano que indica si la tarea está hecha o no
)
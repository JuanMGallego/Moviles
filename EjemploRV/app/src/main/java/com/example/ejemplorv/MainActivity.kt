package com.example.ejemplorv

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Update
import com.example.ejemplorv.databinding.ActivityMainBinding
import com.example.ejemplorv.databinding.ContactosBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val contactos = ContactosBinding.inflate(layoutInflater)
        setContentView(contactos.root)

        @Database(entities = arrayOf(TaskEntity::class), version = 1)
        abstract class TasksDatabase : RoomDatabase() {
            abstract fun taskDao(): TaskDao
        }

        AppDatabase.ContactoDao().insertFriend(Contacto(nombre = "Juan Fernandez Trujillo", tfno = "623582384", isMale = true))

        val allContactos = AppDatabase.contactoDao().getAllContactos()

        @Entity(tableName = "task_entity")
        data class TaskEntity (
            @PrimaryKey(autoGenerate = true)
            var id:Int = 0, // Id de la tarea
            var name:String = "", // Nombre de la tarea
            var isDone:Boolean = false // Booleano que indica si la tarea está hecha o no
        )

        @Dao
        interface TaskDao {
            @Query("SELECT * FROM task_entity")
            suspend fun getAllTasks(): MutableList< TaskEntity>  // Función que devuelve todas las tareas de la base de datos en una lista Mutable.

            @Insert
            suspend fun addTask(taskEntity : TaskEntity):Long    // Función que añade una tarea, la que se pasa por parámetro, y devuelve el id insertado.                                                          // Devuelve Long porque la cantidad de datos guardada puede ser muy alto.

            @Query("SELECT * FROM task_entity where id like :id")
            suspend fun getTaskById(id: Long): TaskEntity        // Función que busca tareas por id (debe ser Long, no Int)

            @Update
            suspend fun updateTask(task: TaskEntity):Int         // Función que actualiza una tarea y devuelve

            @Delete
            suspend fun deleteTask(task: TaskEntity):Int         // Función que borra una tarea y devuelve
        }

        contactos.vistaContactos.adapter = ContactosAdapter(listOf(
            Contacto("Juan Fernandez Trujillo", "623582384", isMale = true),
            Contacto("María Sopla Mocos", "685938912", isMale = false),
            Contacto("Raúl Díaz Palo", "690123490", isMale = true),
            Contacto("Ana María Rodriguez Lopez", "602347124", isMale = false),
            Contacto("Juan Fernandez Trujillo", "623582384", isMale = true),
            Contacto("María Sopla Mocos", "685938912", isMale = false),
            Contacto("Raúl Díaz Palo", "690123490", isMale = true),
            Contacto("Ana María Rodriguez Lopez", "602347124", isMale = false),
            Contacto("Juan Fernandez Trujillo", "623582384", isMale = true),
            Contacto("María Sopla Mocos", "685938912", isMale = false),
            Contacto("Raúl Díaz Palo", "690123490", isMale = true),
            Contacto("Ana María Rodriguez Lopez", "602347124", isMale = false),
            Contacto("Juan Fernandez Trujillo", "623582384", isMale = true),
            Contacto("María Sopla Mocos", "685938912", isMale = false),
            Contacto("Raúl Díaz Palo", "690123490", isMale = true),
            Contacto("Ana María Rodriguez Lopez", "602347124", isMale = false),
            Contacto("Juan Fernandez Trujillo", "623582384", isMale = true),
            Contacto("María Sopla Mocos", "685938912", isMale = false),
            Contacto("Raúl Díaz Palo", "690123490", isMale = true),
            Contacto("Ana María Rodriguez Lopez", "602347124", isMale = false),
            Contacto("Juan  Fernandez Trujillo", "623582384", isMale = true),
            Contacto("María Sopla Mocos", "685938912", isMale = false),
            Contacto("Raúl Díaz Palo", "690123490", isMale = true),
            Contacto("Ana María Rodriguez Lopez", "602347124", isMale = false),
        ), object : ContactoPulsadoListener{

            override fun contactoPulsado(contacto: Contacto) {
                val dial = Intent(
                    Intent.ACTION_DIAL,
                    Uri.parse("tel:" + contacto.tfno)
                )
                startActivity(dial)
            }

        })

    }
}
package com.example.contactscomposeroom

import android.os.Bundle
import android.view.Surface
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
import com.example.contactscomposeroom.ui.theme.ContactsComposeRoomTheme

class MainActivity : ComponentActivity() {

    val contacts : List<String> = listOf(

    )

    val db = Room.databaseBuilder(
        applicationContext,
        AppDatabase::class.java, "database-name"
    ).build()
    val userDao = db.userDao()
    val users: List<User> = userDao.getAll()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContactBook() {

            }
        }
    }
}

@Entity
data class Contact(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "first_name") val firstName: String?,
    @ColumnInfo(name = "last_name") val lastName: String?,
    @ColumnInfo(name = "phone_num") val phoneNum: String?,
    @ColumnInfo(name = "sex") val sex: Boolean?
)

@Dao
interface ContactDao {
    @Query("SELECT * FROM Contact")
    fun getAll(): List<Contact>

    @Query("SELECT * FROM contact WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Contact>

    @Query("SELECT * FROM contact WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): Contact

    @Insert
    fun insertAll(vararg contacts: Contact)

    @Delete
    fun delete(contacts: Contact)
}

@Database(entities = [Contact::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): ContactDao
}

@Composable
fun ContactBook (
    contacts: List<Contact>,
    onDeleteContact: (Contact) -> Unit
) {

    LazyColumn {
        items(contacts) { contact ->
            ContactListItem(contact = contact, onDeleteContact = onDeleteContact)
        }
    }

}

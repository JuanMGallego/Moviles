package com.example.friendlink

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class Manejadora {

    private val db = FirebaseFirestore.getInstance()

    suspend fun registrar(user: User) {
        if (user.userName.isNotEmpty() && user.password.isNotEmpty()) {
            val userData = hashMapOf(
                "userName" to user.userName,
                "password" to user.password
            )

            db.collection("users").add(userData).await()
        }
    }

    suspend fun send (message: Message){
        if (message.msg.isNotEmpty()) {
            val newMsg = hashMapOf(
                "menssage" to message.msg,
                "from" to message.from
            )

            db.collection("messages").add(newMsg).await()
        }
    }



}
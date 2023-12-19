package com.example.friendlink

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun ChatScreen(navHostController: NavHostController) {

    Column {

        Box(
            modifier = Modifier
                .fillMaxHeight(0.9f)
        ){
            LazyVerticalGrid(
                columns = GridCells.Fixed(1)
            ){

            }
        }

    }

}
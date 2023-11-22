package com.example.juegoninos

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
private fun ItemUI(instrumento: Instrumento) {

    Image(
        painter = painterResource(id = instrumento.image),
        contentDescription = null,
        modifier = Modifier
            .background(color = Color(0xFF99B6C2))
            .clickable(
                onClick = {

                }
            )
    )

}

@Composable
fun LazyColumnJuego(instrumentos: List<Instrumento>){

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),


    ) {

        items(instrumentos) { instrumentos ->

            ItemUI(instrumento = instrumentos)

        }

    }

}
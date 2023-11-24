package com.example.examenjuanmanuelgallegotr

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.InspectableModifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun PantallaVehiculos (navHostController: NavHostController, vehiculos: List<Vehiculo>) {

    Column {

        Box(
            modifier = Modifier
                .fillMaxHeight(0.9f)
                .background(color = Color.Cyan)
        ) {

            LazyVerticalGrid(
                columns = GridCells.Fixed(1),
                Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),


                ) {

                items(vehiculos) { vehiculos ->

                    VehiculoUI(vehiculo = vehiculos)

                }

            }

            Column {
                
            }
        }
        Box(
            modifier = Modifier
                .fillMaxHeight()
        ) {
            Row {
                Button(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(0.5f),
                    shape = RectangleShape,
                    onClick = { navHostController.navigate("config") }
                ) {
                    Text(text = "Config")
                }
                Button(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(),
                    shape = RectangleShape,
                    onClick = { }
                ) {
                    Text(text = "Vehiculos")
                }

            }
        }

    }

}

@Composable
private fun VehiculoUI(vehiculo: Vehiculo) {

    Button(
        modifier = Modifier
            .fillMaxWidth(),
        colors = androidx.compose.material3.ButtonDefaults.buttonColors(Color.Gray),
        shape = RectangleShape,
        onClick = { }
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = vehiculo.matricula,
                fontSize = 20.sp,
                modifier = Modifier

            )
        }

    }

}
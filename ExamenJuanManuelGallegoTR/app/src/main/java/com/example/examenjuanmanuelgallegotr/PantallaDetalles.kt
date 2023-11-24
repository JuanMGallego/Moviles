package com.example.examenjuanmanuelgallegotr

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun PantallaDetalles (navHostController: NavHostController) {

    Column {

        Box(
            modifier = Modifier

        ) {
            Column {

                Button(onClick = { navHostController.navigate("detalles") }) {
                    Text("Guardar")
                }

            }
        }
        Box(modifier = Modifier) {
            Row {
                Button(onClick = { navHostController.navigate("config") }) {

                }
                Button(onClick = { navHostController.navigate("vehiculos") }) {

                }

            }
        }

    }

}
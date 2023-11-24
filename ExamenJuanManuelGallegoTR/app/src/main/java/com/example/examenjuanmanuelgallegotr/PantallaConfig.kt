package com.example.examenjuanmanuelgallegotr

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.navigation.NavHostController
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment


@Composable
fun PantallaConfig (navHostController: NavHostController) {

    Column {

        Box(
            modifier = Modifier
                .fillMaxHeight(0.9f)

        ) {
            Column (
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .background(color = Color.Cyan)
            ){

                Text(text = "Limite: 60 km/h")
                Text(text = "Multa: 200 euros")
                Text(text = "Numero Vehiculos: 415")

            }
        }
        Box(modifier = Modifier) {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Button(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(0.5f),
                    shape = RectangleShape,
                    onClick = { }
                ) {
                    Text(text = "Config")
                }
                Button(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(),
                    shape = RectangleShape,
                    onClick = { navHostController.navigate("vehiculos") }
                ) {
                    Text(text = "Vehiculos")
                }

            }
        }

    }

}
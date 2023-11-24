package com.example.examenjuanmanuelgallegotr

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionContext
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.examenjuanmanuelgallegotr.ui.theme.ExamenJuanManuelGallegoTRTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            AppNavigation()

        }
    }
}

@Composable
fun AppNavigation() {

    val repository = Repositorio()
    val vehiculo = repository.getAllVehiculos()

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "config"
    ) {
        composable("config") {
            PantallaConfig(navController)
        }
        composable("vehiculos") {

            PantallaVehiculos(navController, vehiculo)
        }
        composable("detalles") {

            PantallaDetalles(navController)
        }
    }
}
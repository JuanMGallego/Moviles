package com.example.juegoninos

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
private fun ItemUI(context: Context, instrumento: Instrumento) {

    var mp : MediaPlayer = MediaPlayer.create(context, instrumento.sound)

    Image(
        painter = painterResource(id = instrumento.image),
        contentDescription = null,
        modifier = Modifier
            .background(color = Color(0xFF99B6C2))
            .clickable(
                onClick = {
                    mp.start()
                }
            )
    )

}

@Composable
fun LazyColumnJuego(instrumentos: List<Instrumento>){

    var isPlaying by remember { mutableStateOf(false) }
    var mediaPlayer: MediaPlayer? by remember { mutableStateOf(null) }

    val context = LocalContext.current

    val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    val accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

    DisposableEffect(context) {
        val mp = MediaPlayer.create(context, R.raw.cristal_roto)
        val sensorListener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                event?.let {
                    val x = event.values[0]
                    val y = event.values[1]
                    val z = event.values[2]

                    val acceleration = x * x + y * y + z * z

                    if (acceleration > 1) {
                        if (!isPlaying) {
                            isPlaying = true
                            mp.start()
                        }
                    }
                }
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
            }
        }

        sensorManager.registerListener(sensorListener, accelerometerSensor, SensorManager.SENSOR_DELAY_FASTEST)

        onDispose {
            sensorManager.unregisterListener(sensorListener)
            mediaPlayer?.release()
        }
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),


    ) {

        items(instrumentos) { instrumentos ->

            ItemUI(context, instrumento = instrumentos)

        }

    }

}
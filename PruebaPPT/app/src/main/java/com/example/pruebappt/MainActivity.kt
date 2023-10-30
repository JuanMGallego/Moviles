package com.example.pruebappt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RockPaperScissorsGame()
        }
    }
}

enum class Choice { ROCK, PAPER, SCISSORS, NONE }

@Composable
fun RockPaperScissorsGame() {
    var userChoice by remember { mutableStateOf(Choice.NONE) }
    var machineChoice by remember { mutableStateOf(Choice.NONE) }
    var userScore by remember { mutableStateOf(0) }
    var machineScore by remember { mutableStateOf(0) }
    var gameResult by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Piedra, Papel o Tijeras",
            style = MaterialTheme.typography.h4,
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            textAlign = TextAlign.Center
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Tú: $userScore", style = MaterialTheme.typography.h6)
            Text(text = "Máquina: $machineScore", style = MaterialTheme.typography.h6)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = gameResult,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ChoiceButton(Choice.ROCK, userChoice, "Piedra") { userChoice = it }
            ChoiceButton(Choice.PAPER, userChoice, "Papel") { userChoice = it }
            ChoiceButton(Choice.SCISSORS, userChoice, "Tijeras") { userChoice = it }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                val machineChoice = generateRandomChoice()
                updateScores(userChoice, machineChoice)
                this.machineChoice = machineChoice
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("¡Jugar!")
        }
    }
}

@Composable
fun ChoiceButton(
    choice: Choice,
    selectedChoice: Choice,
    label: String,
    onChoose: (Choice) -> Unit
) {
    val isSelected = choice == selectedChoice
    val backgroundColor = if (isSelected) Color.Gray else Color.Transparent
    val contentColor = if (isSelected) Color.White else Color.Black

    Card(
        modifier = Modifier.size(120.dp),
        elevation = 8.dp,
        backgroundColor = backgroundColor
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clickable { onChoose(choice) },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterForResource(choice),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Text(
                text = label,
                color = contentColor,
                style = MaterialTheme.typography.body2
            )
        }
    }
}

fun painterForResource(choice: Choice) = when (choice) {
    Choice.ROCK -> painter(R.drawable.ic_rock)
    Choice.PAPER -> painter(R.drawable.ic_paper)
    Choice.SCISSORS -> painter(R.drawable.ic_scissors)
    else -> painter(R.drawable.ic_launcher_foreground)
}

fun generateRandomChoice(): Choice {
    val choices = Choice.values()
    val randomIndex = Random().nextInt(choices.size)
    return choices[randomIndex]
}

fun updateScores(userChoice: Choice, machineChoice: Choice) {
    when {
        userChoice == machineChoice -> gameResult = "Empate"
        userChoice == Choice.ROCK && machineChoice == Choice.SCISSORS ||
                userChoice == Choice.PAPER && machineChoice == Choice.ROCK ||
                userChoice == Choice.SCISSORS && machineChoice == Choice.PAPER -> {
            gameResult = "¡Ganaste!"
            userScore++
        }
        else -> {
            gameResult = "¡La máquina ganó!"
            machineScore++
        }
    }
}
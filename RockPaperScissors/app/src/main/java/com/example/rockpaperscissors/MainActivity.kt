package com.example.rockpaperscissors

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random
import androidx.navigation.NavHostController
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
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "loginPlayer"
    ) {
        composable("loginPlayer") {
            LoginPlayer(navController)
        }
        composable("game") {

            RockPaperScissorsScreen(LocalContext.current, navController)
        }
        composable("scoreboard") {

            Scoreboard()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPlayer(navController: NavHostController) {

    var user by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF67A4C5))
    ) {
        Spacer(modifier = Modifier.height(100.dp))
        Image(
            painter = painterResource(id = R.drawable.pptlogo),
            contentDescription = "logo app",
            modifier = Modifier
                .size(250.dp)
                .padding(40.dp)
        )
        TextField(
            value = user,
            onValueChange = {user = it},

            keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Email
            ),
            placeholder = { Text(text = "Usuario") },
            modifier = Modifier
                .width(300.dp)
                .fillMaxWidth()
                .padding(8.dp)
        )
        TextField(
            value = password,
            onValueChange = {password = it},
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password
            ),
            placeholder = { Text(text = "Contraseña") },
            modifier = Modifier
                .width(300.dp)
                .fillMaxWidth()
                .padding(8.dp)
        )
        Box(modifier = Modifier.padding(20.dp)) {
            Button(

                onClick = { navController.navigate("game") },
                modifier = Modifier
                    .width(100.dp)
                    .height(50.dp),
                shape = (RoundedCornerShape(0.dp)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF105ACA),
                    contentColor = Color.White)
            ) {
                Text(text = "Enviar")
            }
        }
    }
}

@Composable
fun RockPaperScissorsScreen(context: Context, navController: NavHostController) {

    var playerChoice by remember { mutableStateOf("") }
    var centralText by remember { mutableStateOf("VS") }
    var textSize by remember { mutableStateOf(40)}
    var computerChoice by remember { mutableStateOf(-1) }
    var playerScore by remember { mutableStateOf(0) }
    var computerScore by remember { mutableStateOf(0) }

    var playerImg by remember { mutableStateOf(R.drawable.interrogation) }

    if (playerChoice.equals("rock")) {
        playerImg = R.drawable.rock
    } else if (playerChoice.equals("paper")) {
        playerImg = R.drawable.paper
    } else if (playerChoice.equals("sciss")) {
        playerImg = R.drawable.scissors
    }


    var computerImg by remember { mutableStateOf(R.drawable.interrogation)}

    if (computerChoice == 0) {
        computerImg = R.drawable.rock
    } else if (computerChoice == 1) {
        computerImg = R.drawable.paper
    } else if (computerChoice == 2) {
        computerImg = R.drawable.scissors
    }


    var winner by remember { mutableStateOf(0) }
    var round by remember { mutableStateOf(0) }

        if ((round == 5) && (playerScore > computerScore)) {
            textSize = 30
            centralText = "GANASTE!"
            navController.navigate("Scoreboard")

        } else if ((round == 5) && (playerScore < computerScore)) {
            textSize = 25
            centralText = "PERDISTE :("
            navController.navigate("Scoreboard")
        } else if ((round == 5) && (playerScore == computerScore)){
            textSize = 30
            centralText = "EMPATE"
            navController.navigate("Scoreboard")
        }

    Column (modifier = Modifier.fillMaxWidth()){

        Box(
            modifier = Modifier
                .weight(2f)
                .fillMaxHeight()
                .background(color = Color(0xFFCD3E3E))
        ) {

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.TopCenter),
                horizontalArrangement = Arrangement.SpaceEvenly
            ){

                Image(
                    painter = painterResource(id = R.drawable.rock),
                    contentDescription = "Rock Computer",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(color = Color.LightGray)
                        .border(
                            BorderStroke(4.dp, Color.Gray),
                            RoundedCornerShape(16.dp)
                        )
                )

                Image(
                    painter = painterResource(id = R.drawable.paper),
                    contentDescription = "Paper Computer",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(color = Color.LightGray)
                        .border(
                            BorderStroke(4.dp, Color.Gray),
                            RoundedCornerShape(16.dp)
                        )
                )

                Image(
                    painter = painterResource(id = R.drawable.scissors),
                    contentDescription = "Scissors Computer",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(color = Color.LightGray)
                        .border(
                            BorderStroke(4.dp, Color.Gray),
                            RoundedCornerShape(16.dp)
                        )
                )

            }

        }

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .align(Alignment.Center),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .background(color = Color(0xFF3EA4CD)),
                    contentAlignment = Alignment.Center
                ) {

                    Image(
                        painter = painterResource(playerImg),
                        contentDescription = "Interrogation Player",
                        modifier = Modifier
                            .size(100.dp)
                            .padding(10.dp)
                    )

                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .background(color = Color.Gray)
                        .border(
                            BorderStroke(6.dp, Color.DarkGray)
                        ),
                    contentAlignment = Alignment.Center
                ) {

                    Column {

                        Box (
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {

                            Text(
                                text = computerScore.toString(),
                                style = TextStyle(
                                    fontSize = 30.sp,
                                    fontWeight = FontWeight.Bold
                                )

                            )

                        }

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {

                            Text(
                                text = centralText,
                                style = TextStyle(
                                    fontSize = textSize.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            )

                        }

                        Box (
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {

                            Text(
                                text = playerScore.toString(),
                                style = TextStyle(
                                    fontSize = 30.sp,
                                    fontWeight = FontWeight.Bold
                                )

                            )

                        }

                    }

                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .background(color = Color(0xFFCD3E3E)),
                    contentAlignment = Alignment.Center
                ) {

                    Image(
                        painter = painterResource(computerImg),
                        contentDescription = "Interrogation Computer",
                        modifier = Modifier
                            .size(100.dp)
                            .padding(10.dp)
                    )

                }

            }

        }

        Box(
            modifier = Modifier
                .weight(2f)
                .fillMaxHeight()
                .background(color = Color(0xFF3EA4CD))
        ) {

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.BottomCenter),
                horizontalArrangement = Arrangement.SpaceEvenly
            ){

                Image(
                    painter = painterResource(id = R.drawable.rock),
                    contentDescription = "Rock Player",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(color = Color.LightGray)
                        .border(
                            BorderStroke(4.dp, Color.Gray),
                            RoundedCornerShape(16.dp)
                        )
                        .clickable(
                            onClick = {

                                if (round < 5) {

                                    playerChoice = "rock"
                                    computerChoice = Random.nextInt(0, 2)
                                    winner = duelResult(playerChoice, computerChoice)
                                    if (winner == 1) {
                                        playerScore++
                                        showToast(context, "Ganaste la ronda!")
                                    } else if (winner == 2) {
                                        computerScore++
                                        showToast(context, "Perdiste esta ronda :(")
                                    } else {
                                        showToast(context, "Empate!")
                                    }
                                    round++

                                }

                            }
                        )
                )

                Image(
                    painter = painterResource(id = R.drawable.paper),
                    contentDescription = "Paper Player",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(color = Color.LightGray)
                        .border(
                            BorderStroke(4.dp, Color.Gray),
                            RoundedCornerShape(16.dp)
                        )
                        .clickable(
                            onClick = {

                                if (round < 5) {

                                    playerChoice = "paper"
                                    computerChoice = Random.nextInt(0, 2)
                                    winner = duelResult(playerChoice, computerChoice)
                                    if (winner == 1) {
                                        playerScore++
                                        showToast(context, "Ganaste la ronda!")
                                    } else if (winner == 2) {
                                        computerScore++
                                        showToast(context, "Perdiste esta ronda :(")
                                    } else {
                                        showToast(context, "Empate!")
                                    }
                                    round++

                                }

                            }
                        )
                )

                Image(
                    painter = painterResource(id = R.drawable.scissors),
                    contentDescription = "Scissors Player",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(color = Color.LightGray)
                        .border(
                            BorderStroke(4.dp, Color.Gray),
                            RoundedCornerShape(16.dp)
                        )
                        .clickable(
                            onClick = {

                                if (round < 5) {

                                    playerChoice = "sciss"
                                    computerChoice = Random.nextInt(0, 2)
                                    winner = duelResult(playerChoice, computerChoice)
                                    if (winner == 1) {
                                        playerScore++
                                        showToast(context, "Ganaste la ronda!")
                                    } else if (winner == 2) {
                                        computerScore++
                                        showToast(context, "Perdiste esta ronda :(")
                                    } else {
                                        showToast(context, "Empate!")
                                    }
                                    round++

                                }

                            }
                        )
                )

            }

        }

    }

}

@Composable
fun Scoreboard() {

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF899399))
            .padding(50.dp)
    ) {

    }

}

fun duelResult(playerChoice: String, computerChoice: Int): Int {

    var playerWins = 0

    if (
        (playerChoice.equals("rock") && computerChoice == 2) ||
        (playerChoice.equals("paper") && computerChoice == 0) ||
        (playerChoice.equals("sciss") && computerChoice == 1)
    ) {
        playerWins = 1
    } else if (
        (playerChoice.equals("rock") && computerChoice == 1) ||
        (playerChoice.equals("paper") && computerChoice == 2) ||
        (playerChoice.equals("sciss") && computerChoice == 0)
    ){
        playerWins = 2
    }

    return playerWins

}

private fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}
package com.example.fbrockpaperscissors

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
import androidx.lifecycle.ViewModel
import kotlin.random.Random
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
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

            LoginPlayer(navController, )
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
fun LoginPlayer(
    navController: NavHostController,
    state: SignInState,
    onSignInClick: () -> Unit
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = state.signInError) {
        state.signInError?.let { error ->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = onSignInClick) {
            Text(text = "Sign in")
        }
    }
}

@Composable
fun RockPaperScissorsScreen(context: Context, navController: NavHostController) {

    val database = FirebaseDatabase.getInstance()
    val scoresRef = database.getReference("scores")

    var playerChoice by remember { mutableStateOf("") }
    var centralText by remember { mutableStateOf("VS") }
    var textSize by remember { mutableStateOf(40) }
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


    var computerImg by remember { mutableStateOf(R.drawable.interrogation) }

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
                                        scoresRef.child("playerScore").setValue(playerScore + 1)
                                    } else {
                                        showToast(context, "Empate!")
                                        scoresRef.child("computerScore").setValue(computerScore + 1)
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
                                        scoresRef.child("playerScore").setValue(playerScore + 1)
                                    } else if (winner == 2) {
                                        computerScore++
                                        showToast(context, "Perdiste esta ronda :(")
                                    } else {
                                        showToast(context, "Empate!")
                                        scoresRef.child("computerScore").setValue(computerScore + 1)
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
                                        scoresRef.child("playerScore").setValue(playerScore + 1)
                                    } else if (winner == 2) {
                                        computerScore++
                                        showToast(context, "Perdiste esta ronda :(")
                                        scoresRef.child("computerScore").setValue(computerScore + 1)
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
    var playerWins by remember { mutableStateOf(0) }
    var computerWins by remember { mutableStateOf(0) }

    // Referencia a la base de datos
    val database = FirebaseDatabase.getInstance()
    val scoresRef = database.getReference("scores")

    // Escucha cambios en la puntuación del jugador
    scoresRef.child("playerScore").addValueEventListener(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            playerWins = snapshot.value as? Int ?: 0
        }

        override fun onCancelled(error: DatabaseError) {
            // Manejar error
        }
    })

    // Escucha cambios en la puntuación de la máquina
    scoresRef.child("computerScore").addValueEventListener(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            computerWins = snapshot.value as? Int ?: 0
        }

        override fun onCancelled(error: DatabaseError) {
            // Manejar error
        }
    })

    // Interfaz de usuario
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF4C92BD))
            .padding(50.dp)
    ) {
        // Muestra las partidas ganadas por el jugador
        Text(text = "Partidas ganadas por el jugador: $playerWins")

        // Muestra las partidas ganadas por la máquina
        Text(text = "Partidas ganadas por la máquina: $computerWins")
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
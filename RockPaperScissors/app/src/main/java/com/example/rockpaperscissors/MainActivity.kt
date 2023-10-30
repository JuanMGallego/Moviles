package com.example.rockpaperscissors

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            RockPaperScissorsScreen()

        }
    }
}

@Composable
fun RockPaperScissorsScreen(modifier: Modifier=Modifier) {

    var playerChoice = ""
    var computerChoice = ""
    var playerScore = 0
    var computerScore = 0
    var round = 0

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

            Text(
                text = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(40.dp)
            )

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
                        painter = painterResource(id = R.drawable.interrogation),
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
                                text = "0",
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
                                text = "VS",
                                style = TextStyle(
                                    fontSize = 40.sp,
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
                                text = "0",
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
                        painter = painterResource(id = R.drawable.interrogation),
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
                        .clickable {
                            playerChoice = "rock"
                        }
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
                        .clickable {
                            playerChoice = "paper"
                        }
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
                        .clickable {
                            playerChoice = "sciss"
                        }
                )

            }

        }
    }

}


package com.example.rockpaperscissors

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var playerChoice = ""
            var computerChoice = ""
            var playerScore = 0
            var computerScore = 0
            var round = 0

            RockPaperScissorsScreen()

        }
    }
}

@Composable
fun RockPaperScissorsScreen(modifier: Modifier=Modifier) {

    Column (modifier = Modifier.fillMaxWidth()){

        Box(
            modifier = Modifier
                .weight(1f) // Elemento 2 con peso 2
                .fillMaxHeight()
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
                    contentDescription = "Rock Player",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(color = Color.Cyan)
                        .border(
                            BorderStroke(4.dp, Color(0xFF15937a)),
                            RoundedCornerShape(16.dp)
                        )
                )

                Image(
                    painter = painterResource(id = R.drawable.paper),
                    contentDescription = "Rock Player",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(color = Color.Cyan)
                        .border(
                            BorderStroke(4.dp, Color(0xFF15937a)),
                            RoundedCornerShape(16.dp)
                        )
                )
                Image(
                    painter = painterResource(id = R.drawable.scissors),
                    contentDescription = "Rock Player",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(color = Color.Cyan)
                        .border(
                            BorderStroke(4.dp, Color(0xFF15937a)),
                            RoundedCornerShape(16.dp)
                        )
                )

            }

        }

        Box(
            modifier = Modifier
                .weight(2f) // Elemento 2 con peso 2
                .fillMaxHeight()
        ) {

        }

        Box(
            modifier = Modifier
                .weight(1f) // Elemento 2 con peso 2
                .fillMaxHeight()
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
                        .background(color = Color.Cyan)
                        .border(
                            BorderStroke(4.dp, Color(0xFF15937a)),
                            RoundedCornerShape(16.dp)
                        )
                )

                Image(
                    painter = painterResource(id = R.drawable.paper),
                    contentDescription = "Rock Player",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(color = Color.Cyan)
                        .border(
                            BorderStroke(4.dp, Color(0xFF15937a)),
                            RoundedCornerShape(16.dp)
                        )
                )
                Image(
                    painter = painterResource(id = R.drawable.scissors),
                    contentDescription = "Rock Player",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(color = Color.Cyan)
                        .border(
                            BorderStroke(4.dp, Color(0xFF15937a)),
                            RoundedCornerShape(16.dp)
                        )
                )

            }

        }
    }

}


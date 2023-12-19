package com.example.friendlink

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.friendlink.ui.theme.Roboto
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(navHostController: NavHostController) {

    Surface (modifier = Modifier.background(color = Color(0xFF18202C))){
        Column (modifier = Modifier.fillMaxSize()){

            SeccionSuperior()
            Spacer(modifier = Modifier.height(36.dp))

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 30.dp)
            ){

                SeccionRegister(Manejadora())

                Spacer(modifier = Modifier.height(25.dp))

                SeccionIniciarSesion(navHostController)

            }
        }
    }
}

@Composable
private fun SeccionSuperior() {
    Box(
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.5f),
            painter = painterResource(id = R.drawable.shape_register),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )

        Row(
            modifier = Modifier.padding(top = 80.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(42.dp),
                painter = painterResource(id = R.drawable.logo_white),
                contentDescription = stringResource(id = R.string.app_logo)
            )
            Spacer(modifier = Modifier.width(15.dp))
            Column {
                Text(
                    text = stringResource(id = R.string.friendLink),
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White
                )
                Text(
                    text = stringResource(id = R.string.beAlwaysConnected),
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )
            }
        }

        Text(
            modifier = Modifier
                .padding(bottom = 10.dp)
                .align(alignment = Alignment.BottomCenter),
            text = stringResource(id = R.string.register),
            style = MaterialTheme.typography.headlineLarge,
            color = Color.Black
        )

    }
}

@Composable
private fun SeccionRegister(db: Manejadora) {

    val scope = rememberCoroutineScope()

    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confPassword by remember { mutableStateOf("") }

    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = userName,
        onValueChange = {userName = it},
        label = {
            Text(text = "Usuario", style = MaterialTheme.typography.labelMedium, color = Color.Black)
        },
        colors = TextFieldDefaults.colors(
            unfocusedPlaceholderColor = Color(0xFF94A3B8),
            focusedPlaceholderColor = Color.Black,
            unfocusedContainerColor = Color(0xFFF1F5F9),
            focusedContainerColor = Color(0xFFF1F5F9)
        )
    )
    Spacer(modifier = Modifier.height(15.dp))
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = password,
        onValueChange = {password = it},
        label = {
            Text(text = "Contraseña", style = MaterialTheme.typography.labelMedium, color = Color.Black)
        },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
        colors = TextFieldDefaults.colors(
            unfocusedPlaceholderColor = Color(0xFF94A3B8),
            focusedPlaceholderColor = Color.Black,
            unfocusedContainerColor = Color(0xFFF1F5F9),
            focusedContainerColor = Color(0xFFF1F5F9)
        )
    )
    Spacer(modifier = Modifier.height(15.dp))
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = confPassword,
        onValueChange = {confPassword = it},
        label = {
            Text(text = "Contraseña", style = MaterialTheme.typography.labelMedium, color = Color.Black)
        },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
        colors = TextFieldDefaults.colors(
            unfocusedPlaceholderColor = Color(0xFF94A3B8),
            focusedPlaceholderColor = Color.Black,
            unfocusedContainerColor = Color(0xFFF1F5F9),
            focusedContainerColor = Color(0xFFF1F5F9)
        )
    )

    Spacer(modifier = Modifier.height(20.dp))

    var usuario = User(userName, password)

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        onClick = { if (password == confPassword) {
            scope.launch { db.registrar(usuario) }
        } },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(size = 4.dp)
    ) {
        Text(
            text = "Crear cuenta",
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Medium)
        )
    }
}

@Composable
private fun SeccionIniciarSesion(navHostController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxHeight(fraction = 0.8f)
            .fillMaxWidth(),
        contentAlignment = Alignment.TopCenter
    ) {

        Row {
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color(0xFF94A3B8),
                            fontSize = 14.sp,
                            fontFamily = Roboto,
                            fontWeight = FontWeight.Normal
                        )
                    ) {
                        append("¿Ya tienes una cuenta?")
                    }
                })
            ClickableText(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color.Black,
                            fontSize = 14.sp,
                            fontFamily = Roboto,
                            fontWeight = FontWeight.Medium
                        )
                    ) {
                        append(" ")
                        append("Iniciar Sesión")
                    }
                },
                onClick = { navHostController.navigate("login") }
            )
        }


    }
}
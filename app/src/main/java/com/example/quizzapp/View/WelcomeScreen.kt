package com.example.quizzapp.View

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.jvm.java
import androidx.compose.ui.platform.LocalContext
import com.example.quizzapp.Controller.QuestionsActivity


@Composable
fun WelcomeScreen(modifier: Modifier = Modifier) {

    var name by remember { mutableStateOf("")}
    var showError by remember { mutableStateOf(false) }
    val context = LocalContext.current
    Box(
        modifier = modifier
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF9C27B0),
                        Color(0xFF7B1FA2),
                        Color(0xFF4A148C)
                    ),
                    start = Offset(0f, 0f),
                    end = Offset(0f, Float.POSITIVE_INFINITY)
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Welcome to the Quiz!",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .background(
                        color = Color.White,
                        shape = MaterialTheme.shapes.medium
                    )
                    .padding(24.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Test your knowledge with fun questions!",
                        fontSize = 16.sp,
                        color = Color(0xFF666666), // Dark text on white
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    OutlinedTextField(
                        value = name,
                        onValueChange = {name = it
                                        showError = false},
                        placeholder = {Text("Enter your name")},
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        isError = showError,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFF7B1FA2),
                            focusedLabelColor = Color(0xFF7B1FA2),
                            cursorColor = Color(0xFF7B1FA2)
                        )
                    )

                    // Error message
                    if (showError) {
                        Text(
                            text = "Please enter your name to continue",
                            color = Color(0xFFE53935),
                            fontSize = 12.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 4.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = {
                            if (!name.isBlank())
                            {
                                val intent = Intent(context, QuestionsActivity::class.java)
                                intent.putExtra("USER_NAME",name)
                                context.startActivity(intent)

                            }else{
                                showError=true
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF7B1FA2) // Purple button
                        )
                    ) {
                        Text(
                            text = "Start Quiz",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White // White text on purple button
                        )
                    }
                }
            }
        }
    }
}

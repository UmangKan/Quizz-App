package com.example.quizzapp.Controller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.quizzapp.View.WelcomeScreen
import com.example.quizzapp.ui.theme.QuizzAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            QuizzAppTheme {
                QuizApp()
            }
        }
    }
}

@Composable
fun QuizApp() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        WelcomeScreen(
            modifier = Modifier
                .padding(innerPadding)
                .imePadding()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    QuizzAppTheme {
        WelcomeScreen()
    }
}
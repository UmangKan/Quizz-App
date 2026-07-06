package com.example.quizzapp.Controller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.quizzapp.View.ResultScreen
import com.example.quizzapp.ui.theme.QuizzAppTheme


class ResultActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val userName = intent.getStringExtra("USER_NAME") ?: "Player"
        val score = intent.getIntExtra("SCORE",0)
        val totalQuestions = intent.getIntExtra("TOTAL_QUESTIONS", 0)

        setContent {
            QuizzAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ResultScreen(
                        userName = userName,
                        score = score,
                        totalQuestions = totalQuestions,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
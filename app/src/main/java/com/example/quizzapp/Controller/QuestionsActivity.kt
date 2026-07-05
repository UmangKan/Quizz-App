package com.example.quizzapp.Controller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.quizzapp.ui.theme.QuizzAppTheme
import com.example.quizzapp.View.QuestionScreen

class QuestionsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userName = intent.getStringExtra("USER_NAME") ?: "Player"

        setContent {
            QuizzAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    QuestionScreen(
                        userName = userName,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
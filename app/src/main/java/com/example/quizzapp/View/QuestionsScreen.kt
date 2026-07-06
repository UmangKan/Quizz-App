package com.example.quizzapp.View

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizzapp.Controller.ResultActivity
import com.example.quizzapp.Utils.Constants

@Composable
fun QuestionScreen(
    userName: String,
    modifier: Modifier = Modifier
) {
    val questionList = Constants.getQuestions()
    var currentQuestionIndex by remember { mutableIntStateOf(0) }
    var selectedOption by remember { mutableIntStateOf(-1) }
    var score by remember { mutableIntStateOf(0) }
    var showResult by remember { mutableStateOf(false) }
    var isAnswered by remember { mutableStateOf(false) }

    val context = LocalContext.current

    val currentQuestion = questionList[currentQuestionIndex]

    Box(
        modifier = modifier
            .fillMaxSize()
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
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header with user info
            Text(
                text = "Welcome, $userName!",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(8.dp))

            LinearProgressIndicator(
                progress = { (currentQuestionIndex + 1).toFloat() / questionList.size },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(RoundedCornerShape(4.dp)),
                color = Color.White,
                trackColor = Color.White.copy(alpha = 0.3f)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Score: $score / ${questionList.size}",
                fontSize = 16.sp,
                color = Color.White.copy(alpha = 0.8f)
            )

            Spacer(modifier = Modifier.height(8.dp))


            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Question ${currentQuestionIndex + 1} of ${questionList.size}",
                fontSize = 14.sp,
                color = Color.White.copy(alpha = 0.8f)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // White card for question
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Flag image
                    Image(
                        painter = painterResource(id = currentQuestion.image),
                        contentDescription = "Flag",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .clip(RoundedCornerShape(12.dp)),
                        contentScale = ContentScale.Fit
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Question text
                    Text(
                        text = currentQuestion.question,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF1A237E),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Options
                    val options = listOf(
                        currentQuestion.optionOne,
                        currentQuestion.optionTwo,
                        currentQuestion.optionThree,
                        currentQuestion.optionFour
                    )

                    options.forEachIndexed { index, option ->
                        val backgroundColor = when {
                            !isAnswered -> Color(0xFFF5F5F5)
                            index == currentQuestion.correctAnswer - 1 -> Color(0xFF4CAF50) // Green for correct
                            index == selectedOption -> Color(0xFFF44336) // Red for wrong
                            else -> Color(0xFFF5F5F5)
                        }

                        val borderColor = when {
                            isAnswered && index == currentQuestion.correctAnswer - 1 -> Color(0xFF4CAF50)
                            isAnswered && index == selectedOption && index != currentQuestion.correctAnswer - 1 -> Color(0xFFF44336)
                            index == selectedOption -> Color(0xFF7B1FA2)
                            else -> Color.Transparent
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(backgroundColor)
                                .border(2.dp, borderColor, RoundedCornerShape(12.dp))
                                .clickable(enabled = !isAnswered) {
                                    selectedOption = index
                                    isAnswered = true
                                    if (index == currentQuestion.correctAnswer - 1) {
                                        score++
                                    }
                                }
                                .padding(16.dp)
                        ) {
                            Text(
                                text = option,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                color = when {
                                    isAnswered && (index == currentQuestion.correctAnswer - 1 || index == selectedOption) -> Color.White
                                    else -> Color(0xFF424242)
                                },
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Next/Finish button
            if (isAnswered) {
                Button(
                    onClick = {
                        if (currentQuestionIndex < questionList.size - 1) {
                            currentQuestionIndex++
                            selectedOption = -1
                            isAnswered = false
                        } else {
                            val intent = Intent(context, ResultActivity::class.java )
                            intent.putExtra("USER_NAME", userName)
                            intent.putExtra("SCORE", score)
                            intent.putExtra("TOTAL_QUESTIONS", questionList.size)
                            context.startActivity(intent)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .padding(bottom = 8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White
                    )
                ) {
                    Text(
                        text = if (currentQuestionIndex < questionList.size - 1) "Next Question" else "See Results",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF7B1FA2)
                    )
                }
            }
        }
    }
}
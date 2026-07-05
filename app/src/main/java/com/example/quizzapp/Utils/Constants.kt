package com.example.quizzapp.Utils

import com.example.quizzapp.Model.Questions
import com.example.quizzapp.R

object Constants{

    fun getQuestions(): MutableList<Questions> {
        val questions = mutableListOf<Questions>()

        val quest1 = Questions(
            1,
            "What Country does this flag belong to?",
            R.drawable.flag_of_nepal,
            "Sri Lanka",
            "Nepal",
            "India",
            "Bangladesh",
            2
        )
        questions.add(quest1)

        val quest2 = Questions(
            2,
            "What Country does this flag belong to?",
            R.drawable.flag_of_england,
            "Wales",
            "Scotland",
            "England",
            "Ireland",
            3
        )
        questions.add(quest2)

        val quest3 = Questions(
            3,
            "What Country does this flag belong to?",
            R.drawable.flag_of_argentina,
            "Uruguay",
            "Paraguay",
            "Portugal",
            "Argentina",
            4
        )
        questions.add(quest3)

        val quest4 = Questions(
            4,
            "What Country does this flag belong to?",
            R.drawable.flag_of_india,
            "Sri Lanka",
            "Nepal",
            "India",
            "Bangladesh",
            3
        )
        questions.add(quest4)

        val quest5 = Questions(
            5,
            "What Country does this flag belong to?",
            R.drawable.flag_cape_verde,
            "Sri Lanka",
            "Venezuela",
            "Columbia",
            "Cape Verde",
            4
        )
        questions.add(quest5)

        val quest6 = Questions(
            6,
            "What Country does this flag belong to?",
            R.drawable.flag_of_the_united_states,
            "New Zealand",
            "Australia",
            "USA",
            "China",
            3
        )
        questions.add(quest6)

        val quest7 = Questions(
            1,
            "What Country does this flag belong to?",
            R.drawable.flag_of_portugal,
            "Portugal",
            "Spain",
            "Argentina",
            "Qatar",
            1
        )
        questions.add(quest7)

        return questions
    }
}
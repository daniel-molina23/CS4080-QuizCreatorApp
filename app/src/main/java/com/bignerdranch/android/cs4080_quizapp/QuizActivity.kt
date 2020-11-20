package com.bignerdranch.android.cs4080_quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.sql.BatchUpdateException
import kotlin.math.abs

/**
 * This Activity is Responsible for Showing all the questions to the quiz
 * */

class QuizActivity : AppCompatActivity()
{

    /**Private Data Fields*/
    private lateinit var questionTextView: TextView
    private lateinit var aButton: Button
    private lateinit var bButton: Button
    private lateinit var cButton: Button
    private lateinit var dButton: Button
    private lateinit var nextButton: Button
    private lateinit var prevButton: Button

    //Holds a Reference to the Singleton
    private lateinit var singletonRef: StudyBuddyRepository

    //Used to Index the singletonRef storage of questions
    private var currentQuestion = 0

    //Used to Save the Current Answer
    private lateinit var currentAnswer: String

    /**
     * Method Which Gets Called Every Time the Activity is Created
     * */
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        /**Initializing The Private Data Fields */
        this.questionTextView = findViewById(R.id.current_question)
        this.aButton = findViewById(R.id.a_button)
        this.bButton = findViewById(R.id.b_button)
        this.cButton = findViewById(R.id.c_button)
        this.dButton = findViewById(R.id.d_button)
        this.nextButton = findViewById(R.id.next_button)
        this.prevButton = findViewById(R.id.prev_button)

        //Initializing the SingletonRef
        this.singletonRef = StudyBuddyRepository.get()


        this.initializeQuestion() // Initially Initializing all the questions Based on the Current Question

        //Checking if the user clicked the next question
        this.nextButton.setOnClickListener { view ->
            this.currentQuestion = (this.currentQuestion + 1) % this.singletonRef.storage.size
            this.initializeQuestion() //updating the question on the screen
        }

        //Checking if the user clicked the previous button
        this.prevButton.setOnClickListener {view ->
            this.currentQuestion = abs(this.currentQuestion - 1) % this.singletonRef.storage.size
            this.initializeQuestion() //Updating the question
        }

        //Checking if the User Selected a Answer As well as Validating it
        this.checkAnswer()

    }

    /**Method Which Checks if the User Entered the correct Answer Or Not
     * - It only runs when the user click an answer button
     * */
    private fun checkAnswer()
    {

        this.aButton.setOnClickListener { view ->
            validate("A")
        }

        this.bButton.setOnClickListener { view ->
            validate("B")
        }

        this.cButton.setOnClickListener { view ->
            Log.v("TESTING", "PRINITNG THE CURRENT ANSWER: " + this.currentAnswer)
            validate("C")
        }

        this.dButton.setOnClickListener {view ->
            validate("D")
        }
    }


    /**
     * Helper Function to Reduce code when checking For a Answer
     *
     */
    private fun validate(answerToCheck: String)
    {
        if(this.currentAnswer == answerToCheck)
        {
            Toast.makeText(
                applicationContext,
                "Correct :) ",
                Toast.LENGTH_SHORT
            ).show()
        }
        else
        {
            Toast.makeText(
                applicationContext,
                "Incorrect Try Again :(",
                Toast.LENGTH_SHORT
            ).show()
        }
    }



    /**
     * Initializes all Widgets Based on the current Question
     * */
    private fun initializeQuestion()
    {
        //setting the question
        this.questionTextView.text = singletonRef.storage[currentQuestion].question

        var questionAnswers = singletonRef.storage[currentQuestion].questionAnswers

        //setting the question answers
        this.aButton.text = "A : " + questionAnswers["A"]
        this.bButton.text = "B : " + questionAnswers["B"]
        this.cButton.text = "C : " + questionAnswers["C"]
        this.dButton.text = "D : " + questionAnswers["D"]

        //Setting the current Answer to the question
        this.currentAnswer = singletonRef.storage[currentQuestion].answer

    }


}
package com.bignerdranch.android.cs4080_quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

/**
 * This Activity Will Be The Main Menu Of the Application
 *
 * User Can Either Add Questions Or Take the Quiz
 *
 * */
class MainActivity : AppCompatActivity()
{

    /**Private Data Fields */
    private lateinit var addQuestionButton: Button
    private lateinit var takeQuizButton: Button

    //Getting a Reference to the Singleton
    private lateinit var singletonRef: StudyBuddyRepository

    /**Called Every Time the Activity Starts*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Associating the activity_main layout with this Activity
        setContentView(R.layout.activity_main)

        /**Initializing our Private Data Fields */
        this.addQuestionButton = findViewById(R.id.add_question_button) as Button
        this.takeQuizButton = findViewById(R.id.take_quiz_button) as Button

        this.singletonRef = StudyBuddyRepository.get()

        /**Setting Up Listeners So every time the user clicks a button t*/

        //Checking if the User wants to add a Question
        this.addQuestionButton.setOnClickListener { view ->
            val intent = Intent(this@MainActivity, AddQuestionActivity::class.java)
            startActivity(intent) //Switching to the AddQuestionActivity
        }

        //Checking if the User wants to take the quiz
        this.takeQuizButton.setOnClickListener { view ->

            //No Questions have been Added
            if(this.singletonRef.storage.size == 0)
            {
                Toast.makeText(applicationContext, "Please Add A Question Before Taking the Quiz ", Toast.LENGTH_SHORT).show()
            }
            //Go to the Quiz Activity
            else
            {
                val intent = Intent(this@MainActivity, QuizActivity::class.java)
                startActivity(intent) //Switching to the QuizActivity
            }

        }

    }
}
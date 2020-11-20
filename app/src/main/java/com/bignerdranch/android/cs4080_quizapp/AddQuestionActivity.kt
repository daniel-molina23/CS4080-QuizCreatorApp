package com.bignerdranch.android.cs4080_quizapp


import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * This Activity Is Responsible For Adding Questions To the SingleTon
 *
 * */
class AddQuestionActivity : AppCompatActivity() {

    /**Private Data Fields */
    private lateinit var questionEditText: EditText
    private lateinit var aKeyEditText: EditText
    private lateinit var bKeyEditText: EditText
    private lateinit var cKeyEditText: EditText
    private lateinit var dKeyEditText: EditText
    private lateinit var answerEditText: EditText
    private lateinit var submitButton: Button

    //Reference to the Singleton
    private lateinit var singletonRef: StudyBuddyRepository

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        /**Linking the Layout with this question activity*/
        setContentView(R.layout.activity_add_question)

        /**Initializing all Data Fields */
        this.questionEditText = findViewById(R.id.question_edit_text) as EditText
        this.aKeyEditText = findViewById(R.id.a_edit_text) as EditText
        this.bKeyEditText = findViewById(R.id.b_edit_text) as EditText
        this.cKeyEditText = findViewById(R.id.c_edit_text) as EditText
        this.dKeyEditText = findViewById(R.id.d_edit_text) as EditText
        this.answerEditText = findViewById(R.id.answer_letter) as EditText
        this.submitButton = findViewById(R.id.submit_button) as Button


        //Initializing the Singleton
        this.singletonRef = StudyBuddyRepository.get()

        /**Checking if the User Clicked the Submit Button
         *
         * - Must Have a Question
         * - And an Answer To be able to submit
         * */
        this.submitButton.setOnClickListener { view ->
            this.handleQuestion() //Handles the question when the user submits a question
        }

    }

    //Checks if a Edit text is Empty
    private fun isEmpty(etText: EditText): Boolean {
        val msg: String = etText.text.toString()
        return msg.trim().isEmpty()
    }

    fun handleQuestion()
    {
        //If The user Filled out all the information
        if(!this.questionEditText.text.equals("") && !this.answerEditText.text.equals(""))
        {

            /**Ensuring the Question Answers Are Initialized */
            val aBoolean = isEmpty(this.aKeyEditText)
            val bBoolean = isEmpty(this.bKeyEditText)
            val cBoolean = isEmpty(this.cKeyEditText)
            val dBoolean = isEmpty(this.dKeyEditText)

            if(aBoolean || bBoolean || cBoolean || dBoolean)
            {
                Toast.makeText(
                    applicationContext,
                    "Please Fill Out All Sections Correctly ",
                    Toast.LENGTH_SHORT
                ).show()

            }

            else
            {
                //Ensuring the Answer is Valid
                var answer = this.answerEditText.text.toString()
                answer = answer.toUpperCase()

                //We have a Valid Answer
                if(answer.equals("A") || answer.equals("B") || answer.equals("C") || answer.equals("D"))
                {

                    //Creating the Question
                    var question = Question(
                        this.questionEditText.text.toString(),
                        this.answerEditText.text.toString().toUpperCase()
                    )

                    /**Adding the Answer Questions */

                    question.questionAnswers["A"] = this.aKeyEditText.text.toString()
                    question.questionAnswers["B"] = this.bKeyEditText.text.toString()
                    question.questionAnswers["C"] = this.cKeyEditText.text.toString()
                    question.questionAnswers["D"] = this.dKeyEditText.text.toString()


                    //Saving the Question in the singleTon
                    this.singletonRef.storage.add(question) //Saving the Question

                    //Killing the Activity
                    finish()

                }
                else
                {
                    Toast.makeText(
                        applicationContext,
                        "Please Fill Out All Sections Correctly ",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }

        }
        else
        {
            Toast.makeText(
                applicationContext,
                "Please Fill Out All Sections Correctly ",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

}
package com.bignerdranch.android.cs4080_quizapp

/**
 * This Class Describes what a Question is
 *  A Question Object:
 * - Has a question
 * - Has a hashmap of size four
 *      - Key A: (A text)
 *      - Key B: (B text)
 *      - Key C: (C text)
 *      - key D: (D text)
 * */

class Question
{
    //Going to make theme all Public
    var questionAnswers = mutableMapOf<String, String>() //Holds the Answers to the Question
    lateinit var question: String //The Actual Question
    lateinit var answer: String //Answer to the Question

    //Constructor
    constructor(question: String, answer: String)
    {
        /**Initializing The Private Data Fields */
        this.question = question
        this.answer = answer
    }






}
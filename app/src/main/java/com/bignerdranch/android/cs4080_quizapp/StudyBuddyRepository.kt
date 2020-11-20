package com.bignerdranch.android.cs4080_quizapp

import android.content.Context
import java.lang.IllegalStateException


/**
 * This Will be the Single Ton class Which we will access Throughout the application
 *
 * As well as adding it to the Manifest
 * */
class StudyBuddyRepository private constructor(context: Context)
{
    /**Defining the Storage of the Singleton Will just be public for easy access
     * This will contain all the questions
     * */
    val storage: MutableList<Question> = ArrayList()

    //Defining the SingleTON
    companion object {
        private var INSTANCE: StudyBuddyRepository? = null
        //Ensuring we have a Valid SingleTon
        fun initialize(context: Context){
            if(INSTANCE == null){
                INSTANCE = StudyBuddyRepository(context)
            }
        }
        fun get(): StudyBuddyRepository
        {
            return INSTANCE?:
            throw IllegalStateException("CrimeRepository must be initialized")
        }
    }
}
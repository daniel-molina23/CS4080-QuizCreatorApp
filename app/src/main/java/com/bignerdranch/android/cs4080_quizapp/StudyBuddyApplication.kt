package com.bignerdranch.android.cs4080_quizapp

import android.app.Application

/**
 * Defining the Application Class to Initialize the Singleton Object
 * */
class StudyBuddyApplication : Application()
{
    override fun onCreate() {
        super.onCreate()

        //Initializing the Singleton
        StudyBuddyRepository.initialize(this)
    }
}
package com.aber.ac.uk.cs31620.assignment.datasource

import android.app.Application
import com.aber.ac.uk.cs31620.assignment.model.Exercise

class ExerciseRepository(application: Application) {
    private val exerciseDao = WorkoutPlannerDatabase.getDatabase(application)!!.exerciseDao()

    fun insertExercise(exercise: Exercise) {
        exerciseDao.insertExercise(exercise)
    }

    fun deleteExercise(exercise: Exercise) {
        exerciseDao.deleteExercise(exercise)
    }

     fun getExerciseById(id: Int) =
        exerciseDao.getExerciseById(id)

    fun getExercisesByType(exerciseType: String) =
        exerciseDao.getExercisesByType(exerciseType)

     fun getAllExercises() =
        exerciseDao.getAllExercises()
}
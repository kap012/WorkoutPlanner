package com.aber.ac.uk.cs31620.assignment.model

import androidx.lifecycle.LiveData
import androidx.room.*
import com.aber.ac.uk.cs31620.assignment.model.Exercise

@Dao
interface ExerciseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExercise(exercise: Exercise)

    @Delete
    fun deleteExercise(exercise: Exercise)

    @Query("select * from exercise_table where exerciseId = :id")
    fun getExerciseById(id: Int): Exercise?

    @Query("delete from exercise_table")
    fun deleteAllExercises()

    @Query("select * from exercise_table ORDER BY exerciseId")
    fun getAllExercises(): LiveData<List<Exercise>>

    @Query("select * from exercise_table WHERE exerciseType = :exerciseType")
    fun getExercisesByType(exerciseType: String): LiveData<List<Exercise>>

    @Query("SELECT * FROM exercise_table WHERE exerciseType = :exerciseType")
    fun getExerciseByType(
        exerciseType: String
    ): LiveData<List<Exercise>>
}
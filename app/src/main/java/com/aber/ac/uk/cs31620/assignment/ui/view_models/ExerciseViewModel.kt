package com.aber.ac.uk.cs31620.assignment.ui.view_models

import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.aber.ac.uk.cs31620.assignment.constants.EXERCISE_TYPES
import com.aber.ac.uk.cs31620.assignment.datasource.ExerciseRepository
import com.aber.ac.uk.cs31620.assignment.model.Exercise
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExerciseViewModel(application: Application) : AndroidViewModel(application) {
    private val exerciseRepository: ExerciseRepository = ExerciseRepository(application)

    var topRowIndex by mutableIntStateOf(0)

    var exerciseList: LiveData<List<Exercise>> = exerciseRepository.getExercisesByType(
        EXERCISE_TYPES[topRowIndex])

    fun insertExercise(exercise: Exercise) {
        Log.w("db", "adding exercise")

        viewModelScope.launch(Dispatchers.IO){
            exerciseRepository.insertExercise(exercise)
        }
    }

    fun removeExercise(index: Int) {
        viewModelScope.launch(Dispatchers.IO){
            exerciseRepository.deleteExercise(exerciseList.value!![index])
        }
    }

    fun updateExerciseList(){
        exerciseList = exerciseRepository.getExercisesByType(EXERCISE_TYPES[topRowIndex])
    }

    var workingExercise by mutableStateOf(Exercise())

    fun clearWorkingExercise() {
        workingExercise = Exercise()
    }






}
package com.aber.ac.uk.cs31620.assignment.ui.view_models

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.aber.ac.uk.cs31620.assignment.constants.DAYS_OF_WEEK_LONG
import com.aber.ac.uk.cs31620.assignment.datasource.ExerciseRepository
import com.aber.ac.uk.cs31620.assignment.datasource.SessionRepository
import com.aber.ac.uk.cs31620.assignment.model.Exercise
import com.aber.ac.uk.cs31620.assignment.model.NUMBER_OF_SETS_IN_DROP_SET_CYCLE
import com.aber.ac.uk.cs31620.assignment.model.Session
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.Calendar

const val REP_DURATION_IN_SECONDS = 3

const val REST_BETWEEN_EXERCISES_IN_SECONDS = 180
const val REST_BETWEEN_SETS_IN_SECONDS = 120

const val REST_BETWEEN_DROP_SETS_IN_SECONDS = 180
const val REST_BETWEEN_DROP_SET_CYCLE = 10

class SessionViewModel(application: Application) : AndroidViewModel(application) {
    private val sessionRepository: SessionRepository = SessionRepository(application)
    private val exerciseRepository: ExerciseRepository = ExerciseRepository(application)

    var topRowIndex by mutableIntStateOf(getTodayDayOfWeekIndex())
    var isPickExerciseDialogOpen = mutableStateOf(false)
    var isWorkoutDropdownOpen = mutableStateOf(false)
    var currentSession: LiveData<Session> =
        sessionRepository.getSessionByDayName(DAYS_OF_WEEK_LONG[topRowIndex])
    var allExercises: LiveData<List<Exercise>> = exerciseRepository.getAllExercises()

    var sessionExercises = MutableStateFlow(listOf<Exercise>())
    val sessionDuration = MutableStateFlow("")

    fun addExerciseToCurrentSession(exerciseId: Int) {
        val exerciseIds = currentSession.value!!.exerciseIds
        exerciseIds.add(exerciseId)

        val newSession = Session(
            sessionId = currentSession.value!!.sessionId,
            dayName = currentSession.value!!.dayName,
            workoutType = currentSession.value!!.workoutType,
            exerciseIds = exerciseIds
        )
        viewModelScope.launch(Dispatchers.IO) {
            sessionRepository.insertSession(newSession)
        }
    }

    fun removeExerciseFromSession(exerciseIndex: Int) {
        currentSession.value!!.exerciseIds.removeAt(exerciseIndex)

        viewModelScope.launch(Dispatchers.IO) {
            sessionRepository.updateSessionExerciseIdList(
                sessionId = currentSession.value!!.sessionId!!,
                exerciseIds = currentSession.value!!.exerciseIds
            )
        }
    }

    fun updateDayOfWeekSelection(newIndex: Int) {
        topRowIndex = newIndex

        viewModelScope.launch(Dispatchers.IO) {
            currentSession = sessionRepository.getSessionById(newIndex)
        }
    }

    fun updateCurrentSessionType(newSessionType: String) {
        viewModelScope.launch(Dispatchers.IO) {
            sessionRepository.updateSessionType(
                currentSession.value!!.sessionId!!, newSessionType
            )
        }
    }

    // the Calendar class starts counting from 1 and starts on Sunday
    // that needs to be converted so we can use our list that starts from 0 on Monday
    private fun getTodayDayOfWeekIndex(): Int {
        val dayNumber = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)

        return when (dayNumber) {
            Calendar.MONDAY -> 0
            Calendar.TUESDAY -> 1
            Calendar.WEDNESDAY -> 2
            Calendar.THURSDAY -> 3
            Calendar.FRIDAY -> 4
            Calendar.SATURDAY -> 5
            Calendar.SUNDAY -> 6
            else -> {
                0
            }
        }
    }

    private fun getExerciseList(exerciseIds: List<Int>): List<Exercise>{
        val exerciseList = mutableListOf<Exercise>()
        val indicesToBeRemoved = mutableListOf<Int>()

        exerciseIds.forEachIndexed { listIndex, exerciseId ->
            val exercise = exerciseRepository.getExerciseById(exerciseId)

            if (exercise != null) {
                exerciseList.add(exercise)
            } else {
                indicesToBeRemoved.add(listIndex)
            }
        }

        indicesToBeRemoved.forEach { indexToBeRemoved ->
            currentSession.value?.exerciseIds?.removeAt(
                indexToBeRemoved
            )
        }
        return exerciseList
    }

    fun loadSessionData(exerciseIds: List<Int>) {
        viewModelScope.launch(Dispatchers.IO) {
            val exerciseList = getExerciseList(exerciseIds)
            sessionExercises.value = exerciseList
            sessionDuration.value = getSessionDurationString(exerciseList)
        }
    }

    private fun getSessionDurationString(exercises: List<Exercise>): String {
        val sessionLengthSeconds: Int = getWorkoutLengthInSeconds(exercises)

        val hours = sessionLengthSeconds / 3600
        val minutes = (sessionLengthSeconds % 3600) / 60
        return "${hours}h:${minutes}min"
    }

    private fun getWorkoutLengthInSeconds(exercises: List<Exercise>): Int {
        var durationInSeconds = 0

        for (exercise in exercises) {
            if (exercise.isDropSet) {
                val totalRepsDuration =
                    (exercise.sets * exercise.reps * NUMBER_OF_SETS_IN_DROP_SET_CYCLE) * REP_DURATION_IN_SECONDS
                val totalRestBetweenDropSetsDuration =
                    (exercise.sets - 1) * REST_BETWEEN_DROP_SETS_IN_SECONDS
                val totalRestWithinDropSetsDuration =
                    (exercise.sets - 1) * (NUMBER_OF_SETS_IN_DROP_SET_CYCLE - 1) * REST_BETWEEN_DROP_SET_CYCLE

                durationInSeconds +=
                    totalRepsDuration + totalRestBetweenDropSetsDuration + totalRestWithinDropSetsDuration
            } else {
                val totalRepsDuration = exercise.sets * exercise.reps * REP_DURATION_IN_SECONDS
                val restBetweenSetsDuration = (exercise.sets - 1) * REST_BETWEEN_SETS_IN_SECONDS

                durationInSeconds += restBetweenSetsDuration + totalRepsDuration
            }
            durationInSeconds += REST_BETWEEN_EXERCISES_IN_SECONDS
        }
        return durationInSeconds
    }
}
package com.aber.ac.uk.cs31620.assignment.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.aber.ac.uk.cs31620.assignment.constants.DAYS_OF_WEEK_SHORT
import com.aber.ac.uk.cs31620.assignment.model.Session
import com.aber.ac.uk.cs31620.assignment.ui.components.dialog_boxes.PickExerciseDialog
import com.aber.ac.uk.cs31620.assignment.ui.components.exercise_cards.AddExerciseCard
import com.aber.ac.uk.cs31620.assignment.ui.components.exercise_cards.DayCard
import com.aber.ac.uk.cs31620.assignment.ui.components.exercise_cards.ExerciseList
import com.aber.ac.uk.cs31620.assignment.ui.components.top_level.TopLevelScaffold
import com.aber.ac.uk.cs31620.assignment.ui.components.top_level.TopRow
import com.aber.ac.uk.cs31620.assignment.ui.view_models.SessionViewModel

@Composable
fun WorkoutPlanScreen(
    navController: NavController,
    sessionViewModel: SessionViewModel = viewModel(),
) {
    val topRowIndex = sessionViewModel.topRowIndex
    val currentSession by sessionViewModel.currentSession.observeAsState(initial = Session())
    val sessionExercises by sessionViewModel.sessionExercises.collectAsState()
    val sessionDuration by sessionViewModel.sessionDuration.collectAsState()

    val allExercises by sessionViewModel.allExercises.observeAsState()

    var isPickExerciseDialogOpen by sessionViewModel.isPickExerciseDialogOpen
    var isWorkoutDropdownOpen by sessionViewModel.isWorkoutDropdownOpen

    val exerciseCardModifier = Modifier
        .height(100.dp)
        .padding(start = 40.dp, end = 40.dp, top = 10.dp, bottom = 10.dp)
        .fillMaxWidth()

    sessionViewModel.loadSessionData(currentSession.exerciseIds)

    TopLevelScaffold(navController = navController) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopRow(
                labelList = DAYS_OF_WEEK_SHORT,
                selectedCellLabel = DAYS_OF_WEEK_SHORT[topRowIndex],
                onCellClick = { newDayIndex ->
                    sessionViewModel.updateDayOfWeekSelection(newDayIndex)
                }
            )
            DayCard(modifier = Modifier
                .height(100.dp)
                .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 10.dp),
                currentSession = currentSession,
                isWorkoutDropdownOpen = isWorkoutDropdownOpen,
                openSessionTypeDropdown = { newState ->
                    isWorkoutDropdownOpen = newState
                },
                workoutLengthString = sessionDuration,
                updateSessionType = { newSessionType ->
                    sessionViewModel.updateCurrentSessionType(newSessionType)
                })

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                ExerciseList(
                    modifier = exerciseCardModifier,
                    exerciseList = sessionExercises,
                    removeExercise = { exIndex ->
                        sessionViewModel.removeExerciseFromSession(exIndex)
                        sessionViewModel.loadSessionData(currentSession.exerciseIds)
                    })

                AddExerciseCard(
                    modifier = exerciseCardModifier,
                    contentModifier = Modifier
                        .fillMaxSize()
                        .clickable(onClick = { isPickExerciseDialogOpen = true })
                )
            }

            PickExerciseDialog(
                exercises = allExercises,
                isDialogOpen = isPickExerciseDialogOpen,
                onDismiss = { isPickExerciseDialogOpen = false },
                onExerciseCardClick = { exerciseId ->
                    sessionViewModel.addExerciseToCurrentSession(exerciseId)
                    sessionViewModel.loadSessionData(currentSession.exerciseIds)
                })
        }
    }
}









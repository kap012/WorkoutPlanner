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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.aber.ac.uk.cs31620.assignment.constants.EXERCISE_TYPES
import com.aber.ac.uk.cs31620.assignment.ui.components.dialog_boxes.ExerciseDetailsDialog
import com.aber.ac.uk.cs31620.assignment.ui.components.exercise_cards.AddExerciseCard
import com.aber.ac.uk.cs31620.assignment.ui.components.exercise_cards.ExerciseList
import com.aber.ac.uk.cs31620.assignment.ui.components.top_level.TopLevelScaffold
import com.aber.ac.uk.cs31620.assignment.ui.components.top_level.TopRow
import com.aber.ac.uk.cs31620.assignment.ui.view_models.ExerciseViewModel

@Composable
fun ExercisesScreen(
    navController: NavController, exerciseViewModel: ExerciseViewModel = viewModel()
) {
    var isExerciseDetailsDialogOpen by rememberSaveable { mutableStateOf(false) }
    val topRowIndexState = exerciseViewModel.topRowIndex
    val exerciseList by exerciseViewModel.exerciseList.observeAsState()

    val cardModifier = Modifier
        .height(100.dp)
        .padding(start = 40.dp, end = 40.dp, top = 10.dp, bottom = 10.dp)
        .fillMaxWidth()

    TopLevelScaffold(navController) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopRow(
                labelList = EXERCISE_TYPES,
                selectedCellLabel = EXERCISE_TYPES[topRowIndexState],
                onCellClick = { newIndex ->
                    exerciseViewModel.topRowIndex = newIndex
                    exerciseViewModel.updateExerciseList()
                })

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxWidth()
            ) {
                ExerciseList(
                    modifier = cardModifier,
                    exerciseList = exerciseList,
                    onCardClick = { exercise ->
                        exerciseViewModel.workingExercise = exercise
                        isExerciseDetailsDialogOpen = true
                    },
                    removeExercise = { exercise -> exerciseViewModel.removeExercise(exercise) }
                )

                AddExerciseCard(
                    modifier = cardModifier,
                    contentModifier = Modifier
                        .fillMaxSize()
                        .clickable(onClick = {
                            isExerciseDetailsDialogOpen = true
                        })
                )
            }
        }

        ExerciseDetailsDialog(
            isDialogOpen = isExerciseDetailsDialogOpen,
            workingExercise = exerciseViewModel.workingExercise,
            onDismiss = {
                isExerciseDetailsDialogOpen = false
                exerciseViewModel.clearWorkingExercise()
            },
            onConfirm = { exercise ->
                exerciseViewModel.insertExercise(exercise)
                isExerciseDetailsDialogOpen = false
                exerciseViewModel.clearWorkingExercise()
            }
        )
    }
}





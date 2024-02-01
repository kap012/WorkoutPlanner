package com.aber.ac.uk.cs31620.assignment.ui.components.exercise_cards

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.aber.ac.uk.cs31620.assignment.model.Exercise


@Composable
fun ExerciseList(
    modifier: Modifier,
    exerciseList: List<Exercise>?,
    onCardClick: (Exercise) -> Unit = {},
    removeExercise: (Int) -> Unit
) {

    exerciseList?.let {
        for ((index, exercise) in it.withIndex()) {
            ExerciseCard(
                modifier = modifier,
                exercise = exercise,
                onCardClick = { onCardClick(exercise) },
                onClickRemove = { removeExercise(index) }
            )
        }
    }
}

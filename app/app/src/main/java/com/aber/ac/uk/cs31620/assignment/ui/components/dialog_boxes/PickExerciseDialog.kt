package com.aber.ac.uk.cs31620.assignment.ui.components.dialog_boxes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.aber.ac.uk.cs31620.assignment.model.Exercise
import com.example.assignment.R
import com.aber.ac.uk.cs31620.assignment.ui.components.exercise_cards.SmallExerciseCard

@Composable
fun PickExerciseDialog(
    exercises: List<Exercise>?,
    isDialogOpen: Boolean,
    onDismiss: () -> Unit,
    onExerciseCardClick: (Int) -> Unit
) {
    if (!isDialogOpen) {
        return
    }

    AlertDialog(
        modifier = Modifier.padding(top = 20.dp, bottom = 20.dp),
        onDismissRequest = {
            onDismiss()
        },
        text = {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (exercises != null) {
                    for (ex in exercises) {
                        SmallExerciseCard(
                            exercise = ex,
                            onCardClick = {
                                onExerciseCardClick(ex.exerciseId!!)
                                onDismiss()
                            }
                        )
                    }
                }else{
                    Text(text = stringResource(R.string.no_exercises_text))
                }
            }
        },
        confirmButton = {},
        dismissButton = {
            TextButton(onClick = {
                onDismiss()
            }) {
                Text(text = stringResource(R.string.cancel_button_text))
            }
        })
}
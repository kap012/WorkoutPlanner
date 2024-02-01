package com.aber.ac.uk.cs31620.assignment.ui.components.dialog_boxes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.aber.ac.uk.cs31620.assignment.constants.EXERCISE_TYPES
import com.aber.ac.uk.cs31620.assignment.model.Exercise
import com.aber.ac.uk.cs31620.assignment.ui.components.dropdowns.LabeledDropdownMenu
import com.aber.ac.uk.cs31620.assignment.ui.components.other.InputField
import com.aber.ac.uk.cs31620.assignment.ui.components.other.Toggle
import com.example.assignment.R

@Composable
fun ExerciseDetailsDialog(
    isDialogOpen: Boolean,
    workingExercise: Exercise,
    onDismiss: () -> Unit,
    onConfirm: (Exercise) -> Unit
) {
    if (!isDialogOpen) {
        return
    }
    val focusRequester = remember { FocusRequester() }
    var isExerciseTypeMenuExpanded by rememberSaveable { mutableStateOf(false) }

    val id by rememberSaveable { mutableStateOf(workingExercise.exerciseId) }
    var name by rememberSaveable { mutableStateOf(workingExercise.exerciseName) }
    var type by rememberSaveable { mutableStateOf(workingExercise.exerciseType) }
    var sets by rememberSaveable { mutableStateOf(workingExercise.sets.toString()) }
    var reps by rememberSaveable { mutableStateOf(workingExercise.reps.toString()) }
    var isDropSet by rememberSaveable { mutableStateOf(workingExercise.isDropSet) }
    var setOneWeight by rememberSaveable { mutableStateOf(workingExercise.setOneWeight.toString()) }
    var setTwoWeight by rememberSaveable { mutableStateOf(workingExercise.setTwoWeight.toString()) }
    var setThreeWeight by rememberSaveable { mutableStateOf(workingExercise.setThreeWeight.toString()) }

    AlertDialog(onDismissRequest = {
        onDismiss()
    }, text = {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()),
        ) {
            InputField(text = name,
                label = stringResource(R.string.exercise_name_placeholder_text),
                focusRequester = focusRequester,
                useNumKeyboard = false,
                onValueChange = { newValue -> name = newValue })

            LabeledDropdownMenu(
                isExpanded = isExerciseTypeMenuExpanded,
                updateIsExpanded = { newState -> isExerciseTypeMenuExpanded = newState },
                option = type,
                updateOption = { newExerciseType ->
                    type = newExerciseType
                },
                placeholder = stringResource(R.string.exercise_type_placeholder_text),
                options = EXERCISE_TYPES
            )

            InputField(
                text = if (sets == "0") "" else sets,
                label = stringResource(R.string.number_of_sets_placeholder_text),
                focusRequester = focusRequester,
                useNumKeyboard = true,
                onValueChange = { newValue ->
                    sets = newValue
                }
            )

            InputField(text = if (reps == "0") "" else reps,
                label = stringResource(R.string.number_of_reps_placeholder_text),
                focusRequester = focusRequester,
                useNumKeyboard = true,
                onValueChange = { newValue ->
                    reps = newValue
                })

            InputField(text = if (setOneWeight == "0") "" else setOneWeight,
                label = stringResource(R.string.weight_kg_placeholder_text),
                focusRequester = focusRequester,
                useNumKeyboard = true,
                onValueChange = { newValue ->
                    setOneWeight = newValue
                })

            Toggle(
                isDropSet = isDropSet,
                isDropSetLabel = stringResource(R.string.drop_set_placeholder_text),
                onClick = { newState -> isDropSet = newState }
            )

            if (isDropSet) {
                Divider(modifier = Modifier.padding(top = 10.dp, bottom = 10.dp))

                InputField(
                    text = if (setTwoWeight == "0") "" else setTwoWeight,
                    label = stringResource(R.string.weight_for_set_two_placeholder_text),
                    focusRequester = focusRequester,
                    useNumKeyboard = true,
                    onValueChange = { newValue ->
                        setTwoWeight = newValue
                    },
                    isError = (if (setOneWeight != "0" && setTwoWeight != "0") {
                        setOneWeight < setTwoWeight
                    } else {
                        false
                    }),
                    errorMessage = "Weight for set two must be smaller than for set one"
                )

                InputField(
                    text = if (setThreeWeight == "0") "" else setThreeWeight,
                    label = stringResource(R.string.weight_for_set_three_placeholder_text),
                    focusRequester = focusRequester,
                    useNumKeyboard = true,
                    onValueChange = { newValue ->
                        setThreeWeight = newValue
                    },
                    isError = (if (setTwoWeight != "0" && setThreeWeight != "0") {
                        setTwoWeight < setThreeWeight
                    } else {
                        false
                    }),
                    errorMessage = "Weight for set three must be smaller than for set two"
                )
            }
        }
    },


        confirmButton = {
            TextButton(enabled =
            (name.isNotEmpty() && type.isNotEmpty() && sets != "0" && reps != "0" && setOneWeight != "0")
                    && if (isDropSet) {
                (setTwoWeight != "0" && setThreeWeight != "0") && (setOneWeight > setTwoWeight && setTwoWeight > setThreeWeight)
            } else {
                true
            },
                onClick = {
                    onConfirm(
                        Exercise(
                            id,
                            name,
                            type,
                            sets.toInt(),
                            reps.toInt(),
                            isDropSet,
                            setOneWeight.toInt(),
                            setTwoWeight.toInt(),
                            setThreeWeight.toInt()
                        )
                    )
                }) {
                Text(stringResource(R.string.confirm_button_text))
            }
        }, dismissButton = {
            TextButton(onClick = {
                onDismiss()
            }) {
                Text(stringResource(R.string.cancel_button_text))
            }
        })
}









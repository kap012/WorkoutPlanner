package com.aber.ac.uk.cs31620.assignment.ui.components.icons

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.aber.ac.uk.cs31620.assignment.constants.WorkoutTypeEnum
import com.example.assignment.R

@Composable
fun ExerciseIcon(modifier: Modifier, exerciseType: String){
    Image(
        painter = painterResource(id = getExerciseIconId(exerciseType)),
        contentDescription = exerciseType,
        modifier = modifier
    )
}

private fun getExerciseIconId(exerciseType: String): Int {
    val icon = when (exerciseType) {
        WorkoutTypeEnum.ARMS.type -> R.drawable.arm_icon
        WorkoutTypeEnum.CHEST.type -> R.drawable.chest_icon
        WorkoutTypeEnum.LEGS.type -> R.drawable.legs_icon
        WorkoutTypeEnum.CARDIO.type -> R.drawable.cardio_icon
        WorkoutTypeEnum.OTHER.type -> R.drawable.other_icon
        else -> {
            R.drawable.unknown_icon
        }
    }
    return icon
}






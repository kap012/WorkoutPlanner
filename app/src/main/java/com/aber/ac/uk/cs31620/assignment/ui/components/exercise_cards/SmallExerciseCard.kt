package com.aber.ac.uk.cs31620.assignment.ui.components.exercise_cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.aber.ac.uk.cs31620.assignment.model.Exercise
import com.aber.ac.uk.cs31620.assignment.ui.components.icons.ExerciseIcon
import com.example.assignment.R


@Composable
fun SmallExerciseCard(
    exercise: Exercise,
    onCardClick: () -> Unit = {}
) {
    val cardColor = if (exercise.isDropSet) {
        CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
    }else{
        CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    }

    Card(
        modifier = Modifier
            .height(85.dp)
            .padding(top = 5.dp, bottom = 5.dp),
        colors = cardColor
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .clickable(onClick = onCardClick)
        ) {
            val (exerciseName, setsAndRepsText, weightText, exerciseIcon) = createRefs()

            if (exercise.isDropSet) {
                val (dropSetLabel) = createRefs()
                Text(text = stringResource(R.string.drop_set_label_text),
                    fontSize = 10.sp,
                    modifier = Modifier
                        .padding(start = 12.dp)
                        .constrainAs(dropSetLabel) {
                            top.linkTo(exerciseIcon.bottom)
                        })
            }

            ExerciseIcon(
                modifier = Modifier
                    .padding(start = 20.dp)
                    .constrainAs(exerciseIcon) {
                        start.linkTo(parent.start)
                        centerVerticallyTo(parent)
                    },
                exerciseType = exercise.exerciseType
            )

            Text(
                text = exercise.exerciseName,
                fontSize = 15.sp,
                modifier = Modifier
                    .padding(start = 20.dp, top = 10.dp)
                    .constrainAs(exerciseName) {
                        start.linkTo(exerciseIcon.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(setsAndRepsText.top)
                    }
            )

            Text(text = exercise.setsAndRepsToString(),
                fontSize = 10.sp,
                modifier = Modifier
                    .padding(start = 25.dp)
                    .constrainAs(setsAndRepsText) {
                        top.linkTo(exerciseName.bottom)
                        bottom.linkTo(weightText.top)
                        start.linkTo(exerciseIcon.end)
                    }
            )

            Text(text = exercise.weightToString(),
                fontSize = 10.sp,
                modifier = Modifier
                    .padding(start = 25.dp)
                    .constrainAs(weightText) {
                        top.linkTo(setsAndRepsText.bottom)
                        start.linkTo(exerciseIcon.end)
                    }
            )
        }

    }
}

package com.aber.ac.uk.cs31620.assignment.ui.components.exercise_cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.assignment.R


@Composable
fun AddExerciseCard(modifier: Modifier, contentModifier: Modifier) {
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = contentModifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                Icons.Outlined.AddCircle,
                contentDescription = stringResource(id = R.string.add_exercise_button),
            )

        }
    }
}
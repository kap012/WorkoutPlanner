package com.aber.ac.uk.cs31620.assignment.ui.components.dropdowns

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.aber.ac.uk.cs31620.assignment.constants.EXERCISE_TYPES
import com.example.assignment.R


@Composable
fun SessionTypeDropdownMenu(
    isOpen: Boolean,
    updateIsOpen: (Boolean) -> Unit,
    updateSessionType: (String) -> Unit
) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopEnd)
    ) {
        IconButton(onClick = { updateIsOpen(!isOpen) }) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = stringResource(R.string.workout_dropdown_description)
            )
        }

        DropdownMenu(
            expanded = isOpen,
            onDismissRequest = { updateIsOpen(false) }
        ) {
            for (workoutType in EXERCISE_TYPES) (
                    DropdownMenuItem(
                        text = { Text(workoutType) },
                        onClick = {
                            updateSessionType(workoutType)
                            updateIsOpen(false)
                            Toast.makeText(context, context.getString(R.string.workout_type_selected_toast_template, workoutType), Toast.LENGTH_SHORT).show()
                        }
                    )
                    )
        }
    }
}


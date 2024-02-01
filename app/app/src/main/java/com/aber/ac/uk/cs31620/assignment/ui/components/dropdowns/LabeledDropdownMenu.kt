package com.aber.ac.uk.cs31620.assignment.ui.components.dropdowns

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.assignment.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LabeledDropdownMenu(
    isExpanded: Boolean,
    updateIsExpanded: (Boolean) -> Unit,
    option: String,
    updateOption: (String) -> Unit,
    options: List<String>,
    placeholder: String
) {
    val context = LocalContext.current

    Box(modifier = Modifier.padding(top = 10.dp)) {
        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = {
                updateIsExpanded(!isExpanded)
            }
        ) {
            OutlinedTextField(
                placeholder = { Text(text = placeholder) },
                value = option,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) },
                modifier = Modifier.menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { updateIsExpanded(false) }
            ) {
                options.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            updateOption(item)
                            updateIsExpanded(false)
                            Toast.makeText(context, context.getString(R.string.workout_type_selected_toast_template, item), Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }
        }
    }
}
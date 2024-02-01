package com.aber.ac.uk.cs31620.assignment.ui.components.other

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.KeyboardType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    text: String,
    isError: Boolean = false,
    errorMessage: String = "",
    label: String,
    focusRequester: FocusRequester,
    useNumKeyboard: Boolean,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = text,
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester),
        keyboardOptions = when (useNumKeyboard) {
            true -> KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            false -> KeyboardOptions.Default
        },
        isError = isError,
        supportingText = {
            if (isError) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error
                )
            }
        },
        onValueChange = { newValue ->
            onValueChange(newValue)
        },
        label = { Text(label) },
    )
}
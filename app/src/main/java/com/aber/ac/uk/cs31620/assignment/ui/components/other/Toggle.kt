package com.aber.ac.uk.cs31620.assignment.ui.components.other

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Toggle(
    isDropSet: Boolean,
    isDropSetLabel: String,
    onClick: (Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = isDropSetLabel, modifier = Modifier.padding(start = 10.dp, end = 10.dp)
        )
        Switch(checked = isDropSet, onCheckedChange = { onClick(it) })
    }
}

package com.aber.ac.uk.cs31620.assignment.ui.components.top_level

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun TopRow(
    labelList: List<String>,
    selectedCellLabel: String,
    onCellClick: (Int) -> Unit
) {
    val regularCellModifier = Modifier
        .border(
            BorderStroke(2.dp, MaterialTheme.colorScheme.secondaryContainer)
        )
        .height(75.dp)

    val selectedCellModifier = Modifier
        .border(
            BorderStroke(3.dp, MaterialTheme.colorScheme.onSecondaryContainer),
            shape = RoundedCornerShape(5.dp)
        )
        .height(75.dp)


    Row() {
        labelList.forEachIndexed { index, label ->
            if (label == selectedCellLabel) {
                RowCell(
                    cellLabel = label,
                    cellIndex = index,
                    modifier = selectedCellModifier.weight(1f),
                    onClick = { onCellClick(index) }
                )

            } else {
                RowCell(
                    cellLabel = label,
                    cellIndex = index,
                    modifier = regularCellModifier.weight(1f),
                    onClick = { onCellClick(index) }
                )
            }
        }
    }
}

@Composable
private fun RowCell(
    cellLabel: String,
    cellIndex: Int,
    modifier: Modifier,
    onClick: (Int) -> Unit
) {
    Box(
        modifier = modifier.clickable(onClick = { onClick(cellIndex) }),
    ) {
        Text(
            text = cellLabel,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight()
        )
    }
}

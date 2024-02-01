package com.aber.ac.uk.cs31620.assignment.ui.components.exercise_cards

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.aber.ac.uk.cs31620.assignment.model.Session
import com.aber.ac.uk.cs31620.assignment.ui.components.icons.ExerciseIcon
import com.aber.ac.uk.cs31620.assignment.ui.components.dropdowns.SessionTypeDropdownMenu
import com.example.assignment.R

@Composable
fun DayCard(
    modifier: Modifier,
    currentSession: Session = Session(),
    workoutLengthString: String,
    isWorkoutDropdownOpen: Boolean,
    openSessionTypeDropdown: (Boolean) -> Unit,
    updateSessionType: (String) -> Unit
) {
    Card(
        modifier = modifier
    ) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (icon, dayName, workoutType, sessionLength) = createRefs()

            ExerciseIcon(
                modifier = Modifier
                    .padding(start = 20.dp)
                    .constrainAs(icon) {
                        start.linkTo(parent.start)
                        centerVerticallyTo(parent)
                    }, exerciseType = currentSession.workoutType
            )

            Text(text = currentSession.dayName,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(start = 20.dp, top = 10.dp)
                    .wrapContentHeight()
                    .constrainAs(dayName) {
                        start.linkTo(icon.end)
                    })

            Text(text = stringResource(R.string.session_type_template, currentSession.workoutType),
                modifier = Modifier
                    .padding(start = 25.dp)
                    .wrapContentHeight()
                    .constrainAs(workoutType) {
                        start.linkTo(icon.end)
                        top.linkTo(dayName.bottom)
                    })

            Text(text = workoutLengthString,
                modifier = Modifier
                    .padding(start = 25.dp)
                    .wrapContentHeight()
                    .constrainAs(sessionLength) {
                        start.linkTo(workoutType.end)
                        top.linkTo(dayName.bottom)
                    })

            SessionTypeDropdownMenu(isOpen = isWorkoutDropdownOpen,
                updateIsOpen = { newState -> openSessionTypeDropdown(newState) },
                updateSessionType = { newSessionType -> updateSessionType(newSessionType) })
        }

    }
}

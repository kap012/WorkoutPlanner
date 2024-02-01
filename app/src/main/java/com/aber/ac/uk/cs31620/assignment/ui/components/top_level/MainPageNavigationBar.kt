package com.aber.ac.uk.cs31620.assignment.ui.components.top_level

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.aber.ac.uk.cs31620.assignment.navigation.Screen
import com.aber.ac.uk.cs31620.assignment.navigation.screens
import com.aber.ac.uk.cs31620.assignment.ui.components.icons.IconGroup
import com.example.assignment.R


@Composable
fun MainPageNavigationBar(navController: NavController) {
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry.value?.destination

    val icons = mapOf(
        Screen.WorkoutPlan to IconGroup(
            filledIcon = Icons.Filled.AccountBox,
            outlineIcon = Icons.Outlined.AccountBox,
            label = stringResource(id = R.string.workout_plan)
        ),
        Screen.Exercises to IconGroup(
            filledIcon = Icons.Filled.AddCircle,
            outlineIcon = Icons.Outlined.AddCircle,
            label = stringResource(id = R.string.exercises)
        )
    )

    NavigationBar {
        screens.forEach { screen ->
            val isSelected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
            val labelText = icons[screen]!!.label

            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = (
                                if (isSelected)
                                    icons[screen]!!.filledIcon
                                else
                                    icons[screen]!!.outlineIcon
                                ),
                        contentDescription = labelText
                    )
                },
                label = { Text(labelText) },
                selected = isSelected,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

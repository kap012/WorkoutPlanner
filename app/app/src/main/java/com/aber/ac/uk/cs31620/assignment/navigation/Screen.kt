package com.aber.ac.uk.cs31620.assignment.navigation

sealed class Screen(val route: String) {
    object WorkoutPlan : Screen("workout_plan")
    object Exercises : Screen("exercises")
}

val screens = listOf(
    Screen.WorkoutPlan,
    Screen.Exercises
)


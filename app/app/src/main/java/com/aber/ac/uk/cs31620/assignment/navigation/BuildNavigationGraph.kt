package com.aber.ac.uk.cs31620.assignment.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aber.ac.uk.cs31620.assignment.ui.screens.ExercisesScreen
import com.aber.ac.uk.cs31620.assignment.ui.screens.WorkoutPlanScreen


@Composable
fun BuildNavigationGraph(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.WorkoutPlan.route
    ){
        composable(Screen.WorkoutPlan.route) { WorkoutPlanScreen(navController, viewModel()) }
        composable(Screen.Exercises.route) { ExercisesScreen(navController, viewModel()) }
    }
}


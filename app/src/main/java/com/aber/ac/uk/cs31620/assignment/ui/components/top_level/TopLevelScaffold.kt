package com.aber.ac.uk.cs31620.assignment.ui.components.top_level

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopLevelScaffold(
    navController: NavController,
    pageContent: @Composable (innerPadding: PaddingValues) -> Unit = { }
) {
    Scaffold(
        topBar = {
            MainPageTopAppBar()
        },
        bottomBar = {
            MainPageNavigationBar(navController)
        },
        content = { innerPadding -> pageContent(innerPadding) }
    )
}
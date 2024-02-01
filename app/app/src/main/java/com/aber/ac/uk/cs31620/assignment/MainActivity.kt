package com.aber.ac.uk.cs31620.assignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.aber.ac.uk.cs31620.assignment.navigation.BuildNavigationGraph
import com.aber.ac.uk.cs31620.assignment.ui.theme.AssignmentTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AssignmentTheme {
                BuildNavigationGraph()
            }
        }
    }
}




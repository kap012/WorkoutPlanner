package com.aber.ac.uk.cs31620.assignment.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aber.ac.uk.cs31620.assignment.constants.WorkoutTypeEnum

@Entity(tableName = "session_table")
data class Session(
    @PrimaryKey(autoGenerate = true)
    val sessionId: Int? = null,
    val dayName: String = "",
    var workoutType: String = "",
    var exerciseIds: MutableList<Int> = mutableListOf()
)

val DEFAULT_SESSIONS = mutableListOf(
    Session(
        sessionId = 0,
        dayName = "Monday",
        workoutType = WorkoutTypeEnum.OTHER.type
    ),
    Session(
        sessionId = 1,
        dayName = "Tuesday",
        workoutType = WorkoutTypeEnum.OTHER.type
    ),
    Session(
        sessionId = 2,
        dayName = "Wednesday",
        workoutType = WorkoutTypeEnum.OTHER.type
    ),
    Session(
        sessionId = 3,
        dayName = "Thursday",
        workoutType = WorkoutTypeEnum.OTHER.type
    ),
    Session(
        sessionId = 4,
        dayName = "Friday",
        workoutType = WorkoutTypeEnum.OTHER.type
    ),
    Session(
        sessionId = 5,
        dayName = "Saturday",
        workoutType = WorkoutTypeEnum.OTHER.type
    ),
    Session(
        sessionId = 6,
        dayName = "Sunday",
        workoutType = WorkoutTypeEnum.OTHER.type
    )
)
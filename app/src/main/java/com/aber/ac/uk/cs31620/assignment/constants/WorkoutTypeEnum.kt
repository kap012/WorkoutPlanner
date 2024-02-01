package com.aber.ac.uk.cs31620.assignment.constants

enum class WorkoutTypeEnum(val type: String) {
    ARMS("Arms"),
    CHEST("Chest"),
    LEGS("Legs"),
    CARDIO("Cardio"),
    OTHER("Other")
}

val EXERCISE_TYPES = WorkoutTypeEnum.values().map { it.type }


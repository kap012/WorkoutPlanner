package com.aber.ac.uk.cs31620.assignment.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aber.ac.uk.cs31620.assignment.constants.EXERCISE_TYPES

@Entity(tableName = "exercise_table")
data class Exercise(
    @PrimaryKey(autoGenerate = true)
    val exerciseId: Int? = null,
    var exerciseName: String = "",
    var exerciseType: String = "",
    var sets: Int = 0,
    var reps: Int = 0,
    var isDropSet: Boolean = false,
    var setOneWeight: Int = 0,
    var setTwoWeight: Int = 0,
    var setThreeWeight: Int = 0,
) {

    fun setsAndRepsToString(): String {
        return "Sets:$sets Reps:$reps"
    }

    fun weightToString(): String {
        return if (!this.isDropSet) {
            "Weight: $setOneWeight kg"
        } else {
            "Weight: $setOneWeight kg, $setTwoWeight kg, $setThreeWeight kg"
        }
    }
}

const val NUMBER_OF_SETS_IN_DROP_SET_CYCLE = 3

var DEFAULT_EXERCISES: List<Exercise> = populateDefaultExercises()

private fun populateDefaultExercises(): List<Exercise> {
    val list = mutableListOf<Exercise>()

    for (exerciseType in EXERCISE_TYPES) {
        list.add(
            Exercise(
                exerciseName = "$exerciseType exercise",
                exerciseType = exerciseType,
                sets = 3,
                reps = 12,
                isDropSet = false,
                setOneWeight = 20
            )
        )

        list.add(
            Exercise(
                exerciseName = "$exerciseType drop set exercise",
                exerciseType = exerciseType,
                sets = 3,
                reps = 10,
                isDropSet = true,
                setOneWeight = 30,
                setTwoWeight = 20,
                setThreeWeight = 10,
            )
        )


    }
    return list
}



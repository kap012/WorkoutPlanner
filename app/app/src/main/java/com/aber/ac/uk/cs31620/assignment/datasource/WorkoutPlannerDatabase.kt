package com.aber.ac.uk.cs31620.assignment.datasource

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.aber.ac.uk.cs31620.assignment.model.DEFAULT_EXERCISES
import com.aber.ac.uk.cs31620.assignment.model.DEFAULT_SESSIONS
import com.aber.ac.uk.cs31620.assignment.model.Exercise
import com.aber.ac.uk.cs31620.assignment.model.ExerciseDao
import com.aber.ac.uk.cs31620.assignment.model.Session
import com.aber.ac.uk.cs31620.assignment.model.SessionDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [Exercise::class, Session::class], version = 1
)
@TypeConverters(ListToJsonConverter::class)
abstract class WorkoutPlannerDatabase : RoomDatabase() {
    abstract fun exerciseDao(): ExerciseDao
    abstract fun sessionDao(): SessionDao

    companion object {
        private var instance: WorkoutPlannerDatabase? = null
        private val coroutineScope = CoroutineScope(Dispatchers.IO)

        @Synchronized
        fun getDatabase(ctx: Context): WorkoutPlannerDatabase? {
            if (instance == null) {
                Log.w("db", "Initializing the db")

                instance = Room.databaseBuilder(
                    ctx.applicationContext,
                    WorkoutPlannerDatabase::class.java,
                    "workout_planner_database"
                ).addCallback(roomDatabaseCallback(ctx)).build()
            }
            return instance
        }

        private fun roomDatabaseCallback(context: Context): Callback {
            return object : Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    coroutineScope.launch {
                        populateDatabase(getDatabase(context)!!)
                    }
                }
            }
        }

        private fun populateDatabase(instance: WorkoutPlannerDatabase) {
            Log.w("db", "Populating the db")
            for (day in DEFAULT_SESSIONS) {
                instance.sessionDao().insertSession(day)
            }

            for (exercise in DEFAULT_EXERCISES) {
                instance.exerciseDao().insertExercise(exercise)
            }

        }
    }
}
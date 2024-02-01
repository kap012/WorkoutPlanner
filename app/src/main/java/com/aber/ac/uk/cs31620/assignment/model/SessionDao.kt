package com.aber.ac.uk.cs31620.assignment.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aber.ac.uk.cs31620.assignment.model.Session

@Dao
interface SessionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSession(session: Session)

    @Query("UPDATE session_table SET workoutType = :newWorkoutType WHERE sessionId = :sessionId")
    fun updateSession(sessionId: Int, newWorkoutType: String)

    @Query("UPDATE session_table SET exerciseIds = :exerciseIds WHERE sessionId = :sessionId")
    fun updateSessionExerciseIdList(sessionId: Int, exerciseIds: List<Int>)

    @Query("UPDATE session_table SET workoutType = :workoutType")
    fun updateWorkoutType(workoutType: String)

    @Query("select * from session_table where sessionId = :sessionId")
    fun getSessionById(sessionId: Int): LiveData<Session>

    @Query("select * from session_table where dayName = :dayName")
    fun getSessionByDayName(dayName: String): LiveData<Session>

    @Query(
        """update session_table  
            set dayName = "", workoutType =""
            where sessionId = :sessionId
            """
    )
    fun clearData(sessionId: Int)

    @Query("select * from session_table")
    fun getAllSessions(): LiveData<List<Session>>


}
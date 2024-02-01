package com.aber.ac.uk.cs31620.assignment.datasource

import android.app.Application
import androidx.lifecycle.LiveData
import com.aber.ac.uk.cs31620.assignment.datasource.WorkoutPlannerDatabase
import com.aber.ac.uk.cs31620.assignment.model.Session

class SessionRepository(application: Application) {
    private val sessionDao = WorkoutPlannerDatabase.getDatabase(application)!!.sessionDao()

    fun insertSession(session: Session) {
        sessionDao.insertSession(session)
    }

    fun updateSessionType(sessionId: Int, newType: String) {
        sessionDao.updateSession(sessionId, newType)
    }

    fun updateSessionExerciseIdList(sessionId: Int, exerciseIds: List<Int>) {
        sessionDao.updateSessionExerciseIdList(sessionId, exerciseIds)
    }

    fun getSessionById(sessionId: Int): LiveData<Session> =
        sessionDao.getSessionById(sessionId)

     fun getSessionByDayName(dayName: String): LiveData<Session> =
        sessionDao.getSessionByDayName(dayName)

}
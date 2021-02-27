package com.dinesh.shaadi.data.local

import androidx.room.TypeConverter
import com.dinesh.shaadi.data.entities.MatchStatus

class MatchConverter {

    @TypeConverter
    fun saveStatus(status: MatchStatus): String {
        return status.name
    }

    @TypeConverter
    fun getStatus(status: String): MatchStatus {
        return when (status) {
            MatchStatus.PENDING.name -> MatchStatus.PENDING
            MatchStatus.ACCEPTED.name -> MatchStatus.ACCEPTED
            MatchStatus.REJECTED.name -> MatchStatus.REJECTED
            else -> MatchStatus.PENDING
        }
    }

}
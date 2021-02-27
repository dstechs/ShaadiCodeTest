package com.dinesh.shaadi.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_matches")
data class MatchDisplayModel(
    var name: String,
    var image: String,
    var age: String,
    var gender: String,
    var location: String,
    var accepted_state: MatchStatus = MatchStatus.PENDING,
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null
)

enum class MatchStatus {
    PENDING, ACCEPTED, REJECTED
}
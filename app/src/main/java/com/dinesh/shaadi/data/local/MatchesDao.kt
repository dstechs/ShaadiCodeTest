package com.dinesh.shaadi.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.dinesh.shaadi.data.entities.MatchDisplayModel

@Dao
interface MatchesDao {
    @Query("SELECT * FROM tb_matches")
    fun getAllMatches(): LiveData<MutableList<MatchDisplayModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: MutableList<MatchDisplayModel>)

    @Update
    suspend fun update(characters: MatchDisplayModel)

    @Query("DELETE FROM tb_matches")
    suspend fun deleteAll()
}
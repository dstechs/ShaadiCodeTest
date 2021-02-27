package com.dinesh.shaadi.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dinesh.shaadi.data.entities.MatchDisplayModel
import com.dinesh.shaadi.data.entities.MatchesModel

@Database(entities = [MatchDisplayModel::class], version = 1, exportSchema = false)
@TypeConverters(MatchConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun characterDao(): MatchesDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "shaadimatches")
                .fallbackToDestructiveMigration()
                .build()
    }

}
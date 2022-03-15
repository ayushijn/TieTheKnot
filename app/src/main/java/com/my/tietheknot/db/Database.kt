package com.my.tietheknot.db

import android.content.Context
import androidx.room.RoomDatabase
import com.my.tietheknot.db.UserDao
import androidx.room.Room

@androidx.room.Database(entities = [User::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract val dao: UserDao?

    companion object {
        var database: Database? = null
        fun getInstance(context: Context): Database? {
            if (null == database) {
                database = buildDatabaseInstance(context)
            }
            return database
        }

        private fun buildDatabaseInstance(context: Context): Database {
            return Room.databaseBuilder(
                context,
                Database::class.java, "ALL_NOTES"
            )
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries().build()
        }
    }
}
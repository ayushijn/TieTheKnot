package com.my.tietheknot.db

import android.content.Context
import androidx.room.RoomDatabase
import androidx.room.Room

@androidx.room.Database(entities = [User::class], version = 1)
abstract class MyDatabase : RoomDatabase() {
    abstract val dao: UserDao?

    companion object {
        var myDatabase: MyDatabase? = null
        fun getInstance(context: Context): MyDatabase? {
            if (null == myDatabase) {
                myDatabase = buildDatabaseInstance(context)
            }
            return myDatabase
        }

        private fun buildDatabaseInstance(context: Context): MyDatabase {
            return Room.databaseBuilder(
                context,
                MyDatabase::class.java, "USERS"
            )
                .fallbackToDestructiveMigration()
               .build()
        }
    }
}
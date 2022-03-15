package com.my.tietheknot.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getUserList() : List<User>

    /*
    * Insert the object in database
    * @param note, object to be inserted
    */
    @Insert
    fun insert(user: User)
}
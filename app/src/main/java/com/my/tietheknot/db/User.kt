package com.my.tietheknot.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
class User {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private val id = 0

    @ColumnInfo(name = "status")
    private val status = 0
}
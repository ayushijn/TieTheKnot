package com.my.tietheknot.db

import androidx.annotation.NonNull
import androidx.databinding.BaseObservable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
 data class User (

    @ColumnInfo(name = "id")
    @NonNull
    @PrimaryKey
    var email : String ="",

    @ColumnInfo(name = "status")
    var status : Int = 0


) : BaseObservable() {
    constructor() : this(
    "", 0
    )

}
package com.example.madlevel5task2.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*


@Parcelize
@Entity(tableName = "gameTable")
data class Game (
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "platform")
    var platform: String,
    @ColumnInfo(name = "release")
    var release: Calendar,


    @ColumnInfo
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
):Parcelable
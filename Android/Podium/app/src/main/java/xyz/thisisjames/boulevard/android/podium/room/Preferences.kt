package xyz.thisisjames.boulevard.android.podium.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "preference_table")
class Preferences(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "prefname") val prefname : String,
    @ColumnInfo(name = "prefvalue") val prefvalue : String
)
package com.example.apicasa2

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "gatos")
data class GatoEntity (

    @PrimaryKey (true) val id : Int = 0,
    @ColumnInfo (name = "name" ) val name : String?,
    @ColumnInfo (name = "temperament" )val temperament : String?,
    @ColumnInfo (name = "origin" )val origin : String?

)
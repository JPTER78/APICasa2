package com.example.apicasa2

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [GatoEntity::class], version = 1)
abstract class GatoDatabase : RoomDatabase() {

    abstract fun gatoDao():GatoDao

}
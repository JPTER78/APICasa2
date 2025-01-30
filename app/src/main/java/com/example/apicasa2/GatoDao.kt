package com.example.apicasa2

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert

import androidx.room.Query


@Dao
interface GatoDao {

    @Insert
    fun insertAll(gatoEntityList: List<GatoEntity>)

    @Insert
    fun insertOne(gato: GatoEntity)

    @Delete
    fun delete(gato: GatoEntity)

    @Query("SELECT * FROM gatos")
    fun getAll(): List<GatoEntity>

    @Query("SELECT * FROM gatos WHERE name = :name LIMIT 1")
    fun getGatoByName(name: String): GatoEntity?
}

package com.example.apicasa2

import android.app.Application
import androidx.room.Room

class AppApplication : Application() {

    companion object {

        lateinit var gatoDatabase: GatoDatabase

    }

    override fun onCreate() {

        super.onCreate()

        gatoDatabase = Room.databaseBuilder(
            this,
            GatoDatabase::class.java,
            "CajeroDatabase"
        ).build()

    }

}
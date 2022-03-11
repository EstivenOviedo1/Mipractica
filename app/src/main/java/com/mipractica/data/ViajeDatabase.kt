package com.mipractica.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mipractica.model.Viaje

@Database(entities = [Viaje::class], version = 1, exportSchema = false)
abstract  class ViajeDatabase: RoomDatabase() {
    abstract fun viajeDao() : ViajeDoa

    companion object{
        @Volatile
        private var INSTANCE: ViajeDatabase? = null

        fun getDatabase(context: android.content.Context) : ViajeDatabase{
            val instance = INSTANCE
            if(instance != null){
                return instance
            }
            synchronized(this){
                val basedatos = Room.databaseBuilder(
                    context.applicationContext,
                    ViajeDatabase::class.java,
                    "viaje_database"
                ).build()
                INSTANCE = basedatos
                return basedatos
            }
        }
    }

}
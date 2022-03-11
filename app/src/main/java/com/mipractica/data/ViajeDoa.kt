package com.mipractica.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mipractica.model.Viaje

@Dao
interface ViajeDoa {
    @Query("Select * from Viaje")
    fun getAllData() : LiveData<List<Viaje>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addViaje(viaje: Viaje)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateViaje(viaje: Viaje)

    @Delete
    suspend fun deleteViaje(viaje: Viaje)

}
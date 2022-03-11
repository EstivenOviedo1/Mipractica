package com.mipractica.repository

import androidx.lifecycle.LiveData
import com.mipractica.data.ViajeDoa
import com.mipractica.model.Viaje

class ViajeRepository(private val viajeDao : ViajeDoa) {
    val getAllData: LiveData<List<Viaje>> = viajeDao.getAllData()


    suspend fun addViaje(viaje: Viaje){
        viajeDao.addViaje(viaje)
    }

    suspend fun updateViaje(viaje: Viaje){
        viajeDao.updateViaje(viaje)
    }

    suspend fun deleteViaje(viaje: Viaje){
        viajeDao.deleteViaje(viaje)
    }


}
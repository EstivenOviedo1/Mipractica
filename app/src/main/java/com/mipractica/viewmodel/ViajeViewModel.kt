package com.mipractica.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.mipractica.data.ViajeDatabase
import com.mipractica.model.Viaje
import com.mipractica.repository.ViajeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViajeViewModel (application: Application) : AndroidViewModel(application) {


    val getAllData: LiveData<List<Viaje>>
    private val repository: ViajeRepository

    init {
        val viajeDao = ViajeDatabase.getDatabase(application).viajeDao()
        repository = ViajeRepository(viajeDao)
        getAllData = repository.getAllData
    }


    fun addViaje(viaje: Viaje){
        viewModelScope.launch(Dispatchers.IO) {repository.addViaje(viaje)}
    }

    fun updateViaje(viaje: Viaje){
        viewModelScope.launch(Dispatchers.IO) {repository.updateViaje(viaje)}
    }

    fun deleteViaje(viaje: Viaje){
        viewModelScope.launch(Dispatchers.IO) {repository.deleteViaje(viaje)}
    }
}
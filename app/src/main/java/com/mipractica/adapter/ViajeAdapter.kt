package com.mipractica.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.mipractica.databinding.ViajeFilaBinding
import com.mipractica.model.Viaje
import com.mipractica.ui.viaje.ViajeFragmentDirections

class ViajeAdapter: RecyclerView.Adapter<ViajeAdapter.ViajeViewHolder>() {

    private var listaViajes =  emptyList<Viaje>()


    inner class ViajeViewHolder(private val itemBinding: ViajeFilaBinding) : RecyclerView.ViewHolder(itemBinding.root){
        fun bind(viaje: Viaje){
            itemBinding.tvNombre.text = viaje.nombre
            itemBinding.tvLugar.text = viaje.lugar
            itemBinding.tvPoblacion.text = viaje.poblacion.toString()
            itemBinding.tvDeuda.text = viaje.precio.toString()

            itemBinding.vistaFila.setOnClickListener{
                val action = ViajeFragmentDirections
                    .actionNavViajeToUpdateViajeFragment(viaje)
                itemView.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViajeViewHolder {
        val itemBinding = ViajeFilaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViajeViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViajeViewHolder, position: Int) {
        val viajeActual = listaViajes[position]
        holder.bind(viajeActual)
    }

    override fun getItemCount(): Int {
        return listaViajes.size
    }

    fun setData(viajes: List<Viaje>){
        this.listaViajes = viajes
        notifyDataSetChanged() // Se redibuja la lista
    }

}
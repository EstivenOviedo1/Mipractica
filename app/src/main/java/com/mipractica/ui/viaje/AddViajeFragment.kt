package com.mipractica.ui.viaje

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.mipractica.R
import com.mipractica.databinding.FragmentAddViajeBinding
import com.mipractica.model.Viaje
import com.mipractica.viewmodel.ViajeViewModel


class AddViajeFragment : Fragment() {


    private lateinit var viajeViewModel: ViajeViewModel
    private var _binding: FragmentAddViajeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viajeViewModel = ViewModelProvider(this)[ViajeViewModel::class.java]
        _binding = FragmentAddViajeBinding.inflate(inflater, container, false)

        binding.btAgregar.setOnClickListener {
            addViaje()
        }

       return  binding.root
    }


    private fun addViaje(){

        val nombre = binding.etNombre.text.toString()
        if (nombre.isNotEmpty()) {
            val lugar = binding.etLugar.text.toString()
            val poblacion = binding.etPoblacion.text.toString().toDouble()
            val precio = binding.etPrecio.text.toString().toDouble()

            val viaje = Viaje(0, nombre, lugar, poblacion, precio)
            viajeViewModel.addViaje(viaje)
        } else {
            Toast.makeText(requireContext(), getString(R.string.msg_agregado), Toast.LENGTH_SHORT)
                .show()
        }
        findNavController().navigate(R.id.action_addViajeFragment_to_nav_viaje)
    }

}
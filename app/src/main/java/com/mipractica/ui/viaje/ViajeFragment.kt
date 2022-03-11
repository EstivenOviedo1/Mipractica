package com.mipractica.ui.viaje

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mipractica.R
import com.mipractica.adapter.ViajeAdapter

import com.mipractica.databinding.FragmentViajeBinding
import com.mipractica.viewmodel.ViajeViewModel

class ViajeFragment : Fragment() {

    private var _binding: FragmentViajeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        var viajeViewModel =
            ViewModelProvider(this).get(ViajeViewModel::class.java)

        _binding = FragmentViajeBinding.inflate(inflater, container, false)

        binding.fbAgregar.setOnClickListener{
            findNavController().navigate(R.id.action_nav_viaje_to_addViajeFragment)
        }

        // Activar el RecyclerView
        val viajeAdapter = ViajeAdapter()
        val reciclador = binding.reciclador
        reciclador.adapter = viajeAdapter
        reciclador.layoutManager = LinearLayoutManager(requireContext())

        viajeViewModel = ViewModelProvider(this)[ViajeViewModel::class.java]

        viajeViewModel.getAllData.observe(viewLifecycleOwner) { viajes ->
            viajeAdapter.setData(viajes)
        }

        return binding.root


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
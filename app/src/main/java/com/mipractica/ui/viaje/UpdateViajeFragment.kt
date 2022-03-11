package com.mipractica.ui.viaje

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mipractica.R
import com.mipractica.databinding.FragmentAddViajeBinding
import com.mipractica.databinding.FragmentUpdateViajeBinding
import com.mipractica.model.Viaje
import com.mipractica.viewmodel.ViajeViewModel


class UpdateViajeFragment : Fragment() {


    private lateinit var viajeViewModel: ViajeViewModel
    private var _binding: FragmentUpdateViajeBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<UpdateViajeFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viajeViewModel = ViewModelProvider(this)[ViajeViewModel::class.java]
        _binding = FragmentUpdateViajeBinding.inflate(inflater, container, false)

        binding.etNombre.setText(args.viaje.nombre)
        binding.etLugar.setText(args.viaje.lugar)
        binding.etPoblacion.text = Editable.Factory.getInstance().newEditable(args.viaje.poblacion.toString())
        binding.etPrecio.text = Editable.Factory.getInstance().newEditable(args.viaje.precio.toString())

        binding.btActualizar.setOnClickListener {
            updateViaje()
        }

        setHasOptionsMenu(true)
       return  binding.root
    }


    private fun updateViaje() {
        val nombre = binding.etNombre.text.toString()
        if (nombre.isNotEmpty()) {
            val lugar = binding.etLugar.text.toString()
            val poblacion = binding.etPoblacion.text.toString().toDouble()
            val deuda = binding.etPrecio.text.toString().toDouble()

            val viaje = Viaje(args.viaje.id, nombre, lugar, poblacion, deuda)
            viajeViewModel.updateViaje(viaje)
        } else {
            Toast.makeText(requireContext(), getString(R.string.msg_actualizado), Toast.LENGTH_SHORT)
                .show()
        }
        findNavController().navigate(R.id.action_updateViajeFragment_to_nav_viaje)
    }




    private fun deleteViaje() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton(getString(R.string.si)) { _, _ ->
            viajeViewModel.deleteViaje(args.viaje)
            findNavController().navigate(R.id.action_updateViajeFragment_to_nav_viaje)
        }

        builder.setNegativeButton(getString(R.string.no)) {_,_ ->}
        builder.setTitle(R.string.menu_delete)
        builder.setMessage(getString(R.string.msg_seguro) + " a " + "${args.viaje.nombre}?")
        builder.create().show()
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete){
            deleteViaje()
        }
        return super.onOptionsItemSelected(item)
    }



}
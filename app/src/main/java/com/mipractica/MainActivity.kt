package com.mipractica

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mipractica.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FirebaseApp.initializeApp(this)
        auth = Firebase.auth

        binding.btLogin.setOnClickListener{
            ejecutaLogin()
        }

        binding.btRegister.setOnClickListener{
            ejecutaRegister()
        }
    }



    private fun ejecutaRegister() {
        val correo = binding.etCorreo.text.toString()
        val clave = binding.etClave.text.toString()

        auth.createUserWithEmailAndPassword(correo, clave)
            .addOnCompleteListener(this){ task ->
                if(task.isSuccessful){
                    Log.d("Creando usuario", "Usuario registrado")
                    val usuario = auth.currentUser
                    actualiza(usuario)
                } else {
                    Log.d("Creando usuario", "No se ha podido registrar")
                    Toast.makeText(baseContext, "Falló", Toast.LENGTH_LONG).show()
                    actualiza(null)
                }
            }
    }

    private fun actualiza(usuario: FirebaseUser?) {
        if(usuario != null){
            val intent = Intent(this, Central::class.java)
            startActivity(intent)
        }
    }


    public override fun onStart() {
        super.onStart()
        val usuario = auth.currentUser
        actualiza(usuario)
    }


    private fun ejecutaLogin() {
        val correo = binding.etCorreo.text.toString()
        val clave = binding.etClave.text.toString()

        auth.signInWithEmailAndPassword(correo, clave)
            .addOnCompleteListener(this){ task ->
                if(task.isSuccessful){
                    Log.d("Iniciando sesión", "Sesión iniciada")
                    val usuario = auth.currentUser
                    actualiza(usuario)
                } else {
                    Log.d("Iniciando sesión", "No se ha podido iniciar la sesión")
                    Toast.makeText(baseContext, "Falló", Toast.LENGTH_LONG).show()
                    actualiza(null)
                }
            }
    }
}
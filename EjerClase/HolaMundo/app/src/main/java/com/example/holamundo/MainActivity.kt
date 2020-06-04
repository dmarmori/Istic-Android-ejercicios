package com.example.holamundo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lblNombre.text="Comenzando"
        btnCambiarNombre.setOnClickListener{
            lblNombre.text =  txtNombre.text
        }

       btnCambiarNombre.setOnClickListener{
           lblNombre.text = "Click para cambiar nombre"
            val texto = txtNombre.text
            lblNombre.text = texto
        }


    }


}

package com.example.a1erparcial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_contador.*

class Contador : AppCompatActivity() {

    var contador:Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contador)

        val botonSumar = findViewById<Button>(R.id.btnSumar) //Declaracion para boton ingresar
        botonSumar.setOnClickListener {
            contador = contador +1
            lblContador.text = contador.toString()
        }

        val botonRestar = findViewById<Button>(R.id.btnRestar) //Declaracion para boton ingresar
        botonRestar.setOnClickListener {
            contador = contador -1
            lblContador.text = contador.toString()
        }

        val botonContar = findViewById<Button>(R.id.btnReserContador) //Declaracion para boton ingresar
        botonContar.setOnClickListener {
            contador = 0
            lblContador.text = ""
        }

        val botonAyuda = findViewById<TextView>(R.id.lblayuda) //Declaracion para boton ingresar
        botonAyuda.setOnClickListener {
            val ayudaIntent= Intent(this, ComoJugarContador::class.java)
            startActivity(ayudaIntent)
        }

        val botonDatosCont = findViewById<ImageView>(R.id.imgGuardarCont) //Declaracion para boton ingresar
        botonDatosCont.setOnClickListener {
            val guardarDatosIntent= Intent(this, DatosContador::class.java)
            startActivity(guardarDatosIntent)
        }

    }
}

package com.example.a1erparcial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_contador.*

class Contador : AppCompatActivity() {

    var contador:Int=0

    fun ttoas(mensaje:String){//Funcion para mensaje toast

        var toast = Toast.makeText(this@Contador,"$mensaje", Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER,0,0)
        toast.show()
    }

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

        val botonReset = findViewById<Button>(R.id.btnReserContador) //Declaracion para boton ingresar
        botonReset.setOnClickListener {
            contador = 0
            lblContador.text = ""
        }

        val botonAyuda = findViewById<TextView>(R.id.lblayuda) //Declaracion para boton ingresar
        botonAyuda.setOnClickListener {
            val ayudaIntent= Intent(this, ComoJugarContador::class.java)
            startActivity(ayudaIntent)
        }

        val botonDatosCont = findViewById<ImageView>(R.id.btnGuardarDatosContador) //Declaracion para boton ingresar
        botonDatosCont.setOnClickListener {
            if (lblContador.text == "" || lblContador.text.toString().toInt() == 0)
            {
                this.ttoas("Contador Vacio ó igual a 0")
            }else {
                val datoContado = lblContador.text
                val guardarDatosContIntent = Intent(this, DatosContador::class.java)
                guardarDatosContIntent.putExtra("datoContado", "$datoContado")
                startActivity(guardarDatosContIntent)
            }
        }

    }
}

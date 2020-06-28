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
import kotlinx.android.synthetic.main.activity_contador.compartirButton
import kotlinx.android.synthetic.main.activity_numero_secreto.*

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
                claseFunciones.ttoas("Contador Vacio ó igual a 0",this)
            }else {
                val datoContado = lblContador.text
                val guardarDatosContIntent = Intent(this, DatosContador::class.java)
                guardarDatosContIntent.putExtra("datoContado", "$datoContado")
                startActivity(guardarDatosContIntent)
            }
        }

        val botonIrMenu = findViewById<ImageView>(R.id.imgMenu) //Declaracion para boton ingresar
        botonIrMenu.setOnClickListener {
            val irMenuIntent = Intent(this, Menu::class.java)
            startActivity(irMenuIntent)
            finish()
        }

        val botonCompartir = findViewById<Button>(R.id.compartirButton) //Declaracion para boton Compartir
        //Compartir
        compartirButton.setOnClickListener{
            if (lblContador.text == "" || lblContador.text.toString().toInt() == 0)
            {
                claseFunciones.ttoas("Contador Vacio ó igual a 0",this)
            }else {
                compartir()
            }
        }
    }

    //Funcion para compratir
    private fun compartir(){
        val datoContado = lblContador.text
        val intent = Intent()
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, "Elementos contados: $datoContado \nMi App Dionel Marmori")
        intent.putExtra(Intent.EXTRA_SUBJECT, "Mail enviado desde mi app")
        intent.action = Intent.ACTION_SEND
        val chooseIntent = Intent.createChooser(intent, "Elija una opcion")
        startActivity(chooseIntent)
    }
}

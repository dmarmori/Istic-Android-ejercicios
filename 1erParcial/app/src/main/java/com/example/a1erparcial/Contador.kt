package com.example.a1erparcial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_contador.*

class Contador : AppCompatActivity() {

    var contador:Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contador)

        btnSumar.setOnClickListener {
            contador = contador +1
            lblContador.text = contador.toString()
        }

        btnRestar.setOnClickListener {
            contador = contador -1
            lblContador.text = contador.toString()
        }

        btnReserContador.setOnClickListener {
            contador = 0
            lblContador.text = ""
        }

        lblayuda.setOnClickListener {
            val ayudaIntent= Intent(this, ComoJugarContador::class.java)
            startActivity(ayudaIntent)
        }

    }
}

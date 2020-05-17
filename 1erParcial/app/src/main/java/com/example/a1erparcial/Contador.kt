package com.example.a1erparcial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_contador.*

class Contador : AppCompatActivity() {

    var contador:Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contador)

        btnContar.setOnClickListener {
            contador = contador +1
            lblContador.text = contador.toString()
        }

        btnReserContador.setOnClickListener {
            contador = 0
            lblContador.text = ""
        }

    }
}

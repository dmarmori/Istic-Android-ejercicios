package com.example.a1erparcial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_datos_contador.*

class DatosContador : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datos_contador)

        val datoContado=intent.getStringExtra("datoContado");
        lblDatoContado.text = datoContado


    }
}

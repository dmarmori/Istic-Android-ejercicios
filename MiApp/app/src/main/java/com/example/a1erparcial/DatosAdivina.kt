package com.example.a1erparcial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast

class DatosAdivina : AppCompatActivity() {

    fun ttoas(mensaje:String){//Funcion para mensaje toast

        var toast = Toast.makeText(this@DatosAdivina,"$mensaje", Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER,0,0)
        toast.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datos_adivina)
    }
}

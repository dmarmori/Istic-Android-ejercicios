package com.example.juegonumazar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val numeroSecreto=1//(Math.random() * 100).toInt()

    var vidas:Int = 5
    var intentos:Int = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        txtVidas.text = "Vidas restantes: $vidas"
        txtIntentos.text = "Numero de intentos: $intentos"

        btnAyuda.setOnClickListener{

            if(numeroSecreto <= 50)
            {
                Toast.makeText(this,"Numero Menor a 51 ", Toast.LENGTH_LONG).show()
            }else
            {
                Toast.makeText(this,"Numero mayor a 50 ", Toast.LENGTH_LONG).show()
            }
        }

        btnMeRindo.setOnClickListener{
           Toast.makeText(this,"El numero es: $numeroSecreto" , Toast.LENGTH_LONG).show()
        }


        btnAdivinar.setOnClickListener{

         if (vidas > 1) {
             if (numeroSecreto.toString() == txtIngNum.text.toString()) {
                 Toast.makeText(this, "Crak ", Toast.LENGTH_LONG).show()
                 vidas = vidas - 1
                 intentos = intentos + 1
                 txtVidas.text = "Vidas restantes: " + vidas
                 txtIntentos.text = "Numero de intentos: " + intentos
                 btnAdivinar.isEnabled = false
                 btnAyuda.isEnabled = false
                 txtGanaste.visibility = View.VISIBLE

             } else {
                 Toast.makeText(this, "No es el numero ", Toast.LENGTH_LONG).show()
                 vidas = vidas - 1
                 intentos = intentos + 1
                 txtVidas.text = "Vidas restantes: " + vidas
                 txtIntentos.text = "Numero de intentos: " + intentos
             }
         }else
         {
             Toast.makeText(this, "PERDISTE JAJAJAJAJAJA", Toast.LENGTH_LONG).show()
             vidas = vidas - 1
             intentos = intentos + 1
             txtVidas.text = "Vidas restantes: " + vidas
             txtIntentos.text = "Numero de intentos: " + intentos
             btnAdivinar.isEnabled = false
             btnAyuda.isEnabled = false
         }

        }

    }
}

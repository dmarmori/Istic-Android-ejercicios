package com.example.juegonumazar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val numeroSecreto=(Math.random() * 100).toInt()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
            if(numeroSecreto.toString() == txtIngNum.text.toString()){
                Toast.makeText(this,"Crak ", Toast.LENGTH_LONG).show()

            }else
            {
                Toast.makeText(this,"No es el numero ", Toast.LENGTH_LONG).show()
            }
        }

    }
}

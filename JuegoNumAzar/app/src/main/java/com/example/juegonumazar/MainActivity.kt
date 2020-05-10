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


        btnAdivinar.setOnClickListener{
            lblNombre.text = numeroSecreto.toString()

            if(numeroSecreto.toString() == txtIngNum.text.toString()){
                Toast.makeText(this,"Crak ", Toast.LENGTH_LONG).show()

            }else
            {
                Toast.makeText(this,"No es el numero ", Toast.LENGTH_LONG).show()
            }

        }

    }
}

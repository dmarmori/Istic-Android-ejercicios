package com.example.navegacionistic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class QuienSoy : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quien_soy)
        val botonUno=findViewById<Button>(R.id.btnInicio)
        val botonDos=findViewById<Button>(R.id.btnJugar)

        botonUno.setOnClickListener()
        {
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        botonDos.setOnClickListener()
        {
            val intent= Intent(this,JuegoAdivina::class.java)
            intent.putExtra("usuario","octavio")
            startActivity(intent)
        }
    }
}

package com.example.navegacionistic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val botonUno=findViewById<Button>(R.id.btnQuienSoy)
        botonUno.setOnClickListener()
        {
            val intent=Intent(this,QuienSoy::class.java)
            startActivity(intent)
        }

    }
}

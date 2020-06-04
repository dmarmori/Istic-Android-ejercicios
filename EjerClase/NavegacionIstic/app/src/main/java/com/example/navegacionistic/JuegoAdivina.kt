package com.example.navegacionistic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class JuegoAdivina : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_juego_adivina)
        val usuario=intent.getStringExtra("usuario");
        Toast.makeText(this,usuario,Toast.LENGTH_SHORT).show()
    }
}

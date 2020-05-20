package com.example.a1erparcial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.view.Gravity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        //Thread.sleep(1000)
        setTheme(R.style.AppTheme)//Tema para splashscreem

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        txtRegistroLogin.setOnClickListener{
            val registoIntent = Intent(this, Registro::class.java)
            startActivity(registoIntent)
        }

        btnIngresar.setOnClickListener{
            val menuIntent = Intent(this, Menu::class.java)
            startActivity(menuIntent)
        }
    }
}

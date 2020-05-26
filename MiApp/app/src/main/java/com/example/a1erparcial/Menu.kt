package com.example.a1erparcial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_menu.*

class Menu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val usrlogin=intent.getStringExtra("usuarioLogueado");
        val toast = Toast.makeText(this@Menu,"Bienvenido $usrlogin", Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER,0,0)
        toast.show()

        btnContador.setOnClickListener {
            val ContadorIntent = Intent(this, Contador::class.java)
            startActivity(ContadorIntent)
        }

        btnNumSec.setOnClickListener {
            val NumeSecretIntent = Intent(this, NumeroSecreto::class.java)
            startActivity(NumeSecretIntent)
        }

        lblLegajo.setOnClickListener {
        val toast = Toast.makeText(this@Menu,"Numero de Legajo", Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER,0,0)
        toast.show()
        }
    }
}

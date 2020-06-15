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
        if(usrlogin == null)//Valido si el dato viene null
        {
            claseFunciones.ttoas("Bienvenido",this)
        }else {
            claseFunciones.ttoas("Bienvenido $usrlogin",this)
        }

        btnContador.setOnClickListener {
            val ContadorIntent = Intent(this, Contador::class.java)
            startActivity(ContadorIntent)
        }

        btnNumSec.setOnClickListener {
            val NumeSecretIntent = Intent(this, NumeroSecreto::class.java)
            startActivity(NumeSecretIntent)
        }

        lblLegajo.setOnClickListener {
            claseFunciones.ttoas("Numero de Legajo",this)
        }
    }
}

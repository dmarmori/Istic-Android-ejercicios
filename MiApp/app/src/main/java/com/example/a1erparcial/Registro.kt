package com.example.a1erparcial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_numero_secreto.*
import kotlinx.android.synthetic.main.activity_registro.*

class Registro : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        btnRegistrarme.setOnClickListener{

            val toast = Toast.makeText(this@Registro,"Registro exitoso!!!", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.CENTER,0,0)
            toast.show()

            val LoginIntent = Intent(this, MainActivity::class.java)
            startActivity(LoginIntent)
        }

    }
}

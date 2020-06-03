package com.example.a1erparcial

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_numero_secreto.*
import kotlinx.android.synthetic.main.activity_registro.*
import java.io.IOException
import java.io.OutputStreamWriter

class Registro : AppCompatActivity() {

    fun ttoas(mensaje:String){//Funcion para mensaje toast

        var toast = Toast.makeText(this@Registro,"$mensaje", Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER,0,0)
        toast.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        btnRegistrarme.setOnClickListener{

        registrarUsuario()

            //val LoginIntent = Intent(this, MainActivity::class.java)
            //startActivity(LoginIntent)
        }

    }
    private fun registrarUsuario()
    {
        try {
            if (txtRegistroUsuario.text.toString() == "" || txtMailUsuario.text.toString() == "" ||
                txtRegistroClave.text.toString() == "" || txtRegistroRepClave.text.toString() == "")
            {
                this.ttoas("Todos los campos son obligatorios")
            }else {
                val archivoRegistro = OutputStreamWriter(openFileOutput("registro.txt", Activity.MODE_APPEND))
                archivoRegistro.write(
                    txtRegistroUsuario.text.toString() + "=>" + txtMailUsuario.text.toString() + "=>" +
                            txtRegistroClave.text.toString() + "=>" + txtRegistroRepClave.text.toString() + "\n"
                )
                archivoRegistro.flush()
                archivoRegistro.close()
                this.ttoas("Registro exitoso!!!")
            }
        } catch (e: IOException) {
            this.ttoas("Error al registrar usuario")
        }

    }
}


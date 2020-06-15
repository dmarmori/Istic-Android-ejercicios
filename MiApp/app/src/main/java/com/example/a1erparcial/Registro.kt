package com.example.a1erparcial

import android.app.Activity
import android.os.Bundle
import android.util.Patterns
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_registro.*
import java.io.IOException
import java.io.OutputStreamWriter
import java.util.regex.Pattern


class Registro : AppCompatActivity() {


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
                if(registroVacio()==false)
            {
                claseFunciones.ttoas("Todos los campos son obligatorios",this)
            }else
                {
                    if (!validarEmail(txtMailUsuario.text.toString()))
                    {
                        claseFunciones.ttoas("Email no válido",this)
                    }else
                    {
                        if (chequeaClave()==false)
                        {
                            claseFunciones.ttoas("Las claves deben coincidir",this)
                        }else
                        {
                            val archivoRegistro = OutputStreamWriter(openFileOutput("registro.txt", Activity.MODE_APPEND))
                            archivoRegistro.write(
                                txtRegistroUsuario.text.toString() + "=>" + txtMailUsuario.text.toString() + "=>" +
                                        txtRegistroClave.text.toString() + "=>" + txtRegistroRepClave.text.toString() + "\n"
                            )

                            archivoRegistro.flush()
                            archivoRegistro.close()
                            claseFunciones.ttoas("Registro exitoso!!!",this)
                        }
                    }
                }


        } catch (e: IOException) {
            claseFunciones.ttoas("Error al registrar usuario",this)
        }

    }

    private fun chequeaClave(): Boolean
    {
        var check:Boolean = false
            if(txtRegistroClave.text.toString() ==  txtRegistroRepClave.text.toString())
            {
                check = true
            }
        return check
    }

    private fun registroVacio(): Boolean
    {
        var check:Boolean = true
            if (txtRegistroUsuario.text.toString() == "" || txtMailUsuario.text.toString() == "" ||
                txtRegistroClave.text.toString() == "" || txtRegistroRepClave.text.toString() == "")
            {
                check = false
            }
        return check
    }

    private fun validarEmail(email: String): Boolean {
        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

}


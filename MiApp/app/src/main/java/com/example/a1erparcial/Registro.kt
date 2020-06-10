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
                if(registroVacio()==false)
            {
                this.ttoas("Todos los campos son obligatorios")
            }else
                {
                    if (!validarEmail(txtMailUsuario.text.toString()))
                    {
                        this.ttoas("Email no válido")
                    }else
                    {
                        if (chequeaClave()==false)
                        {
                            this.ttoas("Las claves deben coincidir")
                        }else
                        {
                            val archivoRegistro = OutputStreamWriter(openFileOutput("registro.txt", Activity.MODE_APPEND))
                            archivoRegistro.write(
                                txtRegistroUsuario.text.toString() + "=>" + txtMailUsuario.text.toString() + "=>" +
                                        txtRegistroClave.text.toString() + "=>" + txtRegistroRepClave.text.toString() + "\n"
                            )

                            archivoRegistro.flush()
                            archivoRegistro.close()
                            this.ttoas("Registro exitoso!!!")
                        }
                    }
                }


        } catch (e: IOException) {
            this.ttoas("Error al registrar usuario")
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


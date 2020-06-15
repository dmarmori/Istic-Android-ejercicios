package com.example.a1erparcial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.view.Gravity
import android.widget.Button
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_registro.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        //Thread.sleep(1000)
        setTheme(R.style.AppTheme)//Tema para splashscreem

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //GoogleAnalytic Event
        val analytics : FirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("Message","Integracion de Firebases completa")
        analytics.logEvent("InitScreen",bundle)


        txtRegistroLogin.setOnClickListener{
            val registoIntent = Intent(this, Registro::class.java)
            startActivity(registoIntent)
        }

        val botonIngresar = findViewById<Button>(R.id.btnIngresar) //Declaracion para boton ingresar
        botonIngresar.setOnClickListener{//Boton ingresar
            loginArchivo()

        }
    }

    private fun loginArchivo()
    {
        if (txtUsuarioLogin.text.toString() == "" || txtClaveLogin.text.toString() == "")
        {
           claseFunciones.ttoas("Ingrese usuario y clave",this)
        }else {
            if (fileList().contains("registro.txt")) {
                try {
                    var existeUsuario : Boolean = false

                    val archivoUsuarios = InputStreamReader(openFileInput("registro.txt"))
                    val br = BufferedReader(archivoUsuarios)
                    var linea = br.readLine()
                    while (linea != null) {
                        val arrayDatos = linea.split("=>")
                        if (arrayDatos[0] == txtUsuarioLogin.text.toString() && arrayDatos[2] == txtClaveLogin.text.toString()) {
                            existeUsuario = true
                            val usrlogin = txtUsuarioLogin.text
                            val menuIntent = Intent(this, Menu::class.java)
                            menuIntent.putExtra("usuarioLogueado", "$usrlogin")
                            startActivity(menuIntent)
                            break
                        }
                        linea = br.readLine()

                    }
                    if (existeUsuario == false)
                    {
                        claseFunciones.ttoas("Usuario inexistente",this)
                    }
                    br.close()
                    archivoUsuarios.close()

                } catch (e: IOException) {
                    claseFunciones.ttoas("Error al ingresar",this)
                }
            }
        }

    }
}

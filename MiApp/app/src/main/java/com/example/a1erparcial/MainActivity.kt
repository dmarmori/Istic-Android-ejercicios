package com.example.a1erparcial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.view.Gravity
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    fun ttoas(mensaje:String){//Funcion para mensaje toast

        var toast = Toast.makeText(this@MainActivity,"$mensaje", Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER,0,0)
        toast.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        //Thread.sleep(1000)
        setTheme(R.style.AppTheme)//Tema para splashscreem

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


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
        if (fileList().contains("registro.txt")) {
            try {
                val archivoUsuarios = InputStreamReader(openFileInput("registro.txt"))
                val br = BufferedReader(archivoUsuarios)
                var linea = br.readLine()
                while (linea != null) {
                 //pepito=>1234 esto tiene linea
                    val arrayDatos=linea.split("=>")
                    if(arrayDatos[0]==txtUsuarioLogin.text.toString() && arrayDatos[1]==txtClaveLogin.text.toString())
                    {
                        break
                    }
                    linea = br.readLine()
                }
                br.close()
                archivoUsuarios.close()
                val usrlogin = txtUsuarioLogin.text
                val menuIntent = Intent(this, Menu::class.java)
                menuIntent.putExtra("usuarioLogueado","$usrlogin")
                startActivity(menuIntent)

            } catch (e: IOException) {
                this.ttoas("Error al ingresar")
            }
        }

    }
}

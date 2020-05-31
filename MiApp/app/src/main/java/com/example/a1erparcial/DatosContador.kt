package com.example.a1erparcial

import android.app.Activity
import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_contador.*
import kotlinx.android.synthetic.main.activity_datos_contador.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class DatosContador : AppCompatActivity() {

    fun ttoas(mensaje:String){//Funcion para mensaje toast

        var toast = Toast.makeText(this@DatosContador,"$mensaje", Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER,0,0)
        toast.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datos_contador)

        val txtObservacion=findViewById<TextView>(R.id.txtObservacion)

        val guardarDato = findViewById<Button>(R.id.btnGuardarDatoCont) //Declaracion para boton ingresar
        val eliminarListado = findViewById<Button>(R.id.btnEliminar)

        val datoContado=intent.getStringExtra("datoContado");
        lblDatoContado.text = datoContado


        guardarDato.setOnClickListener {
           try {
               val archivoContador = OutputStreamWriter(openFileOutput("datosContador.txt", Activity.MODE_APPEND))
               archivoContador.write(txtObservacion.text.toString() + ": " + lblDatoContado.text.toString() + "\n")
               archivoContador.flush()
               archivoContador.close()
           }catch (e: IOException) {
               this.ttoas("Error al guardar datos")
           }
            this.ttoas("Guardando...")
            CargarListadoContador()
            guardarDato.isEnabled = false
        }

        eliminarListado.setOnClickListener {
            try {
                val btnBorrarDialogBuilder = AlertDialog.Builder(this@DatosContador) //Dialogo para eliminar listado
                btnBorrarDialogBuilder.setTitle("Eliminar")
                btnBorrarDialogBuilder.setIcon(R.mipmap.ic_launcher)
                btnBorrarDialogBuilder.setMessage("¿Esta seguro de eliminar el archivo del listado?")
                btnBorrarDialogBuilder.setCancelable(false)
                btnBorrarDialogBuilder.setPositiveButton("Si") {_,_->
                    this.ttoas("Eliminando...")
                    CargarListadoContador()
                    finish()
                }
                btnBorrarDialogBuilder.setNegativeButton("No") { _, _ ->
                    Toast.makeText(this, "Indico No...",Toast.LENGTH_SHORT).show()
                }
                btnBorrarDialogBuilder.setNeutralButton("Cancel") { _, _ ->
                    Toast.makeText(this, "Accion Cancelada..",Toast.LENGTH_SHORT).show()
                }
                val btnBorrarDialog = btnBorrarDialogBuilder.create()
                btnBorrarDialog.show()

                EliminarListado()
            }catch (e: IOException) {
                this.ttoas("Error al eliminar listado")
            }
        }


    }

    private fun EliminarListado()
    {
        deleteFile("datosContador.txt")
    }

    private fun CargarListadoContador()
    {
        if (fileList().contains("datosContador.txt")) {
            try {
                val archivoContador = InputStreamReader(openFileInput("datosContador.txt"))
                val br = BufferedReader(archivoContador)
                var linea = br.readLine()
                val listado = StringBuilder()//solo para mostrarlo
                while (linea != null) {
                    listado.append(linea + " \n ")
                    linea = br.readLine()
                }
                br.close()
                archivoContador.close()
                this.txtMultilineContador.setText(listado)
            } catch (e: IOException) {
                this.ttoas("Error al cargar los datos")
            }
        }

    }

    override fun onStart() {//Para cargar los datos cuando inicia la pantalla
        super.onStart()
        CargarListadoContador()
    }

}

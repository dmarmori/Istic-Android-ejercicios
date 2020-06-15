package com.example.a1erparcial

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_datos_contador.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class DatosContador : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datos_contador)

        val txtObservacion=findViewById<TextView>(R.id.txtObservacion)

        val guardarDatoCont = findViewById<Button>(R.id.btnGuardarDatoCont) //Declaracion para boton ingresar
        val eliminarListadoCont = findViewById<Button>(R.id.btnEliminarCont)
        val SalirDatosCont = findViewById<ImageView>(R.id.imgSalirCont)

        val datoContado=intent.getStringExtra("datoContado");
        lblDatoCont.text = datoContado


        guardarDatoCont.setOnClickListener {
            fguardarDatosCont()
            guardarDatoCont.isEnabled = false
        }

        eliminarListadoCont.setOnClickListener {
           fEliminarListado()
           fCargarListadoContador()
        }

        SalirDatosCont.setOnClickListener {
            val irContIntent = Intent(this, Contador::class.java)
            startActivity(irContIntent)
            finish()
        }
    }
////////////////////////////////////////////////////////////////////////////Funiones Privadas
    private fun fguardarDatosCont()
    {
        try {
            val archivoContador = OutputStreamWriter(openFileOutput("datosContador.txt", Activity.MODE_APPEND))
            archivoContador.write(txtObservacion.text.toString() + ": " + lblDatoCont.text.toString() + "\n")
            archivoContador.flush()
            archivoContador.close()
        }catch (e: IOException) {
            claseFunciones.ttoas("Error al guardar datos",this)
        }
        claseFunciones.ttoas("Guardando...",this)
        fCargarListadoContador()
    }
///////////////////////////////////////////////////////////////////////////////////////
    private fun fEliminarListado()
    {
        try {
            val btnBorrarDialogBuilder = AlertDialog.Builder(this@DatosContador) //Dialogo para eliminar listado
            btnBorrarDialogBuilder.setTitle("Eliminar")
            btnBorrarDialogBuilder.setIcon(R.mipmap.ic_launcher)
            btnBorrarDialogBuilder.setMessage("¿Esta seguro de eliminar el archivo del listado?")
            btnBorrarDialogBuilder.setCancelable(false)
            btnBorrarDialogBuilder.setPositiveButton("Si") {_,_->
                claseFunciones.ttoas("Eliminando...",this)
                fCargarListadoContador()
                deleteFile("datosContador.txt")
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

        }catch (e: IOException) {
            claseFunciones.ttoas("Error al eliminar listado",this)
        }
    }
///////////////////////////////////////////////////////////////////////////////
    private fun fCargarListadoContador()
    {
        if (fileList().contains("datosContador.txt")) {
            try {
                val archivoContador = InputStreamReader(openFileInput("datosContador.txt"))
                val br = BufferedReader(archivoContador)
                var linea = br.readLine()
                val listado = StringBuilder()//solo para mostrarlo
                while (linea != null) {
                    listado.append(linea + "\n")
                    linea = br.readLine()
                }
                br.close()
                archivoContador.close()
                this.txtMultilineCont.setText(listado)
            } catch (e: IOException) {
                claseFunciones.ttoas("Error al cargar los datos",this)
            }
        }

    }

    override fun onStart() {//Para cargar los datos cuando inicia la pantalla
        super.onStart()
        fCargarListadoContador()
    }

}

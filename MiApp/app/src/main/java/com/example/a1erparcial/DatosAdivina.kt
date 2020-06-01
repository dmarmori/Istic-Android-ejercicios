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
import kotlinx.android.synthetic.main.activity_datos_adivina.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class DatosAdivina : AppCompatActivity() {

    fun ttoas(mensaje:String){//Funcion para mensaje toast

        var toast = Toast.makeText(this@DatosAdivina,"$mensaje", Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER,0,0)
        toast.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datos_adivina)

        val txtNombreAdivina=findViewById<TextView>(R.id.txtNombreAdivina)

        val guardarDatoAdivina = findViewById<Button>(R.id.btnGuardarDatoAdivina) //Declaracion para boton ingresar
        val eliminarListadoAdivina = findViewById<Button>(R.id.btnEliminarAdivina)

        val SalirDatosAdivina = findViewById<ImageView>(R.id.imgSalirAdivina)

        val datoAdivina=intent.getStringExtra("datosAdivina");
        lblDatoAdivina.text = datoAdivina

        guardarDatoAdivina.setOnClickListener {
            fGuardarDatosAdivina()
            guardarDatoAdivina.isEnabled = false
        }

        eliminarListadoAdivina.setOnClickListener {
            fEliminarListado()
            fCargarListadoAdivina()

        }

        SalirDatosAdivina.setOnClickListener {
            val irAdivinaIntent = Intent(this, NumeroSecreto::class.java)
            startActivity(irAdivinaIntent)
            finish()
        }

    }
///////////////////////////////////////////Funiones Privadas
    private fun fGuardarDatosAdivina()
    {
        try {
            val archivoAdivina = OutputStreamWriter(openFileOutput("datosAdivina.txt", Activity.MODE_APPEND))
            archivoAdivina.write(txtNombreAdivina.text.toString() + ": " + lblDatoAdivina.text.toString() + " Intentos" + "\n")
            archivoAdivina.flush()
            archivoAdivina.close()
        }catch (e: IOException) {
            this.ttoas("Error al guardar datos")
        }
        this.ttoas("Guardando...")
        fCargarListadoAdivina()

    }
/////////////////////////////////////////////////////////////////////////
    private fun fEliminarListado()//Funcion borrar listado
    {
        try {
            val btnBorrarDialogBuilder = AlertDialog.Builder(this@DatosAdivina) //Dialogo para eliminar listado
            btnBorrarDialogBuilder.setTitle("Eliminar")
            btnBorrarDialogBuilder.setIcon(R.mipmap.ic_launcher)
            btnBorrarDialogBuilder.setMessage("¿Esta seguro de eliminar el archivo del listado?")
            btnBorrarDialogBuilder.setCancelable(false)
            btnBorrarDialogBuilder.setPositiveButton("Si") {_,_->
                this.ttoas("Eliminando...")
                deleteFile("datosAdivina.txt")
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
            this.ttoas("Error al eliminar listado")
        }
    }
///////////////////////////////////////////////////////////////////////////
    private fun fCargarListadoAdivina()
    {
        if (fileList().contains("datosAdivina.txt")) {
            try {
                val archivoAdivina = InputStreamReader(openFileInput("datosAdivina.txt"))
                val br = BufferedReader(archivoAdivina)
                var linea = br.readLine()
                val listado = StringBuilder()//solo para mostrarlo
                while (linea != null) {
                    listado.append(linea + "\n")
                    linea = br.readLine()
                }
                br.close()
                archivoAdivina.close()
                this.txtMultilineAdivina.setText(listado)
            } catch (e: IOException) {
                this.ttoas("Error al cargar los datos")
            }
        }
    }
////////////////////////////////////////////////////////////////////////
    override fun onStart() {//Para cargar los datos cuando inicia la pantalla
        super.onStart()
        fCargarListadoAdivina()
    }


}

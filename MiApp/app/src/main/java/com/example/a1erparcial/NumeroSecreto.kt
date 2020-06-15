package com.example.a1erparcial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.view.Gravity
import android.widget.Button
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_numero_secreto.*
import java.io.IOException

class NumeroSecreto : AppCompatActivity() {

    val numeroSecreto=(Math.random() * 100).toInt()

    val validaNum = 100

    var vidas:Int = 5
    var intentos:Int = 0
    var numAyuda:Int = 3
    var ayuda:Int = 1
    var numeroMultiplicado:Int = 0


    fun pista(NumeroPista:Int)
    {
        if (numeroSecreto.toString() > txtIngNum.text.toString())
        {
            claseFunciones.ttoas("Dale para Arriba",this)
        }
        if (numeroSecreto.toString() < txtIngNum.text.toString())
        {
            claseFunciones.ttoas("Dale para abajo",this)
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_numero_secreto)

        txtVidas.text = "Vidas restantes: $vidas"
        txtIntentos.text = "Numero de intentos: $intentos"
        txtAyuda.text = "Ayudas disponibles: $numAyuda"

        val botonDatosAdivina = findViewById<ImageView>(R.id.btnGuardarDatosAdivina) //Declaracion para boton ingresar
        botonDatosAdivina.isEnabled = false //Declaro el boton guardar arriba y lo inicio deshabilitado

        ///////////////////////////////////////////////////////////////////////////////////////////////////

        btnAyuda.setOnClickListener{ //Boton de ayuda
            super.onRestart();
            when(ayuda)
            {
                1 ->
                    if (numeroSecreto <= 50)
                    {
                        claseFunciones.ttoas("Numero Menor a 51",this)
                    } else
                    {
                        claseFunciones.ttoas("Numero mayor a 50",this)
                    }

                2 ->
                    if (numeroSecreto %2 == 0)
                    {
                        claseFunciones.ttoas("El numero es PAR",this)
                    }else
                    {
                        claseFunciones.ttoas("El numero es IMPAR",this)
                    }
                3 ->
                {
                    numeroMultiplicado = (numeroSecreto * 2 * 2 * 2 * 2)/2
                    claseFunciones.ttoas("(Numero * 2 * 2 * 2 * 2)/2 = $numeroMultiplicado",this)
                }
                else ->
                {
                    claseFunciones.ttoas("(Numero * 2 * 2 * 2 * 2)/2 = $numeroMultiplicado",this)
                }

            }

            ayuda = ayuda + 1
            if(numAyuda > 0) {
                numAyuda = numAyuda - 1
                txtAyuda.text = "Ayudas disponibles: " + numAyuda
            }

        }

////////////////////////////////////////////////////////////////////////////////////////////////////

        btnMeRindo.setOnClickListener{ //Boton me rindo

            claseFunciones.ttoas("El numero era: $numeroSecreto",this)

            btnAdivinar.isEnabled = false
            btnAyuda.isEnabled = false
            txtIngNum.isEnabled = false
            txtResFinal.text = "EL QUE ABANDONA NO TIENE PREMIO"
            txtResFinal.visibility = View.VISIBLE

            txtResFinal.setTextColor(txtResFinal.getContext().getResources().getColor(R.color.colorPrimary))
        }

        //////////////////////////////////////////////////////////////////////////////////////////////

        btnAdivinar.setOnClickListener{//boton avidinar

           val NumInicial = txtIngNum.text.toString()

            if(NumInicial == "")
            {
                claseFunciones.ttoas("Debe ingresar un Numero",this)
            }else {

                val numero = txtIngNum.text.toString().toInt()
                if (numero <= 100) {

                    if (vidas > 1) {
                        if (numeroSecreto.toString() == txtIngNum.text.toString()) {
                            if (ayuda < 3) {
                                claseFunciones.ttoas("Ganaste Crack ",this)
                                txtResFinal.text = "GANASTE!! SEGURO SOS DE BOCA"
                                botonDatosAdivina.isEnabled = true
                            } else {
                                claseFunciones.ttoas("Ganaste con ayudin",this)
                                txtResFinal.text = "GANASTE CON AYUDIN COMO RIBER"
                                botonDatosAdivina.isEnabled = true
                            }
                            vidas = vidas - 1
                            intentos = intentos + 1
                            txtVidas.text = "Vidas restantes: " + vidas
                            txtIntentos.text = "Numero de intentos: " + intentos
                            btnAdivinar.isEnabled = false
                            btnAyuda.isEnabled = false
                            btnMeRindo.isEnabled = false
                            txtIngNum.isEnabled = false
                            txtResFinal.visibility = View.VISIBLE


                        } else {
                            pista(txtIngNum.text.toString().toInt())
                            //Toast.makeText(this, "No es el numero ", Toast.LENGTH_LONG).show()
                            vidas = vidas - 1
                            intentos = intentos + 1
                            txtVidas.text = "Vidas restantes: " + vidas
                            txtIntentos.text = "Numero de intentos: " + intentos
                        }
                    } else {
                        claseFunciones.ttoas("PERDISTE Intentalo otra vez",this)
                        vidas = vidas - 1
                        intentos = intentos + 1
                        txtVidas.text = "Vidas restantes: " + vidas
                        txtIntentos.text = "Numero de intentos: " + intentos
                        btnAdivinar.isEnabled = false
                        btnMeRindo.isEnabled = false
                        btnAyuda.isEnabled = false
                        txtIngNum.isEnabled = false
                        txtResFinal.text = "PERDISTE!!! SEGURO SOS DE RIBER"
                        txtResFinal.visibility = View.VISIBLE
                    }

                } else {
                    claseFunciones.ttoas("El nuemero debe ser MENOR A 100",this)
                }
            }

        }

        lblComoJugar.setOnClickListener{
            val ComoJugartIntent = Intent(this, ComoJugarNumSecret::class.java)
            startActivity(ComoJugartIntent)
        }


        botonDatosAdivina.setOnClickListener {
            try {
                val datosAdivina = intentos.toString()
                val guardarDatosAdivinaIntent = Intent(this, DatosAdivina::class.java)
                guardarDatosAdivinaIntent.putExtra("datosAdivina", "$datosAdivina")
                startActivity(guardarDatosAdivinaIntent)

            } catch (e: IOException) {
                claseFunciones.ttoas("Error intente nuevamente",this)
            }

        }

        val botonIrMenu = findViewById<ImageView>(R.id.imgMenu) //Declaracion para boton ingresar
        botonIrMenu.setOnClickListener {
            val irMenuIntent = Intent(this, Menu::class.java)
            startActivity(irMenuIntent)
            finish()
        }

        val jugarDeNuevo = findViewById<Button>(R.id.btnJuegoNuevo) //Declaracion para boton ingresar
        jugarDeNuevo.setOnClickListener {
            val pIntent = Intent(this, NumeroSecreto::class.java)
            startActivity(pIntent)
            finish()

        }


    }



}

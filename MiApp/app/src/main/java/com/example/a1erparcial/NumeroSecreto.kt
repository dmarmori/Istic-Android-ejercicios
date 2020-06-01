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

    val numeroSecreto=1//(Math.random() * 100).toInt()

    val validaNum = 100

    var vidas:Int = 5
    var intentos:Int = 0
    var numAyuda:Int = 3
    var ayuda:Int = 1
    var numeroMultiplicado:Int = 0

    fun ttoas(mensaje:String){//Funcion para mensaje toast

        var toast = Toast.makeText(this@NumeroSecreto,"$mensaje",Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER,0,0)
        toast.show()
    }

    fun pista(NumeroPista:Int)
    {
        if (numeroSecreto.toString() > txtIngNum.text.toString())
        {
            this.ttoas("Dale para Arriba")
        }
        if (numeroSecreto.toString() < txtIngNum.text.toString())
        {
            this.ttoas("Dale para abajo")
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
                        this.ttoas("Numero Menor a 51")
                    } else
                    {
                        this.ttoas("Numero mayor a 50")
                    }

                2 ->
                    if (numeroSecreto %2 == 0)
                    {
                        this.ttoas("El numero es PAR")
                    }else
                    {
                        this.ttoas("El numero es IMPAR")
                    }
                3 ->
                {
                    numeroMultiplicado = (numeroSecreto * 2 * 2 * 2 * 2)/2
                    this.ttoas("(Numero * 2 * 2 * 2 * 2)/2 = $numeroMultiplicado")
                }
                else ->
                {
                    this.ttoas("(Numero * 2 * 2 * 2 * 2)/2 = $numeroMultiplicado")
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

            //val toast = Toast.makeText(this@NumeroSecreto,"El numero era: $numeroSecreto",Toast.LENGTH_SHORT)
            //toast.setGravity(Gravity.CENTER,0,0)
            //toast.show()
            this.ttoas("El numero era: $numeroSecreto")

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
                this.ttoas("Debe ingresar un Numero")
            }else {

                val numero = txtIngNum.text.toString().toInt()
                if (numero <= 100) {

                    if (vidas > 1) {
                        if (numeroSecreto.toString() == txtIngNum.text.toString()) {
                            if (ayuda < 3) {
                                this.ttoas("Ganaste Crack ")
                                txtResFinal.text = "GANASTE!! SEGURO SOS DE BOCA"
                                botonDatosAdivina.isEnabled = true
                            } else {
                                this.ttoas("Ganaste con ayudin")
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
                        this.ttoas("PERDISTE Intentalo otra vez")
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
                    this.ttoas("El nuemero debe ser MENOR A 100")
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
                this.ttoas("Error intente nuevamente")
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

        }


    }



}

package com.example.a1erparcial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.view.Gravity
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_numero_secreto.*

class NumeroSecreto : AppCompatActivity() {

    val numeroSecreto=(Math.random() * 100).toInt()

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
                            } else {
                                this.ttoas("Ganaste con ayudin")
                                txtResFinal.text = "GANASTE CON AYUDIN COMO RIBER"
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


    }
}

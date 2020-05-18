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
                        Toast.makeText(this, "Numero Menor a 51 ", Toast.LENGTH_LONG).show()
                    } else
                    {
                        Toast.makeText(this, "Numero mayor a 50 ", Toast.LENGTH_LONG).show()
                    }

                2 ->
                    if (numeroSecreto %2 == 0)
                    {
                        Toast.makeText(this, "El numero es PAR", Toast.LENGTH_LONG).show()
                    }else
                    {
                        Toast.makeText(this, "El numero es IMPAR", Toast.LENGTH_LONG).show()
                    }
                3 ->
                {
                    numeroMultiplicado = (numeroSecreto * 2 * 2 * 2 * 2)/2
                    Toast.makeText(this, "(Numero * 2 * 2 * 2 * 2)/2 = $numeroMultiplicado", Toast.LENGTH_LONG).show()
                }
                else ->
                {
                    Toast.makeText(this, "(Numero * 2 * 2 * 2 * 2)/2 = $numeroMultiplicado", Toast.LENGTH_LONG).show()
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

            val toast = Toast.makeText(this@NumeroSecreto,"El numero era: $numeroSecreto",Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.CENTER,0,0)
            toast.show()

            btnAdivinar.isEnabled = false
            btnAyuda.isEnabled = false
            txtIngNum.isEnabled = false
            txtResFinal.text = "EL QUE ABANDONA NO TIENE PREMIO"
            txtResFinal.visibility = View.VISIBLE

            txtResFinal.setTextColor(txtResFinal.getContext().getResources().getColor(R.color.colorPrimary))
        }

        //////////////////////////////////////////////////////////////////////////////////////////////

        btnAdivinar.setOnClickListener{//boton avidinar
            val numero = txtIngNum.text.toString().toInt()
            if (numero <= 100 ) {

                if (vidas > 1) {
                    if (numeroSecreto.toString() == txtIngNum.text.toString()) {
                        if (ayuda < 3) {
                            Toast.makeText(this, "Ganaste Crack ", Toast.LENGTH_LONG).show()
                            txtResFinal.text = "GANASTE!! SEGURO SOS DE BOCA"
                        } else {
                            Toast.makeText(this, "Ganaste con ayudin ", Toast.LENGTH_LONG).show()
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
                        Toast.makeText(this, "No es el numero ", Toast.LENGTH_LONG).show()
                        vidas = vidas - 1
                        intentos = intentos + 1
                        txtVidas.text = "Vidas restantes: " + vidas
                        txtIntentos.text = "Numero de intentos: " + intentos
                    }
                } else {
                    Toast.makeText(this, "PERDISTE JAJAJAJAJAJA", Toast.LENGTH_LONG).show()
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
                    txtResFinal.setTextColor(
                        txtResFinal.getContext().getResources().getColor(R.color.colorPrimary)
                    )

                }
            }else
            {
                Toast.makeText(this, "El nuemero debe ser MENOR A 100", Toast.LENGTH_LONG).show()
            }

        }

        lblComoJugar.setOnClickListener{
            val ComoJugartIntent = Intent(this, ComoJugarNumSecret::class.java)
            startActivity(ComoJugartIntent)
        }


    }
}

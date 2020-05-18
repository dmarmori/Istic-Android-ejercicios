package com.example.a1erparcial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_como_jugar_num_secret.*

class ComoJugarNumSecret : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_como_jugar_num_secret)

        btnIrAContar.setOnClickListener {
            val jugarIntent= Intent(this, NumeroSecreto::class.java)
            startActivity(jugarIntent)
            finish()
        }
    }
}

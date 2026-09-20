package com.example.holayo

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SegundaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_segunda)
        val nombre = intent.getStringExtra("nombre") ?: "misterioso visitante"
        findViewById<TextView>(R.id.tvBienvenida).text =
            "Sala de experimentos de $nombre"
    }
}
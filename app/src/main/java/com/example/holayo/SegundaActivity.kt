package com.example.holayo

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SegundaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("VIDA", "Segunda → onCreate")
        setContentView(R.layout.activity_segunda)
        val nombre = intent.getStringExtra("nombre") ?: "misterioso visitante"
        findViewById<TextView>(R.id.tvBienvenida).text =
            "Sala de experimentos de $nombre"
    }
    override fun onStart() { super.onStart(); Log.d("VIDA", "Segunda → onStart") }
    override fun onResume() { super.onResume(); Log.d("VIDA", "Segunda → onResume")
    }
    override fun onPause() { super.onPause(); Log.d("VIDA", "Segunda → onPause") }
    override fun onStop() { super.onStop(); Log.d("VIDA", "Segunda → onStop") }
    override fun onDestroy() { super.onDestroy(); Log.d("VIDA", "Segunda → onDestroy") }
}
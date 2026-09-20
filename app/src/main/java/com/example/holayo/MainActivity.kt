package com.example.holayo
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
// Tu modelo de datos: una línea, y ya tiene igualdad, copia y representación.
data class Perfil(
    val nombre: String,
    val dato: String,
    val apodo: String?
// el ? declara: "puede no haber apodo" — y el compilador lo vigila
)
class MainActivity : AppCompatActivity() {
    private val perfil = Perfil(
        nombre = "Sofía Quiroz",
        dato = "Estoy cursando Aplicaciones Móviles",
        apodo = "Soff"
    )
    private var saludoFormal = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvSaludo = findViewById<TextView>(R.id.tvSaludo)
        val tvDato = findViewById<TextView>(R.id.tvDato)
        val btnSaludar = findViewById<Button>(R.id.btnSaludar)

        tvDato.text = perfil.dato

        fun actualizarSaludo() {
            tvSaludo.text = if (saludoFormal)
                "Hola, soy ${perfil.nombre}"
            else
                "¡Buenas! Acá ${perfil.apodo ?: perfil.nombre}"
        }

        actualizarSaludo() // estado inicial

        btnSaludar.setOnClickListener {
            saludoFormal = !saludoFormal
            actualizarSaludo()
        }
    }
}
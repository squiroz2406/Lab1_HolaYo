package com.example.holayo
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.view.View
// Tu modelo de datos: una línea, y ya tiene igualdad, copia y representación.
data class Perfil(
    val nombre: String,
    val dato: String,
    val apodo: String?,
    val comida: String?,
    val bebida: String?,
)
class MainActivity : AppCompatActivity() {
    private val perfil = Perfil(
        nombre = "Sofía Quiroz",
        dato = "Estoy cursando Aplicaciones Móviles",
        apodo = "Soff",
        comida = "Sushi",
        bebida = null
    )
    private var saludoFormal = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("VIDA", "Main → onCreate")
        setContentView(R.layout.activity_main)

        val tvSaludo = findViewById<TextView>(R.id.tvSaludo)
        val tvDato = findViewById<TextView>(R.id.tvDato)
        val tvComida = findViewById<TextView>(R.id.tvComida)
        val tvBebida = findViewById<TextView>(R.id.tvBebida)
        val btnSaludar = findViewById<Button>(R.id.btnSaludar)
        val btnIrSegunda = findViewById<Button>(R.id.btnIrSegunda)
        tvDato.text = perfil.dato


        btnIrSegunda.setOnClickListener {
            val intent = Intent(this, SegundaActivity::class.java)
            intent.putExtra("nombre", perfil.apodo ?: perfil.nombre)
            startActivity(intent)
        }

        fun actualizarSaludo() {
            tvSaludo.text = if (saludoFormal)
                "Hola, soy ${perfil.nombre}"
            else
                "¡Buenas! Acá ${perfil.apodo ?: perfil.nombre}"
        }

        fun mostrarDatosOpcionales() {
            if (!saludoFormal) { //comida aparece solo en saludo informal
                perfil.comida?.let { comida ->
                    tvComida.text = "Mi comida favorita es: $comida"
                    tvComida.visibility = View.VISIBLE
                } ?: run { tvComida.visibility = View.GONE }
            } else {
                tvComida.visibility = View.GONE
            }

            perfil.bebida?.let { bebida ->
                tvBebida.text = "Mi bebida favorita es: $bebida"
                tvBebida.visibility = View.VISIBLE
            } ?: run { tvBebida.visibility = View.GONE }
        }

        actualizarSaludo() // estado inicial
        mostrarDatosOpcionales() // muestra comida y bebida si no son null


        btnSaludar.setOnClickListener {
            saludoFormal = !saludoFormal
            actualizarSaludo()
            mostrarDatosOpcionales()
        }
        btnIrSegunda.setOnClickListener {
            val intent = Intent(this, SegundaActivity::class.java)
            intent.putExtra("nombre", perfil.apodo ?: perfil.nombre)
            startActivity(intent)
        }

    }
    override fun onStart() { super.onStart(); Log.d("VIDA", "Main → onStart") }
    override fun onResume() { super.onResume(); Log.d("VIDA", "Main → onResume")
    }
    override fun onPause() { super.onPause(); Log.d("VIDA", "Main → onPause") }
    override fun onStop() { super.onStop(); Log.d("VIDA", "Main → onStop") }
    //onRestart() se ejecuta solo cuando una Activity vuelve a primer plano después de haber pasado por onStop()
    //Se llama justo antes de onStart(), pero únicamente en el camino "stop → start"
    //orden completo: onCreate → onStart → onResume → onPause → onStop → onRestart → onStart → onResume
    //Si la Activity nunca llegó a onStop(), jamás vas a ver onRestart().
    override fun onRestart() { super.onRestart(); Log.d("VIDA", "Main → onRestart") } //agrego onRestart
    override fun onDestroy() { super.onDestroy(); Log.d("VIDA", "Main → onDestroy") }
}
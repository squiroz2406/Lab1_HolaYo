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

    companion object {
        private const val KEY_SALUDO_FORMAL = "saludoFormal"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("VIDA", "Main → onCreate")
        setContentView(R.layout.activity_main)

        saludoFormal = savedInstanceState?.getBoolean(KEY_SALUDO_FORMAL) ?: true

        val tvSaludo = findViewById<TextView>(R.id.tvSaludo)
        val tvDato = findViewById<TextView>(R.id.tvDato)
        val tvComida = findViewById<TextView>(R.id.tvComida)
        val tvBebida = findViewById<TextView>(R.id.tvBebida)
        val btnSaludar = findViewById<Button>(R.id.btnSaludar)
        val btnIrSegunda = findViewById<Button>(R.id.btnIrSegunda)
        val btnCompartir = findViewById<Button>(R.id.btnCompartir)

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

        btnCompartir.setOnClickListener {
            val texto = buildString {
                append("¡Hola! Soy ${perfil.nombre}")
                perfil.apodo?.let { append(" (alias $it)") }
                append(".\n")
                append("${perfil.dato}.\n")
                perfil.comida?.let { append("Mi comida favorita es $it.\n") }
                perfil.bebida?.let { append("Mi bebida favorita es $it.\n") }
            }

            val intentCompartir = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, texto)
            }

            startActivity(Intent.createChooser(intentCompartir, "Compartir perfil con..."))
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(KEY_SALUDO_FORMAL, saludoFormal)
        Log.d("VIDA", "Main → onSaveInstanceState (saludoFormal=$saludoFormal)")
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
package com.example.apicasa2

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.apicasa2.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private lateinit var binding: ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding // ViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddGato.setOnClickListener {
            startActivity(Intent(this, AddGatoActivity::class.java))
        }

        binding.btnViewGatos.setOnClickListener {
            startActivity(Intent(this, ListGatosActivity::class.java))
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val catBreeds = RetrofitInstance.api.getListadoRazas()

                catBreeds.forEach { cat ->
                    println("Raza: ${cat.name}, Origen: ${cat.origin}, Temperamento: ${cat.temperament}")

                    // Convertir a entidad y guardar en la base de datos
                    val gatoEntity = GatoEntity(
                        name = cat.name,
                        origin = cat.origin,
                        temperament = cat.temperament
                    )

                    AppApplication.gatoDatabase.gatoDao().insertOne(gatoEntity)
                }

                println("Datos guardados en la base de datos.")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

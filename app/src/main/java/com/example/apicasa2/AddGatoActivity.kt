package com.example.apicasa2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.apicasa2.AppApplication
import com.example.apicasa2.GatoEntity
import com.example.apicasa2.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddGatoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_gato)

        val etName = findViewById<EditText>(R.id.etName)
        val etOrigin = findViewById<EditText>(R.id.etOrigin)
        val etTemperament = findViewById<EditText>(R.id.etTemperament)
        val btnSave = findViewById<Button>(R.id.btnSave)

        btnSave.setOnClickListener {
            val name = etName.text.toString()
            val origin = etOrigin.text.toString()
            val temperament = etTemperament.text.toString()

            if (name.isNotEmpty() && origin.isNotEmpty() && temperament.isNotEmpty()) {
                // Comprobar si ya existe un Gato con el mismo nombre
                CoroutineScope(Dispatchers.IO).launch {
                    val existingGato = AppApplication.gatoDatabase.gatoDao().getGatoByName(name)

                    if (existingGato != null) {
                        // Si ya existe, mostrar mensaje de error en el hilo principal
                        withContext(Dispatchers.Main) {
                            Toast.makeText(this@AddGatoActivity, "Ya existe un Gato con ese nombre", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        // Si no existe, insertar el nuevo Gato
                        val newGato = GatoEntity(name = name, origin = origin, temperament = temperament)
                        AppApplication.gatoDatabase.gatoDao().insertOne(newGato)

                        // Mostrar mensaje de éxito en el hilo principal
                        withContext(Dispatchers.Main) {
                            Toast.makeText(this@AddGatoActivity, "Gato añadido", Toast.LENGTH_SHORT).show()
                        }
                        finish() // Cerrar la actividad
                    }
                }
            } else {

                    Toast.makeText(this@AddGatoActivity, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()

            }
        }
    }
}

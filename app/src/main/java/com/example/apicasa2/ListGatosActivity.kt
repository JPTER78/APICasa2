package com.example.apicasa2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListGatosActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_gatos)

        recyclerView = findViewById(R.id.recyclerViewGatos)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Cargar datos desde la base de datos
        CoroutineScope(Dispatchers.IO).launch {

            val gatos = AppApplication.gatoDatabase.gatoDao().getAll()

            runOnUiThread {
                recyclerView.adapter = GatoAdapter(gatos)
            }

        }
    }
}

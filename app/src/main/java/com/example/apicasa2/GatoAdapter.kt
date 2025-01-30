package com.example.apicasa2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GatoAdapter(private val gatos: List<GatoEntity>) : RecyclerView.Adapter<GatoAdapter.GatoViewHolder>() {

    class GatoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvName)
        val tvOrigin: TextView = view.findViewById(R.id.tvOrigin)
        val tvTemperament: TextView = view.findViewById(R.id.tvTemperament)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GatoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_gato, parent, false)
        return GatoViewHolder(view)
    }

    override fun onBindViewHolder(holder: GatoViewHolder, position: Int) {
        val gato = gatos[position]
        holder.tvName.text = gato.name
        holder.tvOrigin.text = "Origen: ${gato.origin}"
        holder.tvTemperament.text = "Temperamento: ${gato.temperament}"
    }

    override fun getItemCount(): Int {
        return gatos.size
    }
}

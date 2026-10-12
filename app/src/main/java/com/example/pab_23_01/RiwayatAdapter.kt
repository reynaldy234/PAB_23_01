package com.example.pab_23_01

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RiwayatAdapter(private val list: List<RegistrationData>) :
    RecyclerView.Adapter<RiwayatAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvIdReg: TextView = itemView.findViewById(R.id.tvIdReg)
        val tvStatus: TextView = itemView.findViewById(R.id.tvStatus)
        val tvNama: TextView = itemView.findViewById(R.id.tvNama)
        val tvGender: TextView = itemView.findViewById(R.id.tvGender)
        val tvKota: TextView = itemView.findViewById(R.id.tvKota)
        val tvWaktu: TextView = itemView.findViewById(R.id.tvWaktu)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_riwayat, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.tvIdReg.text = item.id
        holder.tvStatus.text = item.status
        holder.tvNama.text = item.nama
        holder.tvGender.text = "Gender: ${item.gender}"
        holder.tvKota.text = "Kota: ${item.kota}"
        holder.tvWaktu.text = "Waktu: ${item.timestamp}"
    }

    override fun getItemCount(): Int = list.size
}

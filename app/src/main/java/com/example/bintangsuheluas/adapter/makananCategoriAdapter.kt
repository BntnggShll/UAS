package com.example.bintangsuheluas.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bintangsuheluas.databinding.ItemMknBinding
import com.squareup.picasso.Picasso
import mia.kotlin.project2a.model.ProdukModel

class makananCategoriAdapter (
    val results : ArrayList<ProdukModel.DataKategorimakanan>
):RecyclerView.Adapter<makananCategoriAdapter.viewHolder>() {
    lateinit var binding:ItemMknBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): makananCategoriAdapter.viewHolder {
        binding = ItemMknBinding.inflate(LayoutInflater.from(parent.context),
            parent,false)
        return viewHolder(binding)
    }

    override fun onBindViewHolder(holder: makananCategoriAdapter.viewHolder, position: Int) {
        val result = results[position]
        Picasso.get().load(result.image).into(holder.imgGambar)
        holder.tvNamaProduk.text = result.nama
        holder.tvHarga.text = result.harga
        holder.tvPromo.text = result.promo
    }

    override fun getItemCount() = results.size

    class viewHolder(binding: ItemMknBinding):
        RecyclerView.ViewHolder(binding.root) {
            //inisialisasi
            val imgGambar = binding.imgGambar
            val tvNamaProduk = binding.tvNamaProduk
            val tvHarga = binding.tvHarga
            val tvPromo = binding.tvPromo

    }
}
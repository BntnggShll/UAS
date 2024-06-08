package com.example.bintangsuheluas.produk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import com.example.bintangsuheluas.R
import com.example.bintangsuheluas.adapter.makananCategoriAdapter
import com.example.bintangsuheluas.databinding.ActivityMakanBinding
import mia.kotlin.project2a.config.Network
import mia.kotlin.project2a.model.ProdukModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MakanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMakanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMakanBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    override fun onResume() {
        super.onResume()
        cari()
        getMakanan("1","")
    }

    fun getMakanan(id_makanan : String, filter : String){
        val kategoriMakanan = Network.service().getMakanan(id_makanan,filter)
        kategoriMakanan.enqueue(object  : Callback<ProdukModel> {
            override fun onResponse(call: Call<ProdukModel>, response: Response<ProdukModel>) {
                if (response.isSuccessful){
                    val hasil = response.body()
                    if (hasil!!.success==1){
                        binding.imgNotFound.visibility = View.GONE
                        binding.rvMakan.visibility = View.VISIBLE
                        //Tampilkan isi dari WEB API ke Adapter
                        val MakananCategoriAdapter = makananCategoriAdapter(hasil.list_makanan)
                        binding.rvMakan.adapter =MakananCategoriAdapter
                    }else{
                        binding.imgNotFound.visibility = View.VISIBLE
                        binding.rvMakan.visibility = View.GONE
                    }
                }else{
                    Toast.makeText(this@MakanActivity,
                        "Gagal Terhubung ke WEB API",
                        Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<ProdukModel>, t: Throwable) {
                Toast.makeText(this@MakanActivity,
                    "Gagal Terhubung ke server",
                    Toast.LENGTH_LONG).show()
            }
        })
    }

    fun cari(){
        binding.svFilter.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                getMakanan("1", newText.toString())
                return true
            }
        })
    }
}
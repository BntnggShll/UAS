package mia.kotlin.project2a.model

data class ProdukModel(
    val success : Int,
    val message : String,
    val list_makanan : ArrayList<DataKategorimakanan>
){

    data class DataKategorimakanan(
        val image : String,
        val nama : String,
        val harga : String,
        val promo : String
    )

}

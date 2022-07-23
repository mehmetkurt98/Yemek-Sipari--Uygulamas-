package com.mehmetkurt.techcareerfinal.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.mehmetkurt.techcareerfinal.R
import com.mehmetkurt.techcareerfinal.databinding.BasketRowBinding
import com.mehmetkurt.techcareerfinal.model.Sepet
import com.mehmetkurt.techcareerfinal.viewmodel.BasketFragmentViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SepetAdapter(var mContext: Context, private var sepetList :List<Sepet>, var viewModel:BasketFragmentViewModel)
    : RecyclerView.Adapter<SepetAdapter.cardSepetTasarim>() {
    var x = "0"

    inner class cardSepetTasarim(cardSepetBinding: BasketRowBinding) : RecyclerView.ViewHolder(cardSepetBinding.root){

        var cardSepetBinding:BasketRowBinding
        init {
            this.cardSepetBinding = cardSepetBinding
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): cardSepetTasarim {

        val inflater = LayoutInflater.from(mContext)
        val tasarim = DataBindingUtil.inflate<BasketRowBinding>(inflater, R.layout.basket_row, parent, false)
        return cardSepetTasarim(tasarim)

    }

    override fun onBindViewHolder(holder: cardSepetTasarim, position: Int) {
        val t = holder.cardSepetBinding
        val sepet = sepetList[position]

        t.food = sepet

        val adet = sepetList[position].yemek_siparis_adet
        val fiyat = sepetList[position].yemek_fiyat.toInt()
        val toplam = adet * fiyat

        x = toplam.toString()

        Log.e("cevap", sepetList[position].yemek_siparis_adet.toString())

        t.imageViewSilYemek.setOnClickListener {
            Snackbar.make(it,"${sepetList[position].yemek_adi} silinsin mi ? ", Snackbar.LENGTH_LONG)
                .setAction("Evet"){
                    CoroutineScope(Dispatchers.Main).launch {
                    viewModel.deleteFoodFromBasket(sepetList[position].yemek_id, "Mustafa Kurt")
                        viewModel.getAllFoodToBasket("Mustafa Kurt")}
                }.show()
        }
    }

    override fun getItemCount(): Int {
        return sepetList.size
    }
    fun setList(newList: List<Sepet>) {
        sepetList = newList
        notifyDataSetChanged()
    }
}

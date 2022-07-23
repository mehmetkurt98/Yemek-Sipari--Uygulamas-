package com.mehmetkurt.techcareerfinal.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mehmetkurt.techcareerfinal.model.FoodAddResponse
import com.mehmetkurt.techcareerfinal.model.FoodResponse
import com.mehmetkurt.techcareerfinal.repo.DetailRepository
import com.mehmetkurt.techcareerfinal.repo.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailFragmentViewModel  @Inject constructor(private val repository: DetailRepository): ViewModel() {
     suspend fun yemekSepetEkle(yemek_adi: String, yemek_resim_adi: String, yemek_fiyat: String, yemek_siparis_adet: Int,
         kullanici_adi: String){
        Log.e("mesaj yemek","viewModel kısmına ulaştı")
        repository.yemekSepetEkle(yemek_adi,  yemek_resim_adi, yemek_fiyat, yemek_siparis_adet,kullanici_adi)
    }
}
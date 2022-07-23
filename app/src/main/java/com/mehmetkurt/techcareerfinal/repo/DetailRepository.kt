package com.mehmetkurt.techcareerfinal.repo

import android.util.Log
import com.mehmetkurt.techcareerfinal.base.BaseRepository
import com.mehmetkurt.techcareerfinal.service.FoodAPI
import javax.inject.Inject

class DetailRepository  @Inject constructor (
    private val foodAPI: FoodAPI
):BaseRepository() {
    suspend fun yemekSepetEkle(yemek_adi: String, yemek_resim_adi: String, yemek_fiyat: String, yemek_siparis_adet:Int, kullanici_adi:String) = safeApiRequest {
        Log.e("mesaj burada","buraya girdi")
        foodAPI.yemekEkle(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi)

    }
}
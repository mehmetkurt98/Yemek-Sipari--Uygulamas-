package com.mehmetkurt.techcareerfinal.repo

import android.util.Log
import com.mehmetkurt.techcareerfinal.base.BaseRepository
import com.mehmetkurt.techcareerfinal.service.FoodAPI
import javax.inject.Inject

class BasketRepository @Inject constructor (
    private val foodAPI: FoodAPI
): BaseRepository() {
    suspend fun getAllFoodToBasket(kullanici_adi:String) = safeApiRequest {
        foodAPI.tumSepettekiYemekler(kullanici_adi)
    }
    suspend fun deleteFoodFromBasket(yemek_id: Int, kullanici_adi:String) = safeApiRequest {
        Log.e("mesaj burada","buraya girdi Silme işlemi için")
        foodAPI.sepettenYemekSil(yemek_id, kullanici_adi)

    }

}
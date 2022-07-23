package com.mehmetkurt.techcareerfinal.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mehmetkurt.techcareerfinal.base.BaseRepository
import com.mehmetkurt.techcareerfinal.model.FoodModel
import com.mehmetkurt.techcareerfinal.service.FoodAPI
import com.mehmetkurt.techcareerfinal.viewmodel.DetailFragmentViewModel
import javax.inject.Inject

class FoodRepository @Inject constructor (
    private val foodAPI: FoodAPI,
): BaseRepository() {
    suspend fun getAllFood() = safeApiRequest {
        foodAPI.getAllFood()
    }

}
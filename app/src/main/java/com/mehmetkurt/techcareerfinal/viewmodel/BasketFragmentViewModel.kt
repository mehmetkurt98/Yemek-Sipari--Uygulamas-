package com.mehmetkurt.techcareerfinal.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mehmetkurt.techcareerfinal.model.BasketResponse
import com.mehmetkurt.techcareerfinal.model.FoodResponse
import com.mehmetkurt.techcareerfinal.repo.BasketRepository
import com.mehmetkurt.techcareerfinal.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketFragmentViewModel @Inject constructor(private val repository: BasketRepository): ViewModel() {
    val foodResponse: MutableLiveData<BasketResponse?> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData(true)
    val onError: MutableLiveData<String?> = MutableLiveData()

    suspend fun getAllFoodToBasket (kullanici_adi: String) = viewModelScope.launch {
        isLoading.value = true
        when (val request = repository.getAllFoodToBasket(kullanici_adi)) {
            is NetworkResult.Success<*> -> {
                isLoading.value = false
                foodResponse.value = request.data
            }
            is NetworkResult.Error<*> -> {
                isLoading.value = false
                onError.value = request.message
            }
        }
    }
    suspend fun deleteFoodFromBasket(yemek_id: Int, kullanici_adi:String){
        Log.e("mesaj yemek","viewModel kısmına ulaştı")
        repository.deleteFoodFromBasket(yemek_id, kullanici_adi)
    }
}
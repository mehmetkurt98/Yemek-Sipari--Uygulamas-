package com.mehmetkurt.techcareerfinal.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mehmetkurt.techcareerfinal.model.FoodResponse
import com.mehmetkurt.techcareerfinal.repo.FoodRepository
import com.mehmetkurt.techcareerfinal.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodFragmentViewModel @Inject constructor(private val repository: FoodRepository): ViewModel() {
    val foodResponse: MutableLiveData<FoodResponse?> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData(true)
    val onError: MutableLiveData<String?> = MutableLiveData()

    fun getAllFood() = viewModelScope.launch {
        isLoading.value = true
        when (val request = repository.getAllFood()) {
            is NetworkResult.Success -> {
                foodResponse.value = request.data
                isLoading.value = false
            }
            is NetworkResult.Error -> {
                onError.value = request.message
                isLoading.value = false
            }
        }
    }
}
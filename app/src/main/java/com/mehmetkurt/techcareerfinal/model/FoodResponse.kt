package com.mehmetkurt.techcareerfinal.model

data class FoodResponse(
    val success: Int,
    val yemekler: List<Yemekler>
)
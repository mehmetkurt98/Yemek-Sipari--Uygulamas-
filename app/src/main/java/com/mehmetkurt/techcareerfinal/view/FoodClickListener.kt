package com.mehmetkurt.techcareerfinal.view

import com.mehmetkurt.techcareerfinal.model.Yemekler

interface FoodClickListener {
    fun onItemClick(food: Yemekler)
}
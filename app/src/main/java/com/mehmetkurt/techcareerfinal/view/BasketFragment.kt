package com.mehmetkurt.techcareerfinal.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.mehmetkurt.techcareerfinal.R
import com.mehmetkurt.techcareerfinal.adapter.SepetAdapter
import com.mehmetkurt.techcareerfinal.databinding.FragmentBasketBinding
import com.mehmetkurt.techcareerfinal.model.Sepet
import com.mehmetkurt.techcareerfinal.viewmodel.BasketFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BasketFragment : Fragment() {
    private lateinit var adapter: SepetAdapter
    private lateinit var tasarim:FragmentBasketBinding
    private lateinit var viewModel: BasketFragmentViewModel
    private val args by navArgs<BasketFragmentArgs>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:BasketFragmentViewModel by viewModels()
        viewModel = tempViewModel
        getAllFoodToBasket()
    }
    private fun setRecycler(data: List<Sepet>) {
        adapter = SepetAdapter(requireContext(), data, viewModel)
        tasarim.sepetAdapter = adapter
        tasarim.totalPrice.text = adapter.x
        adapter.setList(data)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_basket, container, false)
        tasarim.sepetFragment = this
        with(viewModel) {
            foodResponse.observe(viewLifecycleOwner, Observer {
                it?.let {
                    setRecycler(it.sepet_yemekler)
                }
            })
        }
        return tasarim.root
    }
    fun getAllFoodToBasket() {
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.getAllFoodToBasket("Mustafa Kurt")
        }
    }
}
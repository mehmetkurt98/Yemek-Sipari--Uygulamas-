package com.mehmetkurt.techcareerfinal.view

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.mehmetkurt.techcareerfinal.adapter.FoodListAdapter
import com.mehmetkurt.techcareerfinal.base.BaseFragment
import com.mehmetkurt.techcareerfinal.databinding.FragmentProductsBinding
import com.mehmetkurt.techcareerfinal.model.Yemekler
import com.mehmetkurt.techcareerfinal.viewmodel.FoodFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FoodFragment: BaseFragment<FragmentProductsBinding, FoodFragmentViewModel>(
    FragmentProductsBinding::inflate
) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       viewModel.getAllFood()
    }
    override val viewModel by viewModels<FoodFragmentViewModel>()

    override fun onCreateFinished() {
    }

    override fun initializeListeners() {

    }
    override fun observeEvents() {
        with(viewModel) {
            foodResponse.observe(viewLifecycleOwner, Observer {
                it?.let {
                    it.yemekler?.let { it1 -> setRecycler(it1) }
                }
            })
            isLoading.observe(viewLifecycleOwner, Observer {
                handleViews(it)
            })

            onError.observe(viewLifecycleOwner, Observer {
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
            })
        }
    }
    private fun setRecycler(data: List<Yemekler>) {
        val mAdapter = FoodListAdapter(object : FoodClickListener {
            override fun onItemClick(food: Yemekler) {
                val navigation = FoodFragmentDirections.actionProductsFragmentToDetailFragment(food)
                Navigation.findNavController(requireView()).navigate(navigation)
            }
        })
        binding.foodListRecycler.adapter = mAdapter
        mAdapter.setList(data)
    }
    private fun handleViews(isLoading: Boolean = false) {
        //binding.rvHome.isVisible = !isLoading
        //binding.pbHome.isVisible = isLoading
    }
}

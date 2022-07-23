package com.mehmetkurt.techcareerfinal.view

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.mehmetkurt.techcareerfinal.R
import com.mehmetkurt.techcareerfinal.base.BaseFragment
import com.mehmetkurt.techcareerfinal.databinding.FragmentDetailBinding
import com.mehmetkurt.techcareerfinal.extension.navigateToNextFragment
import com.mehmetkurt.techcareerfinal.model.Yemekler
import com.mehmetkurt.techcareerfinal.viewmodel.DetailFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.coroutines.*

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding, DetailFragmentViewModel>(
    FragmentDetailBinding::inflate
) {
    private val args by navArgs<DetailFragmentArgs>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    private fun tikla () {
        var count = 1
        binding.let {
            it.detayButtonAzalt.setOnClickListener {
                when (count) {
                    1 ->  binding.textViewAdet.text = "1"
                    else -> {
                        count -= 1
                        binding.textViewAdet.text = count.toString()
                    }
                }
            }
           it.detayButtonEkle.setOnClickListener {
               count += 1
               binding.textViewAdet.text = count.toString()
           }
            it.geriDonAnasayfa.setOnClickListener {
                navigateToNextFragment(R.id.productsFragment)
            }
            it.sepeteEkle.setOnClickListener {
                sepeteEkle(args.yemeklerObj,count)
            }
        }
    }
    fun sepeteEkle(yemekNesne: Yemekler, adet:Int){
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.yemekSepetEkle(yemekNesne.yemek_adi,
                yemekNesne.yemek_resim_adi,yemekNesne.yemek_fiyat,adet,"Mustafa Kurt")
        }
    }

    override val viewModel by viewModels<DetailFragmentViewModel>()


    override fun onCreateFinished() {
        binding.yemekNesnesi = args.yemeklerObj
        binding.detayYemekFragment = this
        tikla()
    }

    override fun initializeListeners() {}

    override fun observeEvents() {
    }

}
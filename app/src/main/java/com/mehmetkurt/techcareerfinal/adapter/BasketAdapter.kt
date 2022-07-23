package com.mehmetkurt.techcareerfinal.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mehmetkurt.techcareerfinal.databinding.BasketRowBinding
import com.mehmetkurt.techcareerfinal.model.Sepet
import com.mehmetkurt.techcareerfinal.view.BasketClickListener
import com.mehmetkurt.techcareerfinal.viewmodel.BasketFragmentViewModel

class BasketAdapter(var mContext: Context, var sepetList :List<Sepet>, var viewModel:BasketFragmentViewModel, private val listener: BasketClickListener): RecyclerView.Adapter<BasketAdapter.MViewHolder>() {

    class MViewHolder(private val binding: BasketRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: BasketClickListener,food: Sepet) {
            binding.basketListener = listener
            binding.food = food
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = BasketRowBinding.inflate(layoutInflater, parent, false)
                return MViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MViewHolder.from(parent)

    override fun onBindViewHolder(holder: MViewHolder, position: Int) =
        holder.bind(listener,sepetList[position])


    override fun getItemCount() = sepetList.size

    fun setList(newList: List<Sepet>) {
        sepetList = newList
        notifyDataSetChanged()
    }
}
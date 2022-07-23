package com.mehmetkurt.techcareerfinal.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mehmetkurt.techcareerfinal.view.FoodClickListener
import com.mehmetkurt.techcareerfinal.databinding.RecyclerRowBinding
import com.mehmetkurt.techcareerfinal.model.Yemekler

class FoodListAdapter(private val listener: FoodClickListener): RecyclerView.Adapter<FoodListAdapter.MViewHolder>() {
    private var foods = emptyList<Yemekler>()

    class MViewHolder(private val binding: RecyclerRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: FoodClickListener, food: Yemekler) {
            binding.foodListener = listener
            binding.food = food
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RecyclerRowBinding.inflate(layoutInflater, parent, false)
                return MViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MViewHolder.from(parent)

    override fun onBindViewHolder(holder: MViewHolder, position: Int) =
        holder.bind(listener, foods[position])

    override fun getItemCount() = foods.size

    fun setList(newList: List<Yemekler>) {
        foods = newList
        notifyDataSetChanged()
    }
}
package com.jessy.zoo.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jessy.zoo.data.ResultX
import com.jessy.zoo.databinding.ItemHomeBinding

class HomeAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<ResultX, HomeAdapter.HomeViewHolder>(DiffCallback){

    class HomeViewHolder(private var binding: ItemHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(resultX: ResultX) {
            binding.resultX = resultX
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(ItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val dataAll = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(dataAll)
        }
        holder.bind(dataAll)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<ResultX>() {
        override fun areItemsTheSame(oldItem: ResultX, newItem: ResultX): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ResultX, newItem: ResultX): Boolean {
            return oldItem == newItem
        }
    }

    class OnClickListener(val clickListener: (resultX: ResultX) -> Unit) {
        fun onClick(resultX: ResultX) = clickListener(resultX)
    }
}
package com.jessy.zoo.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jessy.zoo.data.DataAll
import com.jessy.zoo.databinding.ItemHomeBinding

class HomeAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<DataAll, HomeAdapter.HomeViewHolder>(DiffCallback){

    class HomeViewHolder(private var binding: ItemHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(dataAll: DataAll) {
            binding.dataAll = dataAll
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

    companion object DiffCallback : DiffUtil.ItemCallback<DataAll>() {
        override fun areItemsTheSame(oldItem: DataAll, newItem: DataAll): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: DataAll, newItem: DataAll): Boolean {
            return oldItem == newItem
        }
    }

    class OnClickListener(val clickListener: (dataAll: DataAll) -> Unit) {
        fun onClick(dataAll: DataAll) = clickListener(dataAll)
    }
}
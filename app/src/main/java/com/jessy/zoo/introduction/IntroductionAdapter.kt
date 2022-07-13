package com.jessy.zoo.introduction

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jessy.zoo.data.ResultX
import com.jessy.zoo.data.ResultY
import com.jessy.zoo.databinding.ItemHomeBinding
import com.jessy.zoo.databinding.ItemIntroductionBinding
import com.jessy.zoo.home.HomeAdapter

class IntroductionAdapter (private val onClickListener: IntroductionAdapter.OnClickListener) :
    ListAdapter<ResultY, IntroductionAdapter.IntroductionViewHolder>(DiffCallback){

    class IntroductionViewHolder(private var binding: ItemIntroductionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(resultY: ResultY) {
            binding.resultY = resultY
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroductionAdapter.IntroductionViewHolder {
        return IntroductionAdapter.IntroductionViewHolder(ItemIntroductionBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: IntroductionAdapter.IntroductionViewHolder, position: Int) {
        val introductionAll = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(introductionAll)
        }
        holder.bind(introductionAll)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<ResultY>() {
        override fun areItemsTheSame(oldItem: ResultY, newItem: ResultY): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ResultY, newItem: ResultY): Boolean {
            return oldItem == newItem
        }
    }

    class OnClickListener(val clickListener: (resultY: ResultY) -> Unit) {
        fun onClick(resultY: ResultY) = clickListener(resultY)
    }
}
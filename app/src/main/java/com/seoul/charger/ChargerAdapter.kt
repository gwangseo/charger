package com.seoul.charger

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.seoul.charger.databinding.ItemChargerBinding

class ChargerAdapter : ListAdapter<CHARGERSTTS, ChargerAdapter.ChargerViewHolder>(ChargerDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChargerViewHolder {
        val binding = ItemChargerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChargerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChargerViewHolder, position: Int) {
        val charger = getItem(position)
        holder.bind(charger)
    }

    class ChargerViewHolder(private val binding: ItemChargerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(charger: CHARGERSTTS) {
            binding.charger = charger
            binding.executePendingBindings()
        }
    }

    class ChargerDiffCallback : DiffUtil.ItemCallback<CHARGERSTTS>() {
        override fun areItemsTheSame(oldItem: CHARGERSTTS, newItem: CHARGERSTTS): Boolean {
            return oldItem.sTATID == newItem.sTATID
        }

        override fun areContentsTheSame(oldItem: CHARGERSTTS, newItem: CHARGERSTTS): Boolean {
            return oldItem == newItem
        }
    }
}

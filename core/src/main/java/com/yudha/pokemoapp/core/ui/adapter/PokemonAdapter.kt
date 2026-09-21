package com.yudha.pokemoapp.core.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yudha.pokemoapp.core.databinding.ItemPokemonBinding
import com.yudha.pokemoapp.core.domain.model.Pokemon

class PokemonAdapter(
    private val onClick: (Pokemon) -> Unit
) : ListAdapter<Pokemon, PokemonAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemPokemonBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemon: Pokemon) {
            binding.tvName.text = pokemon.name
            Glide.with(binding.ivPokemon)
                .load(pokemon.imageUrl)
                .into(binding.ivPokemon)
            
            binding.root.setOnClickListener { onClick(pokemon) }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Pokemon>() {
        override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean = oldItem.name == newItem.name
        override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean = oldItem == newItem
    }
}

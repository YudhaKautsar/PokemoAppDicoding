package com.yudha.pokemoapp.ui.detail

import android.view.View
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.yudha.pokemoapp.R
import com.yudha.pokemoapp.databinding.ActivityDetailBinding
import com.yudha.pokemoapp.core.domain.model.Pokemon
import com.yudha.pokemoapp.core.base.BaseActivity
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : BaseActivity<ActivityDetailBinding>(ActivityDetailBinding::inflate) {

    companion object {
        const val EXTRA_NAME = "extra_name"
    }

    private val viewModel: PokemonDetailViewModel by viewModel()

    override fun setupView() {
        binding.toolbar.setNavigationOnClickListener { finish() }
        val name = intent.getStringExtra(EXTRA_NAME)
        if (name != null) {
            viewModel.fetchPokemonDetail(name)
        }
    }

    override fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.pokemonDetail.collect { detail ->
                        detail?.let {
                            binding.tvNameDetail.text = it.name
                            binding.tvHeight.text = getString(R.string.height_format, it.height)
                            binding.tvWeight.text = getString(R.string.weight_format, it.weight)
                            binding.tvTypes.text = getString(R.string.types_format, it.types.joinToString(", "))
                            binding.tvDescription.text = it.description
                            Glide.with(this@DetailActivity)
                                .load(it.imageUrl)
                                .into(binding.ivPokemonDetail)

                            binding.btnFavorite.setOnClickListener { _ ->
                                viewModel.toggleFavorite(
                                    Pokemon(
                                        name = it.name,
                                        url = "https://pokeapi.co/api/v2/pokemon/${it.id}/",
                                        imageUrl = it.imageUrl ?: ""
                                    )
                                )
                            }
                        }
                    }
                }
                launch {
                    viewModel.isFavorite.collect { isFavorite ->
                        binding.btnFavorite.text = if (isFavorite) {
                            getString(R.string.remove_from_favorite)
                        } else {
                            getString(R.string.add_to_favorite)
                        }
                    }
                }
                launch {
                    viewModel.isLoading.collect { isLoading ->
                        binding.progressBarDetail.visibility = if (isLoading) View.VISIBLE else View.GONE
                    }
                }
                launch {
                    viewModel.error.collect { error ->
                        error?.let {
                            Toast.makeText(this@DetailActivity, it, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}

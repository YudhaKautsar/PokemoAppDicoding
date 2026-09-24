package com.yudha.pokemoapp.ui.detail

import android.view.View
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.yudha.pokemoapp.core.utils.Constants
import com.yudha.pokemoapp.core.utils.loadImage
import com.yudha.pokemoapp.R
import com.yudha.pokemoapp.databinding.ActivityDetailBinding
import com.yudha.pokemoapp.core.domain.model.Pokemon
import com.yudha.pokemoapp.core.base.BaseActivity
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : BaseActivity<ActivityDetailBinding>(ActivityDetailBinding::inflate) {

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
                binding.apply {
                    launch {
                        viewModel.pokemonDetail.collect { detail ->
                            detail?.let {
                                tvNameDetail.text = it.name
                                tvHeight.text = getString(R.string.height_format, it.height)
                                tvWeight.text = getString(R.string.weight_format, it.weight)
                                tvTypes.text =
                                    getString(R.string.types_format, it.types.joinToString(", "))
                                tvDescription.text = it.description
                                ivPokemonDetail.loadImage(it.imageUrl)


                                btnFavorite.setOnClickListener { _ ->
                                    viewModel.toggleFavorite(
                                        Pokemon(
                                            name = it.name,
                                            url = "${Constants.POKE_API_POKEMON_URL}${it.id}/",
                                            imageUrl = it.imageUrl.orEmpty()
                                        )
                                    )
                                }
                            }

                        }
                    }
                    launch {
                        viewModel.isFavorite.collect { isFavorite ->
                            btnFavorite.text = if (isFavorite) {
                                getString(R.string.remove_from_favorite)
                            } else {
                                getString(R.string.add_to_favorite)
                            }
                        }
                    }
                    launch {
                        viewModel.isLoading.collect { isLoading ->
                            progressBarDetail.visibility =
                                if (isLoading) View.VISIBLE else View.GONE
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

    companion object {
        const val EXTRA_NAME = Constants.EXTRA_NAME
    }

}

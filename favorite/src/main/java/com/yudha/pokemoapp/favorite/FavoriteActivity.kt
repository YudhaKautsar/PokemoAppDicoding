package com.yudha.pokemoapp.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.yudha.pokemoapp.favorite.databinding.ActivityFavoriteBinding
import com.yudha.pokemoapp.core.base.BaseActivity
import com.yudha.pokemoapp.core.ui.adapter.PokemonAdapter
import com.yudha.pokemoapp.favorite.di.favoriteModule
import com.yudha.pokemoapp.ui.detail.DetailActivity
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : BaseActivity<ActivityFavoriteBinding>(ActivityFavoriteBinding::inflate) {

    private val viewModel: FavoriteViewModel by viewModel()
    private lateinit var pokemonAdapter: PokemonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        loadKoinModules(favoriteModule)
        super.onCreate(savedInstanceState)
    }

    override fun setupView() {
        binding.toolbar.setNavigationOnClickListener { finish() }
        pokemonAdapter = PokemonAdapter { pokemon ->
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra(DetailActivity.EXTRA_NAME, pokemon.name)
            }
            startActivity(intent)
        }
        binding.rvFavorite.apply {
            adapter = pokemonAdapter
            layoutManager = GridLayoutManager(this@FavoriteActivity, 2)
        }
    }

    override fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.favoritePokemon.collect { list ->
                        pokemonAdapter.submitList(list)
                        binding.tvNoFavorite.visibility = if (list.isEmpty() && !viewModel.isLoading.value) View.VISIBLE else View.GONE
                    }
                }
                launch {
                    viewModel.isLoading.collect { isLoading ->
                        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
                    }
                }
                launch {
                    viewModel.error.collect { error ->
                        error?.let {
                            Toast.makeText(this@FavoriteActivity, it, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}

package com.yudha.pokemoapp.ui.list

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.yudha.pokemoapp.databinding.ActivityPokemonListBinding
import com.yudha.pokemoapp.core.base.BaseActivity
import com.yudha.pokemoapp.ui.detail.DetailActivity
import com.yudha.pokemoapp.core.ui.adapter.PokemonAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.appcompat.widget.SearchView
import com.yudha.pokemoapp.ui.settings.SettingsActivity

class PokemonListActivity : BaseActivity<ActivityPokemonListBinding>(ActivityPokemonListBinding::inflate) {

    private val viewModel: PokemonListViewModel by viewModel()
    private lateinit var pokemonAdapter: PokemonAdapter

    override fun setupView() {
        pokemonAdapter = PokemonAdapter { pokemon ->
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra(DetailActivity.EXTRA_NAME, pokemon.name)
            }
            startActivity(intent)
        }
        binding.rvPokemon.apply {
            adapter = pokemonAdapter
            layoutManager = GridLayoutManager(this@PokemonListActivity, 2)
        }
        binding.fabFavorite.setOnClickListener {
            try {
                val intent = Intent(this, Class.forName("com.yudha.pokemoapp.favorite.FavoriteActivity"))
                startActivity(intent)
            } catch (e: ClassNotFoundException) {
                Toast.makeText(this, "Favorite feature not available", Toast.LENGTH_SHORT).show()
            }
        }

        binding.fabSettings.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
        
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.onSearchQueryChanged(newText.orEmpty())
                return true
            }
        })
    }

    override fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.pokemonList.collect { list ->
                        pokemonAdapter.submitList(list)
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
                            Toast.makeText(this@PokemonListActivity, it, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}

package com.yudha.pokemoapp.ui.settings

import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.yudha.pokemoapp.R
import com.yudha.pokemoapp.databinding.ActivitySettingsBinding
import com.yudha.pokemoapp.core.base.BaseActivity
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsActivity : BaseActivity<ActivitySettingsBinding>(ActivitySettingsBinding::inflate) {

    private val viewModel: SettingsViewModel by viewModel()

    override fun setupView() {
        binding.toolbar.setNavigationOnClickListener { finish() }
    }

    override fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.themeSetting.collect { isDarkModeActive ->
                        binding.switchDarkMode.setOnCheckedChangeListener(null)
                        binding.switchDarkMode.isChecked = isDarkModeActive
                        binding.switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
                            viewModel.saveThemeSetting(isChecked)
                            if (isChecked) {
                                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                            } else {
                                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                            }
                        }
                    }
                }
                launch {
                    viewModel.sortSetting.collect { sortType ->
                        binding.rgSort.setOnCheckedChangeListener(null)
                        if (sortType == "name") {
                            binding.rbName.isChecked = true
                        } else {
                            binding.rbId.isChecked = true
                        }
                        binding.rgSort.setOnCheckedChangeListener { _, checkedId ->
                            val newSortType = if (checkedId == R.id.rbName) "name" else "id"
                            viewModel.saveSortSetting(newSortType)
                        }
                    }
                }
                launch {
                    viewModel.sortOrder.collect { isAscending ->
                        binding.rgOrder.setOnCheckedChangeListener(null)
                        if (isAscending) {
                            binding.rbAscending.isChecked = true
                        } else {
                            binding.rbDescending.isChecked = true
                        }
                        binding.rgOrder.setOnCheckedChangeListener { _, checkedId ->
                            viewModel.saveSortOrder(checkedId == R.id.rbAscending)
                        }
                    }
                }
            }
        }
    }
}

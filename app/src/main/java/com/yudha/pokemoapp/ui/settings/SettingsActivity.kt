package com.yudha.pokemoapp.ui.settings

import android.os.Bundle
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
        binding.switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            viewModel.saveThemeSetting(isChecked)
        }

        binding.rgSort.setOnCheckedChangeListener { _, checkedId ->
            val sortType = if (checkedId == R.id.rbName) "name" else "id"
            viewModel.saveSortSetting(sortType)
        }
    }

    override fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.themeSetting.collect { isDarkModeActive ->
                        binding.switchDarkMode.isChecked = isDarkModeActive
                        if (isDarkModeActive) {
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                        } else {
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                        }
                    }
                }
                launch {
                    viewModel.sortSetting.collect { sortType ->
                        if (sortType == "name") {
                            binding.rbName.isChecked = true
                        } else {
                            binding.rbId.isChecked = true
                        }
                    }
                }
            }
        }
    }
}

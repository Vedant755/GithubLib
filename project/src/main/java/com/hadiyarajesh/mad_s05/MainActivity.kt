package com.hadiyarajesh.mad_s05

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.hadiyarajesh.mad_s05.ui.App
import com.hadiyarajesh.mad_s05.ui.settings.SettingsViewModel
import com.hadiyarajesh.mad_s05.utility.updateResources
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val settingsViewModel by viewModels<SettingsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }

        lifecycleScope.launch {
            settingsViewModel.languageCode.collect { languageCode ->
                updateResources(this@MainActivity, languageCode)
            }
        }
    }
}

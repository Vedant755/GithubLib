package com.hadiyarajesh.mad_s05.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hadiyarajesh.mad_s05.repository.SettingsRepository
import com.hadiyarajesh.mad_s05.utility.ENGLISH_CODE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository
) : ViewModel() {
    val languageCode: StateFlow<String> = settingsRepository.languageCode.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = ENGLISH_CODE
    )

    fun saveLanguage(languageCode: String) {
        viewModelScope.launch {
            settingsRepository.saveLanguage(languageCode = languageCode)
        }
    }
}

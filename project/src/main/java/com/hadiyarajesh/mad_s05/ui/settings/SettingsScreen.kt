package com.hadiyarajesh.mad_s05.ui.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hadiyarajesh.mad_s05.R
import com.hadiyarajesh.mad_s05.ui.components.HorizontalSpacer
import com.hadiyarajesh.mad_s05.utility.ENGLISH_CODE
import com.hadiyarajesh.mad_s05.utility.HINDI_CODE


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    settingsViewModel: SettingsViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val selectedLanguage by remember { settingsViewModel.languageCode }.collectAsState()
    val topBarTile = remember(selectedLanguage) { context.getString(R.string.settings) }
    val hindiLanguageText = remember(selectedLanguage) { context.getString(R.string.hindi) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = topBarTile) })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(8.dp)
        ) {
            Text(
                text = stringResource(R.string.select_language),
                style = MaterialTheme.typography.titleMedium
            )

            Row(modifier = Modifier.fillMaxWidth()) {
                AssistChip(
                    onClick = { settingsViewModel.saveLanguage(ENGLISH_CODE) },
                    label = { Text(text = stringResource(R.string.english)) },
                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = if (selectedLanguage == ENGLISH_CODE) MaterialTheme.colorScheme.primary.copy(
                            alpha = 0.7f
                        ) else MaterialTheme.colorScheme.background
                    )
                )
                HorizontalSpacer(size = 8)

                AssistChip(
                    onClick = {
                        settingsViewModel.saveLanguage(HINDI_CODE)
                    },
                    label = { Text(text = hindiLanguageText) },
                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = if (selectedLanguage == HINDI_CODE) MaterialTheme.colorScheme.primary.copy(
                            alpha = 0.7f
                        ) else MaterialTheme.colorScheme.background
                    )
                )
            }
        }
    }
}

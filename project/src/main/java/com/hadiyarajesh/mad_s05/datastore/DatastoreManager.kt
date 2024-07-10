package com.hadiyarajesh.mad_s05.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.hadiyarajesh.mad_s05.utility.ENGLISH_CODE
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "my_datastore")

class DatastoreManager(
    @ApplicationContext private val context: Context
) {
    private val languageCodeKey = stringPreferencesKey("language_code")

    val languageCode: Flow<String> = context.dataStore.data.map { preference ->
        preference[languageCodeKey] ?: ENGLISH_CODE
    }

    suspend fun saveLanguage(languageCode: String) {
        context.dataStore.edit { preference ->
            preference[languageCodeKey] = languageCode
        }
    }
}

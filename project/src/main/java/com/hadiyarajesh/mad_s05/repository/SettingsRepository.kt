package com.hadiyarajesh.mad_s05.repository

import com.hadiyarajesh.mad_s05.datastore.DatastoreManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsRepository @Inject constructor(
    private val datastoreManager: DatastoreManager
) {
    val languageCode: Flow<String> = datastoreManager.languageCode

    suspend fun saveLanguage(languageCode: String) {
        datastoreManager.saveLanguage(languageCode = languageCode)
    }
}

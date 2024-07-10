package com.hadiyarajesh.mad_s05.utility

import android.content.Context
import java.util.Locale

const val ENGLISH_CODE = "en"
const val HINDI_CODE = "hi"

fun updateResources(
    context: Context,
    languageCode: String
) {
    val locale = Locale(languageCode).also {
        Locale.setDefault(it)
    }

    val resources = context.resources

    val configuration = resources.configuration.apply {
        this.locale = locale
        setLayoutDirection(locale)
    }

    resources.updateConfiguration(configuration, resources.displayMetrics)
}
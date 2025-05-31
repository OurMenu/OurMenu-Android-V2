package com.kuit.ourmenu.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton


private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

@Singleton
class PreferencesManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    companion object {
        private val LOCATION_PERMISSION_GRANTED =
            booleanPreferencesKey("location_permission_granted")
    }

    val locationPermissionGranted: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[LOCATION_PERMISSION_GRANTED] ?: false
        }

    suspend fun setLocationPermissionGranted(granted: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[LOCATION_PERMISSION_GRANTED] = granted
        }
    }
}
package com.pets.weatherapp.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.pets.weatherapp.data.model.CachedForecast
import com.pets.weatherapp.domain.repository.CacheRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json

class CacheRepositoryImpl(
    private val dataStore: DataStore<Preferences>
) : CacheRepository {

    companion object {
        val CACHED_FORECAST_JSON = stringPreferencesKey("cached_forecast_json")

        private val json = Json {
            ignoreUnknownKeys = true
            encodeDefaults = true
        }
    }

    override val cachedForecast: Flow<CachedForecast?> = dataStore.data
        .map { preferences ->
            preferences[CACHED_FORECAST_JSON]?.let { data ->
                try {
                    json.decodeFromString<CachedForecast>(data)
                } catch (e: Exception) {
                    null
                }
            }
        }

    override suspend fun cacheForecastData(data: CachedForecast) {
        dataStore.edit { preferences ->
            preferences[CACHED_FORECAST_JSON] = json.encodeToString(data)
        }
    }
}
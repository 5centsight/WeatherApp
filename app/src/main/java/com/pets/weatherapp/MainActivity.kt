package com.pets.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.pets.weatherapp.presentation.navigation.AppNavGraph
import com.pets.weatherapp.presentation.theme.WeatherAppTheme
import android.app.Activity

class MainActivity : ComponentActivity() {
    private var keepSplashOnScreen by mutableStateOf(true)

    /**
     * Вызывается при создании [Activity]. Выполняет начальную настройку интерфейса:
     * - Настраивает `edge-to-edge` режим
     * - Устанавливает `Compose` контент
     * - Инициализирует навигацию
     * - В течение начальной настройки интерфейса показывает Splash-экран
     *
     * @param savedInstanceState Сохраненное состояние [Activity] для восстановления
     *
     * @see enableEdgeToEdge
     * @see setContent
     * @see WeatherAppTheme
     * @see AppNavGraph
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().setKeepOnScreenCondition {
            keepSplashOnScreen
        }
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherAppTheme {
                AppNavGraph()
                keepSplashOnScreen = false
            }
        }
    }
}


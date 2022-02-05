package com.github.moriatsushi.compose.uis

import android.app.Application
import jp.co.cyberagent.katalog.ext.pagesaver.PageSaverExt
import jp.co.cyberagent.katalog.registerKatalog

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        registerKatalog(
            title = "Jetpack Compose UIs",
            extensions = listOf(PageSaverExt()),
        ) {
        }
    }
}

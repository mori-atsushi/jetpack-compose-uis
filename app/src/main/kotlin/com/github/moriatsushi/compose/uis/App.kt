package com.github.moriatsushi.compose.uis

import android.app.Application
import com.github.moriatsushi.compose.uis.indication.indicationGroup
import com.github.moriatsushi.compose.uis.layout.layoutGroup
import com.github.moriatsushi.compose.uis.textfield.textFieldGroup
import jp.co.cyberagent.katalog.ext.pagesaver.PageSaverExt
import jp.co.cyberagent.katalog.registerKatalog

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        registerKatalog(
            title = "Jetpack Compose UIs",
            extensions = listOf(PageSaverExt()),
        ) {
            group(
                indicationGroup,
                textFieldGroup,
                layoutGroup
            )
        }
    }
}

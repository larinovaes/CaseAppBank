package br.com.itaucasebank

import android.app.Application
import br.com.itaucasebank.di.modules.allModules
import org.koin.core.context.GlobalContext.startKoin

class ItauCaseApplication: Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin{
            modules(allModules)
        }
    }
}

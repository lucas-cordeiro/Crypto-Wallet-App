package br.com.lucas.cordeiro.cryptowalletapp.appstartup

import android.content.Context
import androidx.startup.Initializer
import br.com.lucas.cordeiro.cryptowalletapp.data.di.module.errorModule
import br.com.lucas.cordeiro.cryptowalletapp.data.di.module.repositoryModule
import br.com.lucas.cordeiro.cryptowalletapp.di.module.viewModelModule
import br.com.lucas.cordeiro.cryptowalletapp.domain.di.module.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

class KoinInitializer : Initializer<KoinApplication> {
    override fun create(context: Context): KoinApplication {
        return startKoin {
            androidContext(context)
            modules(
                listOf(
                    useCaseModule,
                    repositoryModule,
                    errorModule,
                    viewModelModule
                )
            )
        }
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}
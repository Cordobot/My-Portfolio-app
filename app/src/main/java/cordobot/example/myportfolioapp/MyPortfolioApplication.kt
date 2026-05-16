package cordobot.example.myportfolioapp

import android.app.Application
import cordobot.example.myportfolioapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyPortfolioApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        
        startKoin {
            androidLogger()
            androidContext(this@MyPortfolioApplication)
            modules(appModule)
        }
    }
}

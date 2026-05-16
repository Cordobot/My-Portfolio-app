package cordobot.example.myportfolioapp.di

import cordobot.example.myportfolioapp.data.repository.PortfolioRepositoryImpl
import cordobot.example.myportfolioapp.domain.repository.PortfolioRepository
import cordobot.example.myportfolioapp.presentation.PortfolioViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import cordobot.example.myportfolioapp.data.remote.FormspreeApi

val appModule = module {
    single {
        val contentType = "application/json".toMediaType()
        Retrofit.Builder()
            .baseUrl("https://formspree.io/")
            .addConverterFactory(Json.asConverterFactory(contentType))
            .build()
    }
    
    single<FormspreeApi> {
        get<Retrofit>().create(FormspreeApi::class.java)
    }

    single<PortfolioRepository> { PortfolioRepositoryImpl() }
    viewModel { PortfolioViewModel(get(), get()) }
}

package ru.grandibambino.testforlifehackstudio.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.grandibambino.testforlifehackstudio.domain.Repository
import ru.grandibambino.testforlifehackstudio.domain.network.Api
import ru.grandibambino.testforlifehackstudio.presentation.CompaniesViewModel
import ru.grandibambino.testforlifehackstudio.presentation.CompanyAdapter
import ru.grandibambino.testforlifehackstudio.presentation.CompanyDescriptionViewModel
import ru.grandibambino.testforlifehackstudio.repository.RepositoryImpl
import ru.grandibambino.testforlifehackstudio.utils.*
import ru.grandibambino.testforlifehackstudio.utils.image.ImageLoader
import ru.grandibambino.testforlifehackstudio.utils.image.ImageLoaderImpl
import java.util.concurrent.TimeUnit

private val interceptors = mutableListOf<Interceptor>(
    HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
)

private fun getOkHttpClient(interceptors: MutableList<Interceptor>): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(CONNECT_READ, TimeUnit.SECONDS)
        .also { builder ->
            interceptors.forEach {
                builder.addInterceptor(it)
            }
        }
        .build()
}

private fun getRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

private fun getApi(retrofit: Retrofit): Api {
    return retrofit.create(Api::class.java)
}

val appModules = module {
    single(named(OK_HTTPCLIENT)) { getOkHttpClient(interceptors) }
    single(named(RETROFIT)) { getRetrofit(get(named(OK_HTTPCLIENT))) }
    single(named(API_SERVICE)) { getApi(get(named(RETROFIT))) }
    single<Repository> { RepositoryImpl(get(named(API_SERVICE))) }
    factory { CompanyAdapter(get()) }
    factory<ImageLoader> { ImageLoaderImpl() }
}

val viewModelModules = module {
    viewModel { CompaniesViewModel(get()) }
    viewModel { CompanyDescriptionViewModel(get()) }
}
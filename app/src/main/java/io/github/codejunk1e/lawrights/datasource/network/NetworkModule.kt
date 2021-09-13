package io.github.codejunk1e.lawrights.datasource.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideDefaultIODispatcher() = Dispatchers.IO

    @Provides fun providesBaseURL() =
        "https://kmr-staging.lawpavilion.com/api/"

    @Provides
    fun providesLogginInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    fun providesHeaderInjector(
        sessionManager: SessionManager
    ): Interceptor {
        return Interceptor { chain ->
            val requestBuilder = chain.request().newBuilder()
            requestBuilder.addHeader("Authorization", "Bearer ${sessionManager.fetchAuthToken()}")
            chain.proceed(requestBuilder.build())
        }
    }

    @Provides
    fun providesOkHttp(
        logger :HttpLoggingInterceptor,
        injector :Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(logger)
            .addInterceptor(injector).build()
    }

    @Provides
    fun provideGsonConverter(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        converter :GsonConverterFactory,
        baseURL :String,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder().baseUrl(baseURL).addConverterFactory(converter).client(okHttpClient).build()
    }

    @Singleton
    @Provides
    fun providesLawRightsService(retrofit :Retrofit): LawRightsService {
        return retrofit.create(LawRightsService::class.java)
    }
}
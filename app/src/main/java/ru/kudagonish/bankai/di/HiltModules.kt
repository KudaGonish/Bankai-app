package ru.kudagonish.bankai.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.kudagonish.bankai.data.remote.AnimeInterface
import ru.kudagonish.bankai.data.remote.ConstantsInterface
import ru.kudagonish.bankai.utils.Constants
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object HiltModules {
    @Provides
    fun logger() = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    fun okHttpClient() = OkHttpClient.Builder()
        .addInterceptor(logger())
        .build()


    @Singleton
    @Provides
    fun provideRetrofitAnimeInterface(): AnimeInterface {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(AnimeInterface::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofitConstantsInterface(): ConstantsInterface {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ConstantsInterface::class.java)
    }
}
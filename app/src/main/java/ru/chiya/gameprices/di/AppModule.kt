package ru.chiya.gameprices.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.chiya.gameprices.data.remote.CheapSharkApi
import ru.chiya.gameprices.data.repository.GameRepositoryImpl
import ru.chiya.gameprices.domain.repository.GameRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideCheapSharkApi(): CheapSharkApi {
        return Retrofit.Builder()
            .baseUrl("https://www.cheapshark.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CheapSharkApi::class.java)
    }
    @Provides
    @Singleton
    fun provideGameRepository(api: CheapSharkApi): GameRepository{
        return GameRepositoryImpl(api)
    }
}

package com.example.retopichincha.data.api.network

import com.example.retopichincha.data.api.RecipesApiService
import com.example.retopichincha.data.respositoryImpl.RecipesRepositoryImpl
import com.example.retopichincha.domain.repository.RecipesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()

    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("http://192.168.1.37:3080/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideRecipeApi(retrofit: Retrofit): RecipesApiService =
        retrofit.create(RecipesApiService::class.java)

    @Provides
    fun RecipesRepository(apiService: RecipesApiService): RecipesRepository {
        return RecipesRepositoryImpl(apiService)
    }
}
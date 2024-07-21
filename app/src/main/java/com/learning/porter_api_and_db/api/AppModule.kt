package com.learning.porter_api_and_db.api

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    val apiKey = "https://jsonplaceholder.typicode.com/"

    @Provides
    fun provideRetrofit():Retrofit {
        return Retrofit.Builder()
            .baseUrl(apiKey)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun providesRepo(apiService: ApiService): DataRepo {
        return DataRepo(apiService)
    }
}
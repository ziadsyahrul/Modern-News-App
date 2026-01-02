package com.ziad.modernnewsapp.di

import android.content.Context
import androidx.room.Room
import com.ziad.modernnewsapp.data.local.NewsDatabase
import com.ziad.modernnewsapp.data.remote.NewsApi
import com.ziad.modernnewsapp.data.repository.NewsRepositoryImpl
import com.ziad.modernnewsapp.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNewsDatabase(@ApplicationContext context: Context): NewsDatabase {
        return Room.databaseBuilder(
            context,
            NewsDatabase::class.java,
            "news_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(NewsApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(db: NewsDatabase, api: NewsApi): NewsRepository {
        return NewsRepositoryImpl(db, api)
    }
}
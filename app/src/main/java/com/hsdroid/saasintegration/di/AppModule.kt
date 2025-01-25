package com.hsdroid.saasintegration.di

import android.app.Application
import androidx.room.Room
import com.hsdroid.saasintegration.data.APIService
import com.hsdroid.saasintegration.data.local.LocalDatabase
import com.hsdroid.saasintegration.data.local.dao.TaskDao
import com.hsdroid.saasintegration.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun providesApiService(): APIService {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(APIService::class.java)
    }

    @Singleton
    @Provides
    fun getRoomDB(context: Application) =
        Room.databaseBuilder(context, LocalDatabase::class.java, "SaaSIntegration")
            .fallbackToDestructiveMigrationOnDowngrade().build()

    @Singleton
    @Provides
    fun getDao(db: LocalDatabase): TaskDao {
        return db.getTaskDao()
    }

}
package com.example.testapplication.di

import android.content.Context
import androidx.room.Room
import com.example.testapplication.Utils
import com.example.testapplication.data.database.AppDatabase
import com.example.testapplication.data.database.BinDao
import com.example.testapplication.data.mappers.Mapper
import com.example.testapplication.data.network.CardDetailsService
import com.example.testapplication.data.repository.DataRepositoryImpl
import com.example.testapplication.data.storages.database.DatabaseStorage
import com.example.testapplication.data.storages.database.DatabaseStorageImpl
import com.example.testapplication.data.storages.network.NetworkStorage
import com.example.testapplication.data.storages.network.NetworkStorageImpl
import com.example.testapplication.domain.repository.DataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {


    @Provides
    fun provideNetworkStorage(cardDetailsService: CardDetailsService): NetworkStorage {
        return NetworkStorageImpl(cardDetailsService)
    }


    @Provides
    fun provideDatabaseStorage(binDao: BinDao, mapper: Mapper): DatabaseStorage {
        return DatabaseStorageImpl(binDao, mapper)
    }

    @Provides
    fun provideMapper(): Mapper = Mapper()

    @Provides
    @Singleton
    fun provideBinRepository(
        databaseStorage: DatabaseStorage,
        networkStorage: NetworkStorage
    ): DataRepository {
        return DataRepositoryImpl(networkStorage, databaseStorage)
    }

    @Singleton
    @Provides
    fun provideBinDao(appDatabase: AppDatabase): BinDao = appDatabase.binDao()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "BinNumberDb.db"
        ).build()


    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(Utils.BASE_URL)
        .client(OkHttpClient().newBuilder().build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideCardDetailsService(retrofit: Retrofit): CardDetailsService =
        retrofit.create(CardDetailsService::class.java)

}
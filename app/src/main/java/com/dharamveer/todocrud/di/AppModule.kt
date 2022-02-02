package com.dharamveer.todocrud.di

import android.app.Application
import androidx.room.Room
import com.dharamveer.todocrud.data.ToDoDatabase
import com.dharamveer.todocrud.data.ToDoRepository
import com.dharamveer.todocrud.data.ToDoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * Created by Dharamveer Gupta on 28-January-2022 12:28 AM,
 * dharamveer.gupt@gmail.com,
 * Roundr,
 * Navi Mumbai, Maharashtra, India.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideToDoDatabase(app: Application): ToDoDatabase {
        return Room.databaseBuilder(
            app,
            ToDoDatabase::class.java,
            "todo_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideToDoRepository(db: ToDoDatabase): ToDoRepository {
        return ToDoRepositoryImpl(db.dao)
    }
}
package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.abstraction.datasource.MovieLocalDataSource
import com.example.abstraction.datasource.MovieRemoteDataSource
import com.example.abstraction.repository.MovieRepository
import com.example.data.api.service.MovieService
import com.example.data.database.AppDatabase
import com.example.data.database.dao.MovieDao
import com.example.data.datasource.MovieLocalDataSourceImpl
import com.example.data.datasource.MovieRemoteDataSourceImpl
import com.example.data.repository.MovieRepositoryImpl
import com.example.usecase.*
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
object DataInject {

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideMovieService(retrofit: Retrofit) = retrofit.create(MovieService::class.java)

    @Singleton
    @Provides
    fun provideMovieRemoteDataSource(
        movieService: MovieService
    ): MovieRemoteDataSource = MovieRemoteDataSourceImpl(
        movieService
    )

    @Singleton
    @Provides
    fun provideMovieLocalDataSource(movieDao: MovieDao): MovieLocalDataSource =
        MovieLocalDataSourceImpl(movieDao)

    @Singleton
    @Provides
    fun provideMovieRepository(
        movieRemoteDataSource: MovieRemoteDataSource,
        movieLocalDataSource: MovieLocalDataSource
    ): MovieRepository = MovieRepositoryImpl(movieRemoteDataSource, movieLocalDataSource)

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "myapp"
        ).build()
    }

    @Provides
    fun provideMovieDao(appDatabase: AppDatabase): MovieDao {
        return appDatabase.movieDao()
    }

    @Singleton
    @Provides
    fun provideGetMovies(
        movieRepository: MovieRepository
    ) = GetMoviesUseCase(movieRepository)

    @Singleton
    @Provides
    fun provideGetMovie(
        movieRepository: MovieRepository
    ) = GetMovieUseCase(movieRepository)

    @Singleton
    @Provides
    fun provideGetFavoriteMovie(
        movieRepository: MovieRepository
    ) = GetFavoriteMovieUseCase(movieRepository)

    @Singleton
    @Provides
    fun provideFindFavoriteMovie(
        movieRepository: MovieRepository
    ) = FindFavoriteMovieUseCase(movieRepository)

    @Singleton
    @Provides
    fun provideInsertFavoriteMovie(
        movieRepository: MovieRepository
    ) = InsertFavoriteMovieUseCase(movieRepository)

    @Singleton
    @Provides
    fun provideDeleteFavoriteMovie(
        movieRepository: MovieRepository
    ) = DeleteFavoriteMovieUseCase(movieRepository)

    @Singleton
    @Provides
    fun provideSearchMovie(
        movieRepository: MovieRepository
    ) = SearchMovieUseCase(movieRepository)
}
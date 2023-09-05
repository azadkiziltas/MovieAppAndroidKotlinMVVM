package com.example.movie.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movie.data.model.Movie.Result


@Database(entities = [Result::class], version = 1)
abstract class WatchListDatabase: RoomDatabase() {

    abstract fun movieDAO(): MovieDAO

    companion object {
        private var instance: WatchListDatabase? = null

        fun getMovieDatabase(context: Context): WatchListDatabase {
            if (instance == null) {
                instance =  Room.databaseBuilder(context,WatchListDatabase::class.java,"btk.db")
                    .allowMainThreadQueries()
                    .build()
            }
            return instance as WatchListDatabase
        }
    }
}
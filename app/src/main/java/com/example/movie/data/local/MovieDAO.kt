package com.example.movie.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movie.data.model.Movie.Result

@Dao
interface MovieDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(movie: Result)

    @Delete
    fun delete(movie: Result)


    @Query("SELECT*FROM movie")
    fun allMovies(): List<Result>

    @Query("SELECT*FROM movie where title like '%' || :title || '%'")
    fun searchMovie(title: String): List<Result>


    @Query("SELECT*FROM movie where id=:id")
    fun getMovieById(id: Int): Result
}
package com.example.movie.data.model.Movie

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class MovieResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)

@Entity(tableName = "movie")
data class Result (
    @SerializedName("adult")
    @ColumnInfo("adult")
    val adult: Boolean,
    @SerializedName("backdrop_path")
    @ColumnInfo("backdrop_path")
    val backdropPath: String,
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    @ColumnInfo("id")
    val id: Int,
    @SerializedName("original_language")
    @ColumnInfo("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    @ColumnInfo("original_title")
    val originalTitle: String,
    @SerializedName("overview")
    @ColumnInfo("overview")
    val overview: String,
    @SerializedName("popularity")
    @ColumnInfo("popularity")
    val popularity: Double,
    @SerializedName("poster_path")
    @ColumnInfo("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    @ColumnInfo("release_date")
    val releaseDate: String,
    @SerializedName("title")
    @ColumnInfo("title")
    val title: String,
    @SerializedName("video")
    @ColumnInfo("video")
    val video: Boolean,
    @SerializedName("vote_average")
    @ColumnInfo("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    @ColumnInfo("vote_count")
    val voteCount: Int

): Serializable
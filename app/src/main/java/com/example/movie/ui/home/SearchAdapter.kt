package com.example.movie.ui.home

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movie.data.local.WatchListDatabase
import com.example.movie.data.model.Movie.Result
import com.example.movie.databinding.ItemMovieSearchBinding
import com.example.movie.util.constants.Constants


class SearchAdapter(
    private var movieList: List<Result>,
    var onclick: (Result) -> Unit,
    var movieDatabaseControl: (Result,Boolean) -> Unit,
    var context: Context?
) :
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    var database : WatchListDatabase? = null

    inner class ViewHolder(val binding: ItemMovieSearchBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMovieSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        database = WatchListDatabase.getMovieDatabase(context!!)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            binding.apply {
                    addWatchList.isChecked = false
                Log.d("___", "onBindViewHolder: "+ database?.movieDAO()?.allMovies())
                if (database?.movieDAO()?.getMovieById(movieList.get(position).id) != null){
                    addWatchList.isChecked = true
                }
                itemTitleTextview.text = movieList.get(position).title
                itemCard.setOnClickListener {
                    onclick(movieList.get(position))

                }
                addWatchList.setOnCheckedChangeListener { checkBox, isChecked ->
                        movieDatabaseControl(movieList.get(position),isChecked)
                }

                val url = Constants.IMAGE_BASE_URL + "t/p/w500" + movieList.get(position).posterPath
                Glide.with(context!!)
                    .load(url)
                    .into(itemImageview)
            }
        }
    }


    override fun getItemCount(): Int {
        return movieList.size
    }
}
package com.example.movie.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movie.data.local.WatchListDatabase
import com.example.movie.databinding.ItemPeopleSearchBinding
import com.example.movie.util.constants.Constants


class SearchPeopleAdapter(
    private var movieList: List<com.example.movie.data.model.People.Result>,
    var onclick: (com.example.movie.data.model.People.Result) -> Unit,
    var movieDatabaseControl: (com.example.movie.data.model.People.Result,Boolean) -> Unit,
    var context: Context?
) :
    RecyclerView.Adapter<SearchPeopleAdapter.ViewHolder>() {
    var database : WatchListDatabase? = null

    inner class ViewHolder(val binding: ItemPeopleSearchBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPeopleSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        database = WatchListDatabase.getMovieDatabase(context!!)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            binding.apply {

                itemTitleTextview.text = movieList.get(position).name
                itemCard.setOnClickListener {
                    onclick(movieList.get(position))

                }


                val url = Constants.IMAGE_BASE_URL + "t/p/w500" + movieList.get(position).profilePath
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
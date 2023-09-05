package com.example.movie.ui.tvshow

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movie.databinding.ItemMovieBinding
import com.example.movie.util.constants.Constants


class TvShowAdapter(
    private var tvShowList: List<com.example.movie.data.model.TvShow.Result>,
    var onclick: (Int) -> Unit,
    var context: Context?
) :
    RecyclerView.Adapter<TvShowAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            binding.apply {
                itemTitleTextview.text = tvShowList.get(position).name
                itemCard.setOnClickListener {
                    onclick(position)
                }

                val url = Constants.IMAGE_BASE_URL + "t/p/w500" + tvShowList.get(position).posterPath
                Glide.with(context!!)
                    .load(url)
                    .into(itemImageview)
            }
        }
    }


    override fun getItemCount(): Int {
        return tvShowList.size
    }
}
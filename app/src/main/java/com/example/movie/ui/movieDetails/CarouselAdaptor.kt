package com.example.movie.ui.movieDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.data.model.Movie.MovieImagesResponse
import com.example.movie.databinding.CarouselBinding
import com.example.movie.util.constants.Constants
import com.example.movie.util.loadFromUrl

class CarouselAdaptor(
    var images: MovieImagesResponse?,
    var onClik: (Int) -> Unit,
    var ondelete: (Int) -> Unit
) :
    RecyclerView.Adapter<CarouselAdaptor.ViewHolder>() {
    inner class ViewHolder(val binding: CarouselBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CarouselBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        with(holder) {
            binding.apply {
                carouselImageView.loadFromUrl(
                    Constants.IMAGE_BASE_URL + "t/p/w500" + (images!!.backdrops?.get(
                        position
                    )?.filePath)
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return images?.backdrops?.size ?: 0
    }
}


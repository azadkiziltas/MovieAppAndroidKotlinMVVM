package com.example.movie.ui.movieDetails

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.movie.data.local.WatchListDatabase
import com.example.movie.data.model.Movie.MovieDetailsResponse
import com.example.movie.data.model.Movie.MovieImagesResponse
import com.example.movie.data.model.Movie.Result
import com.example.movie.databinding.ActivityDetailsBinding
import com.example.movie.util.constants.Constants
import com.example.movie.util.loadFromUrl
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.internal.EdgeToEdgeUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    val viewModel: DetailsViewModel by viewModels()
    var result: Result? = null
    var database : WatchListDatabase? = null
    private lateinit var carouselAdapter: CarouselAdaptor


    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        EdgeToEdgeUtils.applyEdgeToEdge(window, true)
        database = WatchListDatabase.getMovieDatabase(applicationContext)


        val intent = intent
        result = intent.getSerializableExtra("result") as Result


        viewModel.getMovieDetails(result!!.id.toString())
        viewModel.apply {
            movieDetailsLiveData.observe(this@DetailsActivity, Observer {

                Log.d("___", "onCreate: " + it)
                initUi(it)

            })

            error.observe(this@DetailsActivity, Observer {
                it.run {

                }
            })
            loading.observe(this@DetailsActivity, Observer {

            })
        }

        viewModel.getMovieImages(result!!.id.toString())
        viewModel.apply {
            movieImagesLiveData.observe(this@DetailsActivity, Observer {

                Log.d("___", "onCreate: " + it.backdrops)
                initCarousel(it)

            })

            error.observe(this@DetailsActivity, Observer {
                it.run {

                }
            })
            loading.observe(this@DetailsActivity, Observer {

            })
        }


    }

    private fun initCarousel(movieImagesResponse: MovieImagesResponse?) {
        binding.apply {
            carouselAdapter = CarouselAdaptor(movieImagesResponse,onClik = { pozisyon ->
                Log.d("___", "initCarousel: ")
            },               ondelete = { pozisyon ->

            })
            carouselRecyclerView.apply {
                adapter = carouselAdapter

                layoutManager = CarouselLayoutManager()
            }
        }
    }

    private fun initUi(response: MovieDetailsResponse?) {
        binding.apply {
            titleText.text = response?.title ?: null
            posterImageview.loadFromUrl(Constants.IMAGE_BASE_URL + "t/p/w500" + (response?.posterPath))
            descriptionTop.text = response?.overview ?: null
            descriptionBottom.text =
                "Vote avarage : " + (response?.voteAverage ?: null) + " - (" + (response?.voteCount
                    ?: 0) + ")"
            if (database?.movieDAO()?.getMovieById(result!!.id) != null){
                addWatchList.isChecked = true
            }
            binding.addWatchList.setOnCheckedChangeListener { button, ischeck ->
                if (ischeck) {
                    database?.movieDAO()?.insert(result!!)
                    Toast.makeText(applicationContext, "Movie added database", Toast.LENGTH_SHORT).show()

                } else {
                    database?.movieDAO()?.delete(result!!)
                    Toast.makeText(applicationContext, "Movie deleted database", Toast.LENGTH_SHORT).show()
                }
            }
            topAppBar.setNavigationOnClickListener {
                onBackPressed()
            }

        }

    }
}
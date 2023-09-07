package com.example.movie.ui.peopleDetails

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie.data.local.WatchListDatabase
import com.example.movie.data.model.People.PeopleDetailsResponse
import com.example.movie.data.model.People.Result
import com.example.movie.databinding.ActivityPeopleDetailsBinding
import com.example.movie.ui.home.movie.MovieAdapter
import com.example.movie.ui.movieDetails.DetailsActivity
import com.example.movie.util.constants.Constants
import com.example.movie.util.loadFromUrl
import com.google.android.material.internal.EdgeToEdgeUtils
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PeopleDetailsActivity : AppCompatActivity() {
    var result: Result? = null
    val viewModel: PeopleDetailsViewModel by viewModels()
    private lateinit var movieAdapter: MovieAdapter

    var database: WatchListDatabase? = null
    private lateinit var binding: ActivityPeopleDetailsBinding


    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPeopleDetailsBinding.inflate(layoutInflater)

        setContentView(binding.root)
        EdgeToEdgeUtils.applyEdgeToEdge(window, true)
        database = WatchListDatabase.getMovieDatabase(applicationContext)


        val intent = intent
        result = intent.getSerializableExtra("result") as Result
        Log.d("___", "onCreate: "+ result!!.name)

        viewModel.getMovieDetails(result!!.id.toString())
        viewModel.apply {
            peopleDetailsLiveData.observe(this@PeopleDetailsActivity, Observer {

                Log.d("___", "onCreate: " + it)
                initDetailsUi(it)

            })

            error.observe(this@PeopleDetailsActivity, Observer {
                it.run {

                }
            })
            loading.observe(this@PeopleDetailsActivity, Observer {

            })
        }

        initUi()
        initRecyclerView(result!!.knownFor)
    }

    private fun initDetailsUi(peopleDetailsResponse: PeopleDetailsResponse) {
        binding.apply { descriptionTop.text = peopleDetailsResponse.biography }
    }

    private fun initUi() {
        binding.apply {
            titleText.text = result?.name ?: null
            posterImageview.loadFromUrl(Constants.IMAGE_BASE_URL + "t/p/w500" + (result?.profilePath))
//            descriptionTop.text = result?.overview ?: null


            topAppBar.setNavigationOnClickListener {
                onBackPressed()
            }

        }

    }


    private fun initRecyclerView(movieList: List<com.example.movie.data.model.Movie.Result>) {
        binding?.apply {
            movieAdapter = MovieAdapter(
                movieList = movieList.filter { result: com.example.movie.data.model.Movie.Result -> result.title != null }, onclick = { result ->
                    val intent = Intent(applicationContext, DetailsActivity::class.java)
                    intent.putExtra("result",result)
                    startActivity(intent)



                },
                movieDatabaseControl = { result,isCheck ->
                    if (isCheck){
                        database?.movieDAO()?.insert(result)
                    }
                    else{
                        database?.movieDAO()?.delete(result)
                    }

                },
                applicationContext
            )
            recyclerview.apply {
                adapter = movieAdapter
                layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
//                layoutManager = LinearLayoutManager(applicationContext)

            }


        }
    }

}
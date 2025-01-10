package com.example.movie.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R
import com.example.movie.data.local.WatchListDatabase
import com.example.movie.data.model.Movie.Result
import com.example.movie.databinding.ActivityMainBinding
import com.example.movie.ui.movieDetails.DetailsActivity
import com.example.movie.ui.peopleDetails.PeopleDetailsActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.internal.EdgeToEdgeUtils
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    val viewModel: MainViewModel by viewModels()


    private lateinit var binding: ActivityMainBinding
    private var searchFilter: String = "movie"
    private val TAG = "___"
    private lateinit var searchMovieAdapter: SearchMovieAdapter
    private lateinit var searchPeopleAdapter: SearchPeopleAdapter
    var database: WatchListDatabase? = null


    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        EdgeToEdgeUtils.applyEdgeToEdge(window, true)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.fragment_view)

        navView.setupWithNavController(navController)
        database = WatchListDatabase.getMovieDatabase(applicationContext)




        binding.searchView.editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.d("TAG", "onTextChanged: " + s)

                if (searchFilter.equals("movie")) {
                    searchMovie(s.toString())
                } else {
                    searchPeople(s.toString())
                }


            }

            override fun afterTextChanged(s: Editable?) {
            }

        })


        binding.toggleButtons.isSelectionRequired = true
        binding.movieFilterButton.isChecked = true
        binding.toggleButtons.addOnButtonCheckedListener { group, checkedId, isChecked ->
            Log.d(TAG, "onCreate: " + checkedId)
            if (isChecked) {
                searchFilter = "people"
                searchPeople(binding.searchView.editText.text.toString())

            } else {

                searchFilter = "movie"
                searchMovie(binding.searchView.editText.text.toString())
            }


        }

    }

    private fun searchPeople(search: String) {
        viewModel.searchPeople(search)
        viewModel.apply {
            searchPeopleLiveData.observe(this@MainActivity, Observer {

                it.results.forEach {
                    Log.e("___", "it " + it.name)

                }
                initPeopleRecyclerView(it.results)

            })

            error.observe(this@MainActivity, Observer {
                it.run {

                }
            })
            loading.observe(this@MainActivity, Observer {

            })
        }
    }

    private fun initPeopleRecyclerView(results: List<com.example.movie.data.model.People.Result>) {

        binding?.apply {
            searchPeopleAdapter = SearchPeopleAdapter(
                movieList = results,

                onclick = { result ->
                    val intent = Intent(applicationContext, PeopleDetailsActivity::class.java)
                    intent.putExtra("result", result)
                    startActivity(intent)
                },

                movieDatabaseControl = { result, isCheck ->

                },


                applicationContext
            )
            movieRecyclerview.apply {
                adapter = searchPeopleAdapter
                layoutManager = LinearLayoutManager(context)
//                layoutManager = LinearLayoutManager(applicationContext)

            }


        }

    }

    private fun searchMovie(search: String) {
        viewModel.searchMovies(search)
        viewModel.apply {
            searchMovieLiveData.observe(this@MainActivity, Observer {

                it.results.forEach {
                    Log.e("___", "it " + it.title)

                }
                initRecyclerView(it.results)

            })

            error.observe(this@MainActivity, Observer {
                it.run {

                }
            })
            loading.observe(this@MainActivity, Observer {

            })
        }
    }

    private fun initRecyclerView(searchResponseMovie: List<Result>) {
        binding?.apply {
            searchMovieAdapter = SearchMovieAdapter(
                movieList = searchResponseMovie, onclick = { result ->

                    val intent = Intent(applicationContext, DetailsActivity::class.java)
                    intent.putExtra("result", result)
                    startActivity(intent)


                },
                movieDatabaseControl = { result, isCheck ->
                    if (isCheck) {
                        database?.movieDAO()?.insert(result)
                    } else {
                        database?.movieDAO()?.delete(result)
                    }

                },
                applicationContext
            )
            movieRecyclerview.apply {
                adapter = searchMovieAdapter
                layoutManager = LinearLayoutManager(context)
//                layoutManager = LinearLayoutManager(applicationContext)

            }


        }
    }

    override fun onResume() {
        super.onResume()
        if (searchFilter.equals("movie")) {

            searchMovie(binding.searchView.editText.text.toString())
        } else {
            searchPeople(binding.searchView.editText.text.toString())

        }
    }


}
package com.example.movie.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
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
import com.example.movie.ui.details.DetailsActivity
import com.google.android.material.internal.EdgeToEdgeUtils
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    val viewModel: MainViewModel by viewModels()


    private lateinit var binding: ActivityMainBinding
    private val TAG = "___"
    private lateinit var searchMovieAdapter: SearchAdapter
    var database : WatchListDatabase? = null


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
                Log.d("TAG", "onTextChanged: "+s)

                search(s.toString())


            }

            override fun afterTextChanged(s: Editable?) {
            }

        })


        binding.movieRecyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager

                val totalItemCount = layoutManager.itemCount
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()
                Log.d(TAG, "onScrolled: "+lastVisibleItem)
                // Eğer son görünür öğe, toplam öğe sayısının bir eksiği ise, sona gelinmiştir.
                if (lastVisibleItem == totalItemCount - 1) {
                    // RecyclerView'ın sonuna gelindi, yeni verileri yüklemek için gerekli işlemleri yapabilirsiniz.
                    Log.d("___", "onScrolled: ")

                    // Örneğin, yeni verileri yükleme işlemini burada başlatabilirsiniz.
                }
            }
        })

    }

    private fun search(search: String) {
        viewModel.getAllComments(search)
        viewModel.apply {
            searchLiveData.observe(this@MainActivity, Observer {

                it.results.forEach {
                    Log.e("___","it " +it.title)

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
            searchMovieAdapter = SearchAdapter(
                movieList = searchResponseMovie, onclick = { result ->

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
            movieRecyclerview.apply {
                adapter = searchMovieAdapter
                layoutManager = LinearLayoutManager(context)
//                layoutManager = LinearLayoutManager(applicationContext)

            }


        }
    }

    override fun onResume() {
        super.onResume()
        search(binding.searchView.editText.text.toString())
        }


}
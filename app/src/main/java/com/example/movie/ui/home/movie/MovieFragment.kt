package com.example.movie.ui.home.movie

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.data.local.WatchListDatabase
import com.example.movie.data.model.Movie.Result
import com.example.movie.databinding.FragmentMovieBinding
import com.example.movie.ui.details.DetailsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : Fragment() {

    private var _binding: FragmentMovieBinding? = null
    private lateinit var movieAdapter: MovieAdapter
    val movieViewModel: MovieViewModel by viewModels()
    var database : WatchListDatabase? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        val root: View = binding.root



        database = WatchListDatabase.getMovieDatabase(requireContext())
        observeViewModel()

        binding.movieRecyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as GridLayoutManager

                val totalItemCount = layoutManager.itemCount
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

                // Eğer son görünür öğe, toplam öğe sayısının bir eksiği ise, sona gelinmiştir.
                if (lastVisibleItem == totalItemCount - 1) {
                    // RecyclerView'ın sonuna gelindi, yeni verileri yüklemek için gerekli işlemleri yapabilirsiniz.
                    Log.d("___", "onScrolled: ")

                    // Örneğin, yeni verileri yükleme işlemini burada başlatabilirsiniz.
                }
            }
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



    fun scrollToTop() {
        _binding?.movieRecyclerview?.startNestedScroll(
            ViewCompat.SCROLL_AXIS_VERTICAL,
            ViewCompat.TYPE_NON_TOUCH
        )
        binding?.movieRecyclerview?.smoothScrollBy(0, -10000)
        Log.d("___", "scroolToTop: ")


    }
    fun loog(){
        Log.d("___", "loog: ")
    }


    fun observeViewModel() {

        movieViewModel.apply {
            allPopularMoviesLiveData.observe(viewLifecycleOwner, Observer {

                it.results.forEach {
                    Log.e("___", "it " + it.title)
                }
                initRecyclerView(it.results)
            })

            error.observe(viewLifecycleOwner, Observer {
                it.run {

                }
            })
            loading.observe(viewLifecycleOwner, Observer {

            })

        }
    }


    private fun initRecyclerView(movieList: List<Result>) {
        binding?.apply {
            movieAdapter = MovieAdapter(
                movieList = movieList, onclick = { result ->
                    val intent = Intent(context,DetailsActivity::class.java)
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
                getContext()
            )
            movieRecyclerview.apply {
                adapter = movieAdapter
                layoutManager = GridLayoutManager(context, 2)
//                layoutManager = LinearLayoutManager(applicationContext)

            }


        }
    }


    override fun onResume() {
        super.onResume()
        observeViewModel()
    }
}
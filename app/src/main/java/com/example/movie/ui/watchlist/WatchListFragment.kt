package com.example.movie.ui.watchlist

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movie.data.local.WatchListDatabase
import com.example.movie.data.model.Movie.Result
import com.example.movie.databinding.FragmentWatchListBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WatchListFragment : Fragment() {

    private var _binding: FragmentWatchListBinding? = null
    val watchListViewModel: WatchListViewModel by viewModels()
    private lateinit var watchListAdapter: WatchListAdapter
    var database: WatchListDatabase? = null

    var movieList: ArrayList<Result>? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        _binding = FragmentWatchListBinding.inflate(inflater, container, false)
        val root: View = binding.root



        database = WatchListDatabase.getMovieDatabase(requireContext())


        movieList = database?.movieDAO()?.allMovies() as ArrayList<Result>?


        initRecyclerView()
        movieList?.forEach {
            Log.d("___", "onCreateView: " + it.title)
        }

        return root

    }


    private fun initRecyclerView() {
        binding?.apply {
//            watchListRecyclerview.setItemViewCacheSize(movieList?.size ?: 0)
            watchListAdapter = WatchListAdapter(
                movieList = movieList!!, onclick = { result ->
                    Log.d("___", "initRecyclerView: " + result.title)
                },
                movieDatabaseControl = { result, isCheck, pozition ->
                    if (isCheck) {
                        database?.movieDAO()?.insert(result)

                    } else {






                        if (!watchListRecyclerview.isComputingLayout())
                        {
                            Handler().post(Runnable {
                                movieList!!.removeAt(pozition)
                                watchListAdapter.notifyDataSetChanged()
                                database?.movieDAO()?.delete(result)
                            })
                        }




                    }

                }, getContext()
            )
            watchListRecyclerview.apply {
                adapter = watchListAdapter
                layoutManager = GridLayoutManager(context, 2)
//                layoutManager = LinearLayoutManager(applicationContext)

            }


        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
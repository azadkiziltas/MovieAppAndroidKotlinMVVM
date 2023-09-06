package com.example.movie.ui.home.tvshow

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
import com.example.movie.data.model.TvShow.Result
import com.example.movie.databinding.FragmentTvShowBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TvShowFragment : Fragment() {
    private lateinit var tvShowAdapter: TvShowAdapter
    val tvShowViewModel: TvShowViewModel by viewModels()
    private var _binding: FragmentTvShowBinding? = null


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentTvShowBinding.inflate(inflater, container, false)
        val root: View = binding.root

        observeViewModel()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    fun scrollToTop() {
        _binding?.tvShowRecyclerview?.startNestedScroll(
            ViewCompat.SCROLL_AXIS_VERTICAL,
            ViewCompat.TYPE_NON_TOUCH
        )
        binding?.tvShowRecyclerview?.smoothScrollBy(0, -10000)
        Log.d("___", "scroolToTop: ")


    }


    fun observeViewModel() {

        tvShowViewModel.apply {
            allPopularTvShowLiveData.observe(viewLifecycleOwner, Observer {

                it.results.forEach {
                    Log.e("___", "it " + it.name)
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


    private fun initRecyclerView(tvShowList: List<Result>) {
        binding?.apply {
            tvShowAdapter = TvShowAdapter(
                tvShowList = tvShowList, onclick = { position ->
                    Log.d("___", "initRecyclerView: " + tvShowList.get(position).name)
                }, getContext()
            )
            tvShowRecyclerview.apply {
                adapter = tvShowAdapter
                layoutManager = GridLayoutManager(context, 2)
//                layoutManager = LinearLayoutManager(applicationContext)

            }


        }
    }
}
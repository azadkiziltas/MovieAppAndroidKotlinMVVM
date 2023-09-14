package com.example.movie.ui.home.people

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movie.databinding.FragmentPeopleBinding
import com.example.movie.ui.peopleDetails.PeopleDetailsActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PeopleFragment : Fragment() {
    private lateinit var peopleAdapter: PeopleAdapter

    private var _binding: FragmentPeopleBinding? = null
    val peopleViewModel: PeopleViewModel by viewModels()


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPeopleBinding.inflate(inflater, container, false)
        val root: View = binding.root
        observeViewModel()


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }




    fun observeViewModel() {

        peopleViewModel.apply {
            allPopularPeopleLiveData.observe(viewLifecycleOwner, Observer {

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


    private fun initRecyclerView(peopleList: List<com.example.movie.data.model.People.Result>) {
        binding?.apply {
            peopleAdapter = PeopleAdapter(
                peopleList = peopleList,
                onclick = { result ->
                    val intent = Intent(context, PeopleDetailsActivity::class.java)
                    intent.putExtra("result",result)
                    startActivity(intent)
                },
                getContext()
            )
            peopleRecyclerview.apply {
                adapter = peopleAdapter
                layoutManager = GridLayoutManager(context, 2)

            }


        }
    }

}
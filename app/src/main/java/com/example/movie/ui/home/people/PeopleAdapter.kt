package com.example.movie.ui.home.people

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movie.databinding.ItemPeopleBinding
import com.example.movie.util.constants.Constants


class PeopleAdapter(
    private var peopleList: List<com.example.movie.data.model.People.Result>,
    var onclick: (com.example.movie.data.model.People.Result) -> Unit,
    var context: Context?
) :
    RecyclerView.Adapter<PeopleAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemPeopleBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPeopleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            binding.apply {

                itemCard.setOnClickListener {
                    onclick(peopleList.get(position))

                }
                itemTitleTextview.text = peopleList.get(position).name

                val url = Constants.IMAGE_BASE_URL + "t/p/w500" + peopleList.get(position).profilePath
                Glide.with(context!!)
                    .load(url)
                    .into(itemImageview)
            }
        }
    }


    override fun getItemCount(): Int {
        return peopleList.size
    }
}
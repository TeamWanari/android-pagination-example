package com.example.along.animalrecyclerview.animals.adapter

import android.arch.paging.PagedListAdapter
import android.content.Context
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.along.animalrecyclerview.R
import com.example.along.animalrecyclerview.animals.model.Animal

import kotlinx.android.synthetic.main.animal_item.view.*

class AnimalAdapter constructor(context: Context) : PagedListAdapter<Animal, AnimalAdapter.AnimalViewHolder>(
        object : DiffUtil.ItemCallback<Animal>() {
            override fun areItemsTheSame(oldItem: Animal?, newItem: Animal?): Boolean = oldItem == newItem

            override fun areContentsTheSame(oldItem: Animal?, newItem: Animal?): Boolean = oldItem?.description == newItem?.description
        }) {

    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val view = mInflater.inflate(R.layout.animal_item, parent, false)
        return AnimalViewHolder(view)
    }

    // binds the data to the TextView in each row
    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        val animal = getItem(position)
        holder.animaTitle?.text = animal?.name
        holder.animaText?.text = animal?.description
    }

    class AnimalViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var animaTitle = itemView?.name_animal_item
        var animaText = itemView?.description_animal_item
    }
}
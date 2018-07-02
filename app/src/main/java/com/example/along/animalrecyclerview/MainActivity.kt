package com.example.along.animalrecyclerview

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.along.animalrecyclerview.animals.adapter.AnimalAdapter
import com.example.along.animalrecyclerview.animals.model.AnimalViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var animalRecyclerView: RecyclerView
    private lateinit var animalAdapter: AnimalAdapter
    private lateinit var animalViewModel: AnimalViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        animalRecyclerView = findViewById(R.id.animal_recycler_view)
        animalRecyclerView.layoutManager = LinearLayoutManager(this)
        animalAdapter = AnimalAdapter(this)
        animalViewModel = AnimalViewModel()

        animalRecyclerView.adapter = animalAdapter
        animalRecyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        animalViewModel.getAnimals()?.observe(this, Observer(animalAdapter::submitList))
    }
}
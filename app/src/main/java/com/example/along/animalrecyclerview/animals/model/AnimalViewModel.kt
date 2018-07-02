package com.example.along.animalrecyclerview.animals.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.PagedList
import com.example.along.animalrecyclerview.animals.manipulation.AnimalDataProvider

class AnimalViewModel : ViewModel() {
    private val provider: AnimalDataProvider? = AnimalDataProvider()

    fun getAnimals(): LiveData<PagedList<Animal>>? {
        return provider?.getAnimals()
    }
}
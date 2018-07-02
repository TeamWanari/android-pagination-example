package com.example.along.animalrecyclerview.animals.manipulation

import android.arch.lifecycle.LiveData
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.example.along.animalrecyclerview.animals.model.Animal

class AnimalDataProvider {

    var animalDataFactory: AnimalDataFactory = AnimalDataFactory()
    private val PAGE_SIZE = 4

    fun getAnimals(): LiveData<PagedList<Animal>>? {
        val config = PagedList.Config.Builder()
                .setInitialLoadSizeHint(PAGE_SIZE)
                .setPageSize(PAGE_SIZE)
                .build()


        return LivePagedListBuilder(animalDataFactory, config)
                .setInitialLoadKey("")
                .build()
    }
}
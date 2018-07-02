package com.example.along.animalrecyclerview.animals.manipulation

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.example.along.animalrecyclerview.animals.model.Animal

class AnimalDataFactory : DataSource.Factory<String, Animal>() {

    private var datasourceLiveData = MutableLiveData<AnimalDataSource>()

    override fun create(): AnimalDataSource {
        val dataSource = AnimalDataSource()
        datasourceLiveData.postValue(dataSource)
        return dataSource
    }
}
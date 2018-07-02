package com.example.along.animalrecyclerview.animals.manipulation

import android.arch.paging.ItemKeyedDataSource
import com.example.along.animalrecyclerview.animals.model.Animal
import com.example.along.animalrecyclerview.firebase.FirebaseManager
import io.reactivex.schedulers.Schedulers

class AnimalDataSource : ItemKeyedDataSource<String, Animal>() {

    init {
        FirebaseManager.getAnimalChangeSubject()?.observeOn(Schedulers.io())?.subscribeOn(Schedulers.computation())?.subscribe {
            invalidate()
        }
    }

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<Animal>) {
        FirebaseManager.getAnimals(params.requestedLoadSize).subscribe({
            callback.onResult(it)
        }, {})
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<Animal>) {
        FirebaseManager.getAnimalsAfter(params.key, params.requestedLoadSize).subscribe({
            callback.onResult(it)
        }, {})
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<Animal>) {
        FirebaseManager.getAnimalsBefore(params.key, params.requestedLoadSize).subscribe({
            callback.onResult(it)
        }, {})
    }

    override fun getKey(item: Animal): String {
        return item.objectKey ?: ""
    }
}
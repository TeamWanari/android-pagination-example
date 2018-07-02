package com.example.along.animalrecyclerview.animals.manipulation

import android.arch.paging.PositionalDataSource
import io.reactivex.annotations.NonNull
import java.util.*

abstract class BaseDataSource<T> : PositionalDataSource<T>() {
    protected abstract fun countItems(): Int
    protected abstract fun loadRangeAtPosition(position: Int, size: Int): List<T>?

    override fun loadInitial(@NonNull params: PositionalDataSource.LoadInitialParams,
                             @NonNull callback: PositionalDataSource.LoadInitialCallback<T>) {
        val total = countItems()

        if (total == 0) {
            callback.onResult(Collections.emptyList(), 0, 0)
        } else {
            val position = PositionalDataSource.computeInitialLoadPosition(params, total)
            val size = PositionalDataSource.computeInitialLoadSize(params, position, total)
            val list = loadRangeAtPosition(position, size)

            if (list != null && list.size == size) {
                callback.onResult(list, position, total)
            } else {
                invalidate()
            }
        }
    }

    override fun loadRange(@NonNull params: PositionalDataSource.LoadRangeParams,
                           @NonNull callback: PositionalDataSource.LoadRangeCallback<T>) {
        val list = loadRangeAtPosition(params.startPosition, params.loadSize)

        if (list != null) {
            callback.onResult(list)
        } else {
            invalidate()
        }
    }
}
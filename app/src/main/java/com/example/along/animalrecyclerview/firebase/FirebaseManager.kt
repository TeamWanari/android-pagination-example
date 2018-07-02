package com.example.along.animalrecyclerview.firebase

import com.androidhuman.rxfirebase2.database.RxFirebaseDatabase
import com.example.along.animalrecyclerview.animals.model.Animal
import com.google.firebase.database.*
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject

object FirebaseManager {
    private val ANIMAL_ROUTE = "animals"

    private val animalAdapterInvalidation = PublishSubject.create<Any>()

    val database = FirebaseDatabase.getInstance()
    val databaseRef = database.reference

    init {
        databaseRef.child(ANIMAL_ROUTE).addChildEventListener(object : ChildEventListener {
            override fun onCancelled(p0: DatabaseError) {}

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {
                animalAdapterInvalidation.onNext(true)
            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
                animalAdapterInvalidation.onNext(true)
            }

            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                animalAdapterInvalidation.onNext(true)
            }

            override fun onChildRemoved(p0: DataSnapshot) {
                animalAdapterInvalidation.onNext(true)
            }
        })
    }

    fun getAnimalChangeSubject(): PublishSubject<Any>? {
        return animalAdapterInvalidation
    }

    fun getAnimals(count: Int): Single<List<Animal>> {
        return RxFirebaseDatabase.data(databaseRef.child(ANIMAL_ROUTE).orderByKey().limitToFirst(count))
                .map {
                    it.getArrayValue(Animal::class.java)
                }
    }

    fun getAnimalsAfter(key: String, count: Int): Single<List<Animal>> {
        return RxFirebaseDatabase.data(databaseRef.child(ANIMAL_ROUTE).orderByKey().startAt(key).limitToFirst(count))
                .map {
                    it.getArrayValue(Animal::class.java).drop(1)
                }
    }

    fun getAnimalsBefore(key: String, count: Int): Single<List<Animal>> {
        return RxFirebaseDatabase.data(databaseRef.child(ANIMAL_ROUTE).orderByKey().endAt(key).limitToLast(count))
                .map {
                    it.getArrayValue(Animal::class.java).dropLast(1)
                }
    }
}
package com.example.along.animalrecyclerview.animals.model

import com.example.along.animalrecyclerview.firebase.FirebaseObject

data class Animal(
        val name: String? = null,
        val description: String? = null
): FirebaseObject()
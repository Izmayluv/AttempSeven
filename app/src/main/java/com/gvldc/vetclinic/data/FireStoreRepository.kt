package com.gvldc.vetclinic.data

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.gvldc.vetclinic.models.RecyclerViewDataModels
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.util.Date

class FireStoreRepository {

    private val fireStore = FirebaseFirestore.getInstance()

    suspend fun getPets(): List<RecyclerViewDataModels.ItemPet> = withContext(Dispatchers.IO) {
        val petsCollection = fireStore.collection("Pets")
        val petsSnapshot = petsCollection.get().await()

        val petsList = mutableListOf<RecyclerViewDataModels.ItemPet>()
        for (document in petsSnapshot) {
            val name = document.getString("name") ?: ""
            val imageUrl = document.getString("imageUrl") ?: ""
            val birthDateMillis = document.getLong("birthDate") ?: 0
            val breed = document.getString("breed") ?: ""

            val birthDate = Date(birthDateMillis)

            val pet = RecyclerViewDataModels.ItemPet(name, imageUrl, birthDate, breed)
            petsList.add(pet)
        }

        petsList

    }
}
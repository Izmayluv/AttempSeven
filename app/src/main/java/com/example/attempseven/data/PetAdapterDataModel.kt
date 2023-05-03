package com.example.attempseven.data

import java.util.Date

sealed class PetAdapterDataModel{

    data class Pet(
        val name: String,
        val imageUrl: String,
        val birthDay: Date,
        val breed: String
    ): PetAdapterDataModel()

    data class Header(
        val title: String
    ): PetAdapterDataModel()
}

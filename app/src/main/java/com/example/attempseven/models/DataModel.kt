package com.example.attempseven.models

import java.util.Date

sealed class DataModel{

    data class ItemHeader(
        val title: String
    ): DataModel()

    data class ItemHomeStart(
       val text: String,
       val imageUrlOne: String,
       val imageUrlTwo: String,
       val imageUrlThree: String,
       val imageUrlFour: String
    ): DataModel()

    data class ItemPet(
        val name: String,
        val imageUrl: String,
        val birthDay: Date,
        val breed: String,
        val test: String
    ): DataModel()
}

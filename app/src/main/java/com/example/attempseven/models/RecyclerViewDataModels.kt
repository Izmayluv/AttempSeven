package com.example.attempseven.models

import java.util.Date

sealed class RecyclerViewDataModels{

    data class ItemHeader(
        val title: String
    ): RecyclerViewDataModels()

    data class ItemHomeStart(
       val text: String,
       val imageUrlOne: String,
       val imageUrlTwo: String,
       val imageUrlThree: String,
       val imageUrlFour: String
    ): RecyclerViewDataModels()

    data class ItemPet(
        val name: String,
        val imageUrl: String,
        val birthDay: Date,
        val breed: String,
        val test: String
    ): RecyclerViewDataModels()

    data class ItemAppointmentWDoctor(
        val action: String,
        val image: String
    ): RecyclerViewDataModels()

    data class ViewPagerData(
        val imageUrl: String
    ): RecyclerViewDataModels()

    data class ItemViewPager(
        val list: MutableList<ViewPagerData>
    ): RecyclerViewDataModels()
}

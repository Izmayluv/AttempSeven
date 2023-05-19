package com.gvldc.vetclinic.domain.models

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
        val imageUrl: String
    ): RecyclerViewDataModels()

    data class ItemService(
        val serviceDirection: String,
        val description: String,
        val imageUrl: String
    ): RecyclerViewDataModels()

    data class ItemPromoViewPager(
        val imageUrl: String
    ): RecyclerViewDataModels()

    data class ViewPager(
        val list: MutableList<RecyclerViewDataModels>
    ): RecyclerViewDataModels()
}

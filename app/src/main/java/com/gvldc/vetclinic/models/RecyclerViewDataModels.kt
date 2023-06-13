package com.gvldc.vetclinic.models

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
    ): RecyclerViewDataModels()

    data class ItemAppointment(
        val action: String,
        val imageUrl: String
    ): RecyclerViewDataModels()

    data class ItemClinics(
        val action: String,
        val imageUrl: String
    ): RecyclerViewDataModels()

    data class ItemVets(
        val action: String,
        val imageUrl: String
    ): RecyclerViewDataModels()

    data class ItemLogo(
        val imageUrl: String
    ): RecyclerViewDataModels()

    data class ItemNews(
        val title: String,
        val news: String,
        val imageUrl: String
    ): RecyclerViewDataModels()

    data class ItemNotification(
        val title: String,
        val message: String,
        val datetime: String
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

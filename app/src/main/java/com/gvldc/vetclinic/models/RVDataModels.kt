package com.gvldc.vetclinic.models

sealed class RVDataModels {

    data class ItemHeader(
        val title: String
    ) : RVDataModels()

    data class ItemHomeStart(
        val text: String,
        val imageUrlOne: String,
        val imageUrlTwo: String,
        val imageUrlThree: String,
        val imageUrlFour: String
    ) : RVDataModels()

    data class ItemPet(
        val name: String,
        val imageUrl: String,
        val birthDay: String,
        val breed: String,
        val species: String
    ) : RVDataModels()

    data class ItemAppointment(
        val action: String,
        val imageUrl: String
    ) : RVDataModels()

    data class ItemClinics(
        val action: String,
        val imageUrl: String
    ) : RVDataModels()

    data class ItemVets(
        val action: String,
        val imageUrl: String
    ) : RVDataModels()

    data class ItemLogo(
        val imageUrl: String
    ) : RVDataModels()

    data class ItemNews(
        val title: String,
        val news: String,
        val imageUrl: String
    ) : RVDataModels()

    data class ItemNotification(
        val title: String,
        val message: String,
        val datetime: String
    ) : RVDataModels()

    data class ItemService(
        val serviceDirection: String,
        val description: String,
        val imageUrl: String
    ) : RVDataModels()

    data class ParentModel(
        val list: MutableList<ChildModel>
    ) : RVDataModels()

    data class ChildModel(
        val imageUrl: String
    ) : RVDataModels()

    data class VetInfo(
        val imageUrl: String,
        val name: String,
        val species: String,
        val receptionFrom: String,
        val education: String
    ) : RVDataModels()

    data class ItemFutureAppointment(
        val serviceName: String,
        val vetName: String,
        val clinicName: String,
        val futureAppointmentDateTime: String,
        val date: String
    ) : RVDataModels()
}

package com.gvldc.vetclinic.data

import android.util.Log
import com.gvldc.vetclinic.models.RecyclerViewDataModels
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Date

class Repository {

    private val fireStoreRepository = FireStoreRepository()

    private val inputListHome = mutableListOf(
        RecyclerViewDataModels.ItemHeader(
            "Добро пожаловать"
        ),
        RecyclerViewDataModels.ItemHomeStart(
            "qwe",
            "https://docs.google.com/uc?id=1cefeiDRcJrSAB1zY5MZfrqpAI32MPVjy",
            "https://docs.google.com/uc?id=1DGOWCWQ763j2uL0PxLnH-AZjB2oaf7pa",
            "https://docs.google.com/uc?id=1IYeOUe4xP_uiiWjoPf_dEAwFTIEm-uEk",
            "https://docs.google.com/uc?id=1MzMeyCwOyjjwijP-v714L7gF8fFZqhWS"
        ),
        RecyclerViewDataModels.ItemAppointmentWDoctor(
            "Записаться к ветеринару",
            "https://animal-doc.ru/images/sobaken.png",
        ),
        RecyclerViewDataModels.ItemHomeStart(
            "qwe",
            "https://docs.google.com/uc?id=1cefeiDRcJrSAB1zY5MZfrqpAI32MPVjy",
            "https://docs.google.com/uc?id=1DGOWCWQ763j2uL0PxLnH-AZjB2oaf7pa",
            "https://docs.google.com/uc?id=1IYeOUe4xP_uiiWjoPf_dEAwFTIEm-uEk",
            "https://docs.google.com/uc?id=1MzMeyCwOyjjwijP-v714L7gF8fFZqhWS"
        ),
        RecyclerViewDataModels.ItemHomeStart(
            "qwe",
            "https://docs.google.com/uc?id=1cefeiDRcJrSAB1zY5MZfrqpAI32MPVjy",
            "https://docs.google.com/uc?id=1DGOWCWQ763j2uL0PxLnH-AZjB2oaf7pa",
            "https://docs.google.com/uc?id=1IYeOUe4xP_uiiWjoPf_dEAwFTIEm-uEk",
            "https://docs.google.com/uc?id=1MzMeyCwOyjjwijP-v714L7gF8fFZqhWS"
        ),
        RecyclerViewDataModels.ItemHeader(
            "Акции"
        ),
        RecyclerViewDataModels.ItemHomeStart(
            "qwe",
            "https://docs.google.com/uc?id=1cefeiDRcJrSAB1zY5MZfrqpAI32MPVjy",
            "https://docs.google.com/uc?id=1DGOWCWQ763j2uL0PxLnH-AZjB2oaf7pa",
            "https://docs.google.com/uc?id=1IYeOUe4xP_uiiWjoPf_dEAwFTIEm-uEk",
            "https://docs.google.com/uc?id=1MzMeyCwOyjjwijP-v714L7gF8fFZqhWS"
        ),
        RecyclerViewDataModels.ItemHeader(
            "Новости"
        ),
        RecyclerViewDataModels.ItemHomeStart(
            "qwe",
            "https://animal-doc.ru/images/sobaken.png",
            "https://docs.google.com/uc?id=1DGOWCWQ763j2uL0PxLnH-AZjB2oaf7pa",
            "https://docs.google.com/uc?id=1IYeOUe4xP_uiiWjoPf_dEAwFTIEm-uEk",
            "https://docs.google.com/uc?id=1MzMeyCwOyjjwijP-v714L7gF8fFZqhWS"
        ),
    )

/*    val inputListPets = mutableListOf(
        RecyclerViewDataModels.ItemHeader(
            "Мои питомцы "
        ),
        RecyclerViewDataModels.ItemPet(
            "Алекс",
            "https://pngimg.com/d/cat_PNG50426.png",
            Date(245),
            "Сиамская",
        ),
        RecyclerViewDataModels.ItemPet(
            "Шон",
            "https://www.freepnglogos.com/uploads/cat-png/cute-cat-images-download-7.png",
            Date(220),
            "Персидская",
        ),
        RecyclerViewDataModels.ItemPet(
            "Джерри",
            "https://pngfre.com/wp-content/uploads/transparent-cat-by-pngfre-75.png",
            Date(260),
            "Мейн-кун",
        ),
        RecyclerViewDataModels.ItemPet(
            "Дейзи",
            "https://www.freepnglogos.com/uploads/cat-png/cat-sitting-boarding-daycare-15.png",
            Date(290),
            "Корниш-рекс",
        ),
        RecyclerViewDataModels.ItemPet(
            "Ханна",
            "https://docs.google.com/uc?id=1jqKbtJUYtZE5SKuoGSxUHVHvJRymbAF_",
            Date(300),
            "Пикси-боб",
        )
    )*/

    private val inputListServices = mutableListOf(
        RecyclerViewDataModels.ItemHeader(
            "Выберите услугу"
        ),
        RecyclerViewDataModels.ItemService(
            "Первичный осмотр",
            "В ходе обследования врач выполняет осмотр. На основании полученных данных проконсультирует владельца о необходимых в дальнейшем действиях.",
            "https://zoostatus.ru/upload/zoostatus-articles-foto/pervichniy_osmotr/pervichnii_osmotr_1.jpg"
        )
    )

    suspend fun getPetsData(): MutableList<RecyclerViewDataModels> {
        val pets = fireStoreRepository.getPets()
        val inputListPets = mutableListOf<RecyclerViewDataModels>()
        inputListPets.add(RecyclerViewDataModels.ItemHeader("Мои питомцы "))
        inputListPets.addAll(pets)
        Log.d("TEST", "test")
        return inputListPets
    }

    fun getHomeData(): MutableList<RecyclerViewDataModels>{
        return inputListHome
    }

    fun getServicesData(): MutableList<RecyclerViewDataModels>{
        return inputListServices
    }
}
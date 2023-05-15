package com.example.attempseven.data

import com.example.attempseven.models.RecyclerViewDataModels
import java.util.Date

class Repository {

    fun getPetsData(): MutableList<RecyclerViewDataModels> {
        return inputListPets
    }

    fun getHomeData(): MutableList<RecyclerViewDataModels>{
        return inputListHome
    }

    fun getServicesData(): MutableList<RecyclerViewDataModels>{
        return inputListServices
    }

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

    val inputListPets = mutableListOf(
        RecyclerViewDataModels.ItemHeader(
            "Мои питомцы "
        ),
        RecyclerViewDataModels.ItemPet(
            "Алекс",
            "https://pngimg.com/d/cat_PNG50426.png",
            Date(245),
            "Сиамская",
            "1"
        ),
        RecyclerViewDataModels.ItemPet(
            "Шон",
            "https://www.freepnglogos.com/uploads/cat-png/cute-cat-images-download-7.png",
            Date(220),
            "Персидская",
            "2"
        ),
        RecyclerViewDataModels.ItemPet(
            "Джерри",
            "https://pngfre.com/wp-content/uploads/transparent-cat-by-pngfre-75.png",
            Date(260),
            "Мейн-кун",
            "3"
        ),
        RecyclerViewDataModels.ItemPet(
            "Дейзи",
            "https://www.freepnglogos.com/uploads/cat-png/cat-sitting-boarding-daycare-15.png",
            Date(290),
            "Корниш-рекс",
            "4"
        ),
        RecyclerViewDataModels.ItemPet(
            "Ханна",
            "https://docs.google.com/uc?id=1jqKbtJUYtZE5SKuoGSxUHVHvJRymbAF_",
            Date(300),
            "Пикси-боб",
            "5"
        )
    )

    val inputListServices = mutableListOf(
        RecyclerViewDataModels.ItemHeader(
            "Выберите услугу"
        ),
        RecyclerViewDataModels.ItemService(
            "Первичный осмотр",
            "В ходе обследования врач выполняет осмотр. На основании полученных данных проконсультирует владельца о необходимых в дальнейшем действиях.",
            "https://docs.google.com/uc?id=1jqKbtJUYtZE5SKuoGSxUHVHvJRymbAF_"
        )
    )
}
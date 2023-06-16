package com.gvldc.vetclinic.data

import android.util.Log
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.gvldc.vetclinic.models.RecyclerViewDataModels
import com.gvldc.vetclinic.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.util.Date

class Repository {

    private val fireStoreDb = FirebaseFirestore.getInstance()

    private suspend fun getPets(userId: String): List<RecyclerViewDataModels.ItemPet> =
        withContext(Dispatchers.IO) {
            val petsCollection = fireStoreDb.collection("Users").document(userId).collection("Pets")
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
                Log.d("tag", "Питомцы грузятся")
            }
            petsList
        }

/*    fun getUserData(userId: String): User {
        val userDocument = FirebaseFirestore.getInstance().collection("Users").document(userId).get()
        val snapshot = userDocument.result

        val name = snapshot.getString("name") ?: ""
        val email = snapshot.getString("email") ?: ""
        val phone = snapshot.getString("phone") ?: ""

        return User(name, email, phone)
    }*/





    fun registerUser(userId: String, name: String, email: String, phone: String) {
        val userCollection = fireStoreDb.collection("Users").document(userId)

        val userData = hashMapOf(
            "name" to name,
            "email" to email,
            "phone" to phone
        )

        userCollection.set(userData)
            .addOnSuccessListener {
                Log.d("TAG", "Пользователь успешно зарегистрирован с ID: $userId")
            }
            .addOnFailureListener { e ->
                Log.d("TAG", "Ошибка при регистрации пользователя: $e")
            }
    }

    fun createPet(
        userId: String,
        petId: String,
        name: String,
        birthday: Timestamp,
        imageUrl: String,
        species: String,
        breed: String
    ) {
        val userPetsCollection =
            fireStoreDb.collection("Users").document(userId).collection("Pets").document(petId)

        val petData = hashMapOf(
            "name" to name,
            "birthday" to birthday,
            "imageUrl" to imageUrl,
            "species" to species,
            "breed" to breed
        )

        userPetsCollection.set(petData)
            .addOnSuccessListener {
                Log.d("TAG", "Питомец успешно создан с ID: $petId")
            }
            .addOnFailureListener { e ->
                Log.d("TAG", "Ошибка при создании питомца: $e")
            }
    }

    fun createPetWithoutImage(
        userId: String,
        petId: String,
        name: String,
        birthday: Timestamp,
        species: String,
        breed: String
    ) {
        val userPetsCollection =
            fireStoreDb.collection("Users").document(userId).collection("Pets").document(petId)

        val petData = hashMapOf(
            "name" to name,
            "birthday" to birthday,
            "species" to species,
            "breed" to breed
        )

        userPetsCollection.set(petData)
            .addOnSuccessListener {
                Log.d("TAG", "Питомец успешно создан с ID: $petId")
            }
            .addOnFailureListener { e ->
                Log.d("TAG", "Ошибка при создании питомца: $e")
            }
    }

    private val inputListHome = mutableListOf(
        RecyclerViewDataModels.ItemHeader(
            "Добро пожаловать"
        ),
        RecyclerViewDataModels.ItemLogo(
            "https://docs.google.com/uc?id=1WKK29RnGQk0KMFsKgm-MbgWuy5H51s3R"
        ),
        RecyclerViewDataModels.ItemAppointment(
            "Записаться к ветеринару",
            "https://docs.google.com/uc?id=1amDUBN2MhlmdLTvGGRtm5NfWkuO3pFdb",
        ),
        RecyclerViewDataModels.ItemClinics(
            "Наши клиники",
            "https://docs.google.com/uc?id=177g6wg-XTKWnASGEk60odgd_ZddK3_9G",
        ),
        RecyclerViewDataModels.ItemVets(
            "Наши ветеринары",
            "https://docs.google.com/uc?id=1NYIWi98c7bRn6ZcvDBwHo7BpQyaOHZ-F",
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
        RecyclerViewDataModels.ItemNews(
            title = "Котоновость",
            news = "В Санкт-Петербурге в предстоящие выходные пройдет праздник, посвященный местным кошкам. На несколько часов Конногвардейский бульвар переименуют в Котогвардейский, сообщает пресс-служба района.\n" +
                    "\n" +
                    "Мероприятие состоится 3 июня с 12:00 до 16:00 часов, информирует телеканал «\u200E78».\u200E В этот день бульвар превратится в обитель кошачьих. Для гостей праздника подготовили квесты, конкурсы, мастер-классы, концерты и котопарад.\n" +
                    "\n" +
                    "Большой городской праздник пройдет в Петербурге уже в восьмой раз. Его успели полюбить горожане и гости Северной столицы всех возрастов.",
            imageUrl = "https://koshka.top/uploads/posts/2021-11/1637942708_6-koshka-top-p-sidyashchei-koshki-6.jpg"
        ),
        RecyclerViewDataModels.ItemNews(
            title = "Праздник кошек",
            news = "В Санкт-Петербурге в предстоящие выходные пройдет праздник, посвященный местным кошкам. На несколько часов Конногвардейский бульвар переименуют в Котогвардейский, сообщает пресс-служба района.\n" +
                    "\n" +
                    "Мероприятие состоится 3 июня с 12:00 до 16:00 часов, информирует телеканал «\u200E78».\u200E В этот день бульвар превратится в обитель кошачьих. Для гостей праздника подготовили квесты, конкурсы, мастер-классы, концерты и котопарад.\n" +
                    "\n" +
                    "Большой городской праздник пройдет в Петербурге уже в восьмой раз. Его успели полюбить горожане и гости Северной столицы всех возрастов.",
            imageUrl = "https://koshka.top/uploads/posts/2021-11/1637942708_6-koshka-top-p-sidyashchei-koshki-6.jpg"
        )
    )

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

    private val inputListNotifications = mutableListOf(
        RecyclerViewDataModels.ItemHeader(
            "Уведомления"
        ),
        RecyclerViewDataModels.ItemNotification(
            "Акция",
            "С 15 по 20 апреля во всех клиниках ГВЛДЦ действует скидка на стрижку когтей 20%",
            "19:41 14 апреля"
        ),
        RecyclerViewDataModels.ItemNotification(
            "Вакцинация",
            "Завтра в 14:00, вы записаны на вакцинацию Майкла",
            "12:00 12 апреля"
        ),
        RecyclerViewDataModels.ItemNotification(
            "Запись к ветеринару",
            "2 апреля в 12:10, вы записаны на стрижку когтей Майкла",
            "10:00 1 апреля"
        ),
    )

    suspend fun getPetsData(userId: String): MutableList<RecyclerViewDataModels> {
        val pets = getPets(userId)
        val inputListPets = mutableListOf<RecyclerViewDataModels>()
        inputListPets.add(RecyclerViewDataModels.ItemHeader("Мои питомцы"))
        inputListPets.addAll(pets)
        Log.d("TEST", "test")
        return inputListPets
    }

    fun getHomeData(): MutableList<RecyclerViewDataModels> {
        return inputListHome
    }

    fun getNotificationsData(): MutableList<RecyclerViewDataModels> {
        return inputListNotifications
    }

    fun getServicesData(): MutableList<RecyclerViewDataModels> {
        return inputListServices
    }
}
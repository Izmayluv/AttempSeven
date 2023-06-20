package com.gvldc.vetclinic.data

import android.util.Log
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.gvldc.vetclinic.models.RVDataModels
import com.gvldc.vetclinic.models.User
import com.gvldc.vetclinic.utils.MyUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class Repository {

    private val fireStoreDb = FirebaseFirestore.getInstance()

    private suspend fun getPets(userId: String): List<RVDataModels.ItemPet> =
        withContext(Dispatchers.IO) {
            val petsCollection = fireStoreDb.collection("Users")
                .document(userId).collection("Pets")
            val petsSnapshot = petsCollection.get().await()

            val petsList = mutableListOf<RVDataModels.ItemPet>()
            for (document in petsSnapshot) {
                val name = document.getString("name") ?: ""
                val imageUrl = document.getString("imageUrl") ?: ""
                val breed = document.getString("breed") ?: ""
                val species = document.getString("species") ?: ""
                val birthday = document.getTimestamp("birthday") ?: ""
                val pet = RVDataModels.ItemPet(
                    name, imageUrl, MyUtils.timestampToString(
                        birthday as Timestamp, "dd.MM.yyyy"
                    ), breed, species
                )
                petsList.add(pet)
                Log.d("tag", "Питомцы грузятся")
            }
            petsList
        }

    suspend fun getPetsData(userId: String): MutableList<RVDataModels> {
        val pets = getPets(userId)
        val inputListPets = mutableListOf<RVDataModels>()
        inputListPets.add(RVDataModels.ItemHeader("Мои питомцы"))
        inputListPets.addAll(pets)
        Log.d("TEST", "test")
        return inputListPets
    }

    fun getUserData(userId: String, callback: (User?) -> Unit) {
        val userDocument = fireStoreDb.collection("Users").document(userId)

        userDocument.get().addOnSuccessListener { documentSnapshot ->
            if (documentSnapshot.exists()) {
                val name = documentSnapshot.getString("name")
                val email = documentSnapshot.getString("email")
                val phone = documentSnapshot.getString("phone")

                if (name.isNullOrEmpty() || email.isNullOrEmpty() || phone.isNullOrEmpty()) {
                    callback(null)
                } else {
                    val user = User(name, email, phone)
                    callback(user)
                }
            } else {
                // Документ пользователя не существует
                callback(null)
            }
        }.addOnFailureListener {
            // Ошибка при получении данных пользователя
            callback(null)
        }
    }

    fun getNotificationsData(userId: String, callback: (MutableList<RVDataModels>) -> Unit) {
        val userNotificationsCollection =
            fireStoreDb.collection("Users").document(userId)
                .collection("Notifications")

        userNotificationsCollection.orderBy("time", Query.Direction.DESCENDING).get()
            .addOnSuccessListener { querySnapshot ->

                val notificationsList = mutableListOf<RVDataModels>(
                    RVDataModels.ItemHeader(
                        "Уведомления"
                    )
                )

                for (document in querySnapshot.documents) {
                    val title = document.getString("title")
                    val message = document.getString("message")
                    val time = document.getTimestamp("time")

                    if (title != null && message != null && time != null) {
                        val notification = RVDataModels.ItemNotification(
                            title, message, MyUtils.timestampToString(time, "HH:mm dd MMMM")
                        )
                        notificationsList.add(notification)
                    }
                }
                callback(notificationsList)
            }.addOnFailureListener {
                // Ошибка при получении уведомлений пользователя
                callback(mutableListOf<RVDataModels>())
            }
    }


    fun registerUser(userId: String, name: String, email: String, phone: String) {
        val userCollection = fireStoreDb.collection("Users").document(userId)

        val userData = hashMapOf(
            "name" to name, "email" to email, "phone" to phone
        )

        userCollection.set(userData).addOnSuccessListener {
            Log.d("TAG", "Пользователь успешно зарегистрирован с ID: $userId")
        }.addOnFailureListener { e ->
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

        userPetsCollection.set(petData).addOnSuccessListener {
            Log.d("TAG", "Питомец успешно создан с ID: $petId")
        }.addOnFailureListener { e ->
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
            "name" to name, "birthday" to birthday, "species" to species, "breed" to breed
        )

        userPetsCollection.set(petData).addOnSuccessListener {
            Log.d("TAG", "Питомец успешно создан с ID: $petId")
        }.addOnFailureListener { e ->
            Log.d("TAG", "Ошибка при создании питомца: $e")
        }
    }

    fun createNotification(
        userId: String, notificationId: String, title: String, time: Timestamp, message: String
    ) {
        val userNotificationCollection =
            fireStoreDb.collection("Users").document(userId).collection("Notifications")
                .document(notificationId)

        val notificationData = hashMapOf(
            "title" to title, "message" to message, "time" to time
        )

        userNotificationCollection.set(notificationData).addOnSuccessListener {
            Log.d("TAG", "Питомец успешно создан с ID: $notificationId")
        }.addOnFailureListener { e ->
            Log.d("TAG", "Ошибка при создании питомца: $e")
        }
    }

    private val promoList = mutableListOf(
        RVDataModels.ChildModel(
            "https://animal-doc.ru/upload/iblock/6ca/6caa5d0e760b6cac887448249a69a934.png"
        ),
        RVDataModels.ChildModel(
            "https://docs.google.com/uc?id=1S4JRvqwT_Y-94laZZ4Ua7fj-IL8JE0-i"
        ),
        RVDataModels.ChildModel(
            "https://docs.google.com/uc?id=1GFSocBDLpj4FqRDezmA46UbpMyMkcyVi"
        ),
        RVDataModels.ChildModel(
            "https://animal-doc.ru/upload/iblock/6ca/6caa5d0e760b6cac887448249a69a934.png"
        ),
        RVDataModels.ChildModel(
            "https://docs.google.com/uc?id=1GFSocBDLpj4FqRDezmA46UbpMyMkcyVi"
        ),
    )

    private val inputListHome = mutableListOf(
        RVDataModels.ItemHeader(
            "Добро пожаловать"
        ), RVDataModels.ItemLogo(
            "https://docs.google.com/uc?id=1WKK29RnGQk0KMFsKgm-MbgWuy5H51s3R"
        ), RVDataModels.ItemAppointment(
            "Записаться к ветеринару",
            "https://docs.google.com/uc?id=1amDUBN2MhlmdLTvGGRtm5NfWkuO3pFdb",
        ), RVDataModels.ItemClinics(
            "Наши клиники",
            "https://docs.google.com/uc?id=177g6wg-XTKWnASGEk60odgd_ZddK3_9G",
        ), RVDataModels.ItemVets(
            "Наши ветеринары",
            "https://docs.google.com/uc?id=1NYIWi98c7bRn6ZcvDBwHo7BpQyaOHZ-F",
        ), RVDataModels.ItemHeader(
            "Акции"
        ), RVDataModels.ParentModel(
            promoList
        ), RVDataModels.ItemHeader(
            "Новости"
        ), RVDataModels.ItemNews(
            title = "Праздник кошек",
            news = "    В Санкт-Петербурге в предстоящие выходные пройдет праздник, посвященный местным кошкам. На несколько часов Конногвардейский бульвар переименуют в Котогвардейский, сообщает пресс-служба района.\n" + "   Мероприятие состоится 3 июня с 12:00 до 16:00 часов, информирует телеканал «\u200E78».\u200E В этот день бульвар превратится в обитель кошачьих. Для гостей праздника подготовили квесты, конкурсы, мастер-классы, концерты и котопарад.\n" + "   Большой городской праздник пройдет в Петербурге уже в восьмой раз. Его успели полюбить горожане и гости Северной столицы всех возрастов.",
            imageUrl = "https://koshka.top/uploads/posts/2021-11/1637942708_6-koshka-top-p-sidyashchei-koshki-6.jpg"
        ), RVDataModels.ItemNews(
            title = "Праздник кошек",
            news = "    В Санкт-Петербурге в предстоящие выходные пройдет праздник, посвященный местным кошкам. На несколько часов Конногвардейский бульвар переименуют в Котогвардейский, сообщает пресс-служба района.\n" + "   Мероприятие состоится 3 июня с 12:00 до 16:00 часов, информирует телеканал «\u200E78».\u200E В этот день бульвар превратится в обитель кошачьих. Для гостей праздника подготовили квесты, конкурсы, мастер-классы, концерты и котопарад.\n" + "   Большой городской праздник пройдет в Петербурге уже в восьмой раз. Его успели полюбить горожане и гости Северной столицы всех возрастов.",
            imageUrl = "https://kot-pes.com/wp-content/uploads/2018/10/post_5bc3b9295ffdc.jpg"
        )
    )

    private val inputListServices = mutableListOf(
        RVDataModels.ItemHeader(
            "Выберите услугу"
        ), RVDataModels.ItemService(
            "Первичный осмотр",
            "В ходе обследования врач выполняет осмотр. На основании полученных данных проконсультирует владельца о необходимых в дальнейшем действиях.",
            "https://zoostatus.ru/upload/zoostatus-articles-foto/pervichniy_osmotr/pervichnii_osmotr_1.jpg"
        )
    )

    private val inputListVetsInfo = mutableListOf(
        RVDataModels.VetInfo(
            "https://animal-doc.ru/upload/iblock/262/2621f1b4cdd9c5a9beeda778d1b00fc5.png",
            "Балашова Татьяна Алексеевна",
            "Терапевт, специалист по интенсивной терапии, эндокринолог",
            "В клинике на Турку и Будапештской",
            "Окончила Санкт-Петербургскую государственную академию ветеринарной медицины в 2010г. За плечами Татьяны Алексеевны более 7 лет практической деятельности, внимательного отношения к пациентам и помощи бездомным животным"
        ),
        RVDataModels.VetInfo(
            "https://animal-doc.ru/upload/iblock/b8b/b8b05ac3b3a24b42e4ccbaeecd9e1065.png",
            "Фомин Александр Александрович",
            "Невролог, хирург, ортопед",
            "По записи в клинике на Петергофском шоссе 47к2 и на Турку д.11 к.2",
            "Бологовский аграрный колледж, диплом с отличием - Санкт-Петербургская Государственная Академия Ветеринарной Медицины (СПбГАВМ), диплом с отличием"
        ),
        RVDataModels.VetInfo(
            "https://animal-doc.ru/upload/iblock/f7a/f7ae70c4e2104779779bfd6b67c97199.png",
            "Ипатов Дмитрий Сергеевич",
            "Терапевт, хирург, специалист УЗИ, рентгенолог",
            "В клинике на Петергофском шоссе",
            "Окончил Санкт-Петербургскую государственную академию ветеринарной медицины в 2003г. С 2015 года Дмитрий Сергеевич работает в сети клиник ГВЛДЦ№1. За его плечами более 15 лет опыта работы. Дмитрий Сергеевич работает с мелкими домашними животными (кошки, собаки, хорьки), интересуется фелинологией (наука о кошках)"
        ),

        )


    fun getHomeData(): MutableList<RVDataModels> {
        return inputListHome
    }

    fun getServicesData(): MutableList<RVDataModels> {
        return inputListServices
    }

    fun getVetsInfo(): MutableList<RVDataModels.VetInfo> {
        return inputListVetsInfo
    }
}
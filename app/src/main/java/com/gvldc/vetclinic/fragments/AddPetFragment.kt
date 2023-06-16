package com.gvldc.vetclinic.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.gvldc.vetclinic.R
import com.gvldc.vetclinic.activities.MainActivity
import com.gvldc.vetclinic.databinding.FragmentAddPetBinding
import com.gvldc.vetclinic.utils.MyUtils
import com.gvldc.vetclinic.viewmodels.ViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class AddPetFragment : Fragment(R.layout.fragment_add_pet) {

    private lateinit var bindingFragmentAddPet: FragmentAddPetBinding
    private var fileUri: Uri? = null

    private val viewModel by activityViewModels<ViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingFragmentAddPet = FragmentAddPetBinding.inflate(layoutInflater)

        bindingFragmentAddPet.cardViewImage.setOnClickListener {
            openGallery()
        }

        bindingFragmentAddPet.buttonSave.setOnClickListener {

            val storageRef = Firebase.storage.reference

            val currentUserId = FirebaseAuth.getInstance().currentUser?.uid.toString()
            val newPetId = MyUtils.generateUUID()

            val imageName = "images/image_uid${currentUserId}_datetime${
                SimpleDateFormat("yyyyMMdd:HHmmss", Locale.getDefault())
                    .format(Date())
            }_pid${newPetId}.jpg"

            val imagesRef = storageRef.child(imageName)

            val namePet: String
            val birthdayPet: String
            val speciesPet: String
            val breedPet: String

            val pattern = "dd.MM.yyyy"

            bindingFragmentAddPet.apply {
                namePet = editTextPetName.text.toString().trim()
                birthdayPet = editTextPetDate.text.toString().trim()
                speciesPet = editTextPetSpecies.text.toString().trim()
                breedPet = editTextPetBreed.text.toString().trim()
            }

            if (fileUri != null) {
                val uploadTask = imagesRef.putFile(fileUri!!)

                /*                uploadTask.addOnProgressListener { taskSnapshot ->
                                    val progress = (100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount)
                                    // Обновляем прогресс загрузки
                                }*/

                // Добавляем слушатель для получения ссылки на загруженный файл
                uploadTask.continueWithTask { task ->
                    if (!task.isSuccessful) {
                        task.exception?.let {
                            // Обработка ошибки при загрузке файла
                        }
                    }
                    // Получаем ссылку на загруженный файл
                    imagesRef.downloadUrl
                }.addOnCompleteListener { task ->
                    if (task.isSuccessful) {

                        val downloadUri = task.result
                        val imageUrl = downloadUri.toString()

                        //запись в бд питомца с картинкой

                        viewModel.createPet(
                            currentUserId,
                            newPetId,
                            namePet,
                            MyUtils.stringToTimestamp(birthdayPet, pattern)!!,
                            imageUrl,
                            speciesPet,
                            breedPet
                        )

                    } else {
                        // Обработка ошибки при получении ссылки на загруженный файл
                    }
                }
            } else {
                //запись питомца в бд без картинки
                viewModel.createPetWithoutImage(
                    currentUserId,
                    newPetId,
                    namePet,
                    MyUtils.stringToTimestamp(birthdayPet, pattern)!!,
                    speciesPet,
                    breedPet
                )
            }
            //переход обратно к списку питомцев
            navigateToPetsFragment()
            Toast.makeText(
                bindingFragmentAddPet.root.context,
                "$namePet успешно добавлен!",
                Toast.LENGTH_SHORT
            ).show()
        }

        return bindingFragmentAddPet.root
    }

    private val galleryLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val selectedImageUri: Uri? = data?.data

                if (selectedImageUri != null) {
                    bindingFragmentAddPet.iv.setImageURI(selectedImageUri)
                    fileUri = selectedImageUri
                }
            }
        }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        val options = ActivityOptionsCompat.makeBasic()
        galleryLauncher.launch(intent, options)
    }

    private fun navigateToPetsFragment() {
        val petsFragment = PetsFragment()

        val mainActivity = requireActivity() as MainActivity
        mainActivity.supportFragmentManager.beginTransaction()
            .replace(R.id.flFragment, petsFragment)
            .commit()
    }

}
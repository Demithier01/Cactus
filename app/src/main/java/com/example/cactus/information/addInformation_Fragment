package com.example.cactus.information

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.cactus.databinding.FragmentAddInformentsBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class AddInformentsFragment : Fragment() {
    private lateinit var binding : FragmentAddInformentsBinding
    private lateinit var firebaseRef: DatabaseReference
    private lateinit var storageRef: StorageReference
    private var uri : Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddInformentsBinding.inflate(inflater,container,false)
        firebaseRef = FirebaseDatabase.getInstance().getReference("information")
        storageRef = FirebaseStorage.getInstance().getReference("images")

        binding.btnData.setOnClickListener {
            saveData()
        }
        val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()){
            binding.imgAdd.setImageURI(it)
            if (it != null){
                uri = it
            }
        }
        binding.bntPickImg.setOnClickListener{
            pickImage.launch("image/*")
        }
        return binding.root
    }
    private fun saveData() {
        val name = binding.editName.text.toString().trim()
        val des = binding.editDes.text.toString().trim()

        if (name.isEmpty()) {
            binding.editName.error = "Enter is Name"
            return
        }
        if (des.isEmpty()) {
            binding.editDes.error = "Enter is Description"
            return
        }
        //บันทึกข้อมูล

        val informentId = firebaseRef.push().key!!
        var informents : Informents
        uri?.let{
            storageRef.child(informentId).putFile(it)
                .addOnSuccessListener { task ->
                    task.metadata!!.reference!!.downloadUrl
                        .addOnSuccessListener { url ->
                            Toast.makeText(context,"Image Success", Toast.LENGTH_SHORT).show()
                            val imgUrl = url.toString()
                            informents = Informents(informentId,name,des,imgUrl)

                            firebaseRef.child(informentId).setValue(informents)
                                .addOnCompleteListener {
                                    Toast.makeText(context, "Data stored Success!!", Toast.LENGTH_SHORT).show()
                                }
                                .addOnFailureListener {
                                    Toast.makeText(context, "Error: ${it.message}", Toast.LENGTH_SHORT).show()
                                }
                        }
                }
        }
    }
}



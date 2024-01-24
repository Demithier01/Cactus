package com.example.cactus.restApi

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cactus.databinding.FragmentRetrofitBinding
import com.example.cactus.restApi.model.SpeciesItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitFragment : Fragment() {
    private lateinit var binding: FragmentRetrofitBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentRetrofitBinding.inflate(inflater, container, false)
        binding.rvRetrofit.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this.context)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gatData()
        updateData()
        createData()
        deleteData()
    }

    private fun gatData(){
        val restService = RetrofitInstance.getRetrofitInstance().create(SpeciesService::class.java)
        val getCall: Call<Map<String, SpeciesItem>> = restService.getSpecies()
        getCall.enqueue(object : Callback<Map<String, SpeciesItem>> {
            override fun onResponse(call: Call<Map<String, SpeciesItem>>, response: Response<Map<String, SpeciesItem>>) {
                if (response.isSuccessful) {
                    val list = ArrayList<SpeciesItem>()
                    val species = response.body()
                    if (species != null) {
                        for ((_, item) in species.entries) {
                            list.add(item)
                        }
                    }
                    val rvAdapter = RetrofitAdapter(list)
                    binding.rvRetrofit.adapter = rvAdapter
                } else {
                    Log.e("RetrofitFragment", "Error getting album: ${response.code()}")
                    Toast.makeText(context, "Error: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Map<String, SpeciesItem>>, t: Throwable) {
                handleFailure(t)
            }
        })
    }

    private fun updateData(){
        // Update data
//        val restService = RetrofitInstance.getRetrofitInstance().create(SpeciesService::class.java)
//        val update = SpeciesItem(id = 1, name = "Echinopsis Calochlora", title = "เป็นไม้ลูกเต่าที่มีกำเนิดมาจากภูเขาและที่ดินที่ร่วนปนทรายในภูมิภาคตะวันตกของอาร์เจนตินา มีลำต้นทรงกระบอกและทรงกระบอกแบบกระจก สูงได้ถึง 6-12 นิ้ว (15-30 ซม.) และมีเส้นผ่านศูนย์กลางที่ชัดเจน มีดอกสวยงามที่มีขนาดใหญ่สีขาวหรือสีเหลืองอ่อน ดอกจะบานตอนกลางคืน")
//        val updateCall: Call<SpeciesItem> = restService.updateSpecies(1, update)
//        updateCall.enqueue(object : Callback<SpeciesItem> {
//            override fun onResponse(call: Call<SpeciesItem>, response: Response<SpeciesItem>) {
//                if (response.isSuccessful) {
//                    val updatedSpecies = response.body()
//                    if (updatedSpecies != null) {
//                        Toast.makeText(context, "Update data Success!!", Toast.LENGTH_SHORT).show()
//                    }
//                } else {
//                    Toast.makeText(context, "error updating album", Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onFailure(call: Call<SpeciesItem>, t: Throwable) {
//                handleFailure(t)
//            }
//        })
    }
    private fun createData(){
//        val restService = RetrofitInstance.getRetrofitInstance().create(SpeciesService::class.java)
//        val create = SpeciesItem(id = 10,"laRoche","In this video, we will learn how we can update and delete data from the backend server using the retrofit library.")
//        val postCall: Call<SpeciesItem> = restService.createSpecies(create)
//        postCall.enqueue(object : Callback<SpeciesItem> {
//            override fun onResponse(call: Call<SpeciesItem>, response: Response<SpeciesItem>) {
//                if (response.isSuccessful) {
//                    val responseItem = response.body()
//
//                } else {
//                    Log.e("RetrofitFragment", "Error posting album: ${response.code()}")
//                    Toast.makeText(context, "Error: ${response.code()}", Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onFailure(call: Call<SpeciesItem>, t: Throwable) {
//                handleFailure(t)
//            }
//        })
    }
    private fun deleteData(){
//        val restService = RetrofitInstance.getRetrofitInstance().create(SpeciesService::class.java)
//        val deleteCall: Call<SpeciesItem> = restService.deleteSpecies("-Nour2eAaG_AaH_ujW5m")
//        deleteCall.enqueue(object : Callback<SpeciesItem> {
//            override fun onResponse(call: Call<SpeciesItem>, response: Response<SpeciesItem>) {
//                if (response.isSuccessful) {
//                    val deleteItem = response.body()
//                    if (deleteItem != null) {
//                        Toast.makeText(context, "Delete data Success!!", Toast.LENGTH_SHORT).show()
//                    }
//                } else {
//                    Toast.makeText(context, "error updating album", Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onFailure(call: Call<SpeciesItem>, t: Throwable) {
//                handleFailure(t)
//            }
//        })
    }


    private fun handleFailure(t: Throwable) {

        val failureMessage = "Failure: ${t.message}"

        Log.e("RetrofitFragment", failureMessage)
    }
}




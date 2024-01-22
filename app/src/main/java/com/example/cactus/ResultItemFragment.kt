package com.example.cactus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cactus.databinding.FragmentResultItemBinding

class ResultItemFragment : Fragment() {

    private lateinit var binding: FragmentResultItemBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // ใช้ Data Binding เพื่อ Inflate layout ให้กับ Fragment
        binding = FragmentResultItemBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ตรวจสอบข้อมูลที่ส่งมาจาก HomePageFragment
        val apiMessage = arguments?.getString("apiMessage")
        if (!apiMessage.isNullOrEmpty()) {
            binding.tvButton.text = apiMessage
        } else {
            binding.tvButton.text = "No data available"
        }

        //คำสั่งเมื่อคลิ๊กปุ่ม back
        binding.back.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
    }

}

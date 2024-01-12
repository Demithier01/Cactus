package com.example.cactus.homePage

import CactusAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cactus.R
import com.example.cactus.databinding.FragmentHomePageBinding
import java.util.Locale
class HomePageFragment : Fragment() {

    private lateinit var binding: FragmentHomePageBinding
    private lateinit var cactusAdapter: CactusAdapter
    private val navController by lazy { findNavController() }
    private lateinit var viewModel: HomepageViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // ใช้ Data Binding เพื่อ Inflate layout ให้กับ Fragment
        binding = FragmentHomePageBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[HomepageViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.setHasFixedSize(true)

        // ตรวจสอบการเปลี่ยนแปลงใน cactusList ผ่าน ViewModel
        viewModel.cactusList.observe(viewLifecycleOwner, Observer { cactusList ->
            // สร้าง Adapter ใหม่จาก cactusList และกำหนดให้ RecyclerView ใช้ Adapter
            cactusAdapter = CactusAdapter(cactusList)
            binding.recyclerView.adapter = cactusAdapter
            // กำหนดการทำงานเมื่อคลิกแต่ละ card
            cactusAdapter.onItemClick = { cactus ->
                // นำทางไปยัง DetailFragment เมื่อทำการคลิก
                val bundle = Bundle()
                bundle.putParcelable("cactus", cactus)
                navController.navigate(R.id.action_homePageFragment_to_detailFragment, bundle)
            }
        })

        binding.searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // เรียกใช้ฟังก์ชัน filterList เพื่อกรองรายการ cactusList
                filterList(newText)
                return true
            }
        })
    }
    /// ฟังก์ชันสำหรับกรองรายการ cactusList ตามข้อความที่กรอก
    private fun filterList(query: String?) {
        viewModel.cactusList.observe(viewLifecycleOwner, Observer { cactuses ->
            // กรองรายการ cactuses ตาม query และอัปเดต RecyclerView ด้วยรายการที่กรองแล้ว
            val filteredList = cactuses.filter { it.name.lowercase(Locale.ROOT).contains(query?.lowercase(Locale.ROOT) ?: "") }
            // แสดง Toast หากไม่พบข้อมูล หรืออัปเดต RecyclerView ด้วยรายการที่กรองแล้ว
            if (filteredList.isEmpty()) {
                Toast.makeText(requireContext(), "No Data found", Toast.LENGTH_SHORT).show()
            } else {
                cactusAdapter.setFilteredList(filteredList)
            }
        })
    }

}


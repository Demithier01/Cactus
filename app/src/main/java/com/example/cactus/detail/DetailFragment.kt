package com.example.cactus.detail

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.cactus.databinding.FragmentDetailBinding
import com.example.cactus.model.Cactus
class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[DetailViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // สร้างตัวแปร viewModel สำหรับเข้าถึง DetailViewModel
        viewModel.selectedCactus.observe(viewLifecycleOwner, Observer { cactus ->
            cactus?.let {

                Glide.with(this)
                    .load(cactus.image)
                    .into(binding.detailImageView)

                binding.detailTextView.text = cactus.name
                binding.detailTextView2.text = cactus.description

                binding.clickLink.setOnClickListener {
                    val browserIntent = Intent(Intent.ACTION_VIEW, cactus.link.toUri())
                    startActivity(browserIntent)
                }
            }
        })

        // รับข้อมูล Cactus ผ่าน Argument และส่งไปยัง DetailViewModel
        val cactus: Cactus? = arguments?.getParcelable("cactus")
        cactus?.let { viewModel.setCactus(cactus) }

        ///คำสั่งเมื่อคลิ๊กปุ่ม back
        binding.icBack.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
    }
}

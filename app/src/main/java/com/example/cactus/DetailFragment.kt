package com.example.cactus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.cactus.databinding.FragmentDetailBinding
import com.example.cactus.model.SpeciesItem

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       binding = FragmentDetailBinding.inflate(inflater,container,false)
       return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val speciesItem = it.getParcelable<SpeciesItem>("id")
            speciesItem?.let { displaySpeciesDetails(it) }
        }

        binding.btnBack.setOnClickListener {
                activity?.supportFragmentManager?.popBackStack()
        }
    }
    private fun displaySpeciesDetails(speciesItem: SpeciesItem) {
        binding.apply {
            detailName.text = speciesItem.name
            detailTitle.text = speciesItem.title
            Glide.with(detailImg.context)
                .load(speciesItem.image)
                .placeholder(R.drawable.image)
                .error(R.drawable.ic_img_error)
                .into(detailImg)
        }
    }
}

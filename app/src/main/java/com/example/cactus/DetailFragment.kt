package com.example.cactus

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.example.cactus.databinding.FragmentDetailBinding
import com.example.cactus.model.Cactus

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cactus: Cactus? = arguments?.getParcelable("cactus")
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

        binding.icBack.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
    }
//    companion object {
//        fun newInstance(cactus: Cactus): DetailFragment {
//            val fragment = DetailFragment()
//            val args = Bundle()
//            args.putParcelable("cactus", cactus)
//            fragment.arguments = args
//            return fragment
//        }
//    }


}

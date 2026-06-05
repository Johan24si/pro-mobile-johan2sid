package com.example.slebew_apps.Home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.slebew_apps.Home.Pertemuan2.SecondActivity
import com.example.slebew_apps.Home.Pertemuan3.ThirdActivity
import com.example.slebew_apps.Home.pertemuan_4.FourthActivity
import com.example.slebew_apps.Home.pertemuan_5.FifthActivity
import com.example.slebew_apps.Home.Pertemuan_7.SeventhActivity
import com.example.slebew_apps.Home.pertemuan_9.NinthActivity
import com.example.slebew_apps.Home.pertemuan_10.TenthActivity
import com.example.slebew_apps.Home.photo.PhotoActivity
import com.example.slebew_apps.Home.photo.PhotoAdapter
import com.example.slebew_apps.data.api.CatFactApiClient
import com.example.slebew_apps.data.api.PhotoApiClient
import com.example.slebew_apps.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            btnPertemuan2.setOnClickListener {
                startActivity(Intent(requireContext(), SecondActivity::class.java))
            }
            btnPertemuan3.setOnClickListener {
                startActivity(Intent(requireContext(), ThirdActivity::class.java))
            }
            btnPertemuan4.setOnClickListener {
                startActivity(Intent(requireContext(), FourthActivity::class.java))
            }
            btnPertemuan5.setOnClickListener {
                startActivity(Intent(requireContext(), FifthActivity::class.java))
            }
            btnPertemuan7.setOnClickListener {
                startActivity(Intent(requireContext(), SeventhActivity::class.java))
            }
            btnPertemuan9.setOnClickListener {
                startActivity(Intent(requireContext(), NinthActivity::class.java))
            }
            btnPertemuan10.setOnClickListener {
                startActivity(Intent(requireContext(), TenthActivity::class.java))
            }
            btnPertemuan11.setOnClickListener {
                startActivity(Intent(requireContext(), PhotoActivity::class.java))
            }
            btnRefresh.setOnClickListener {
                loadCatFact()
            }
        }
        loadCatFact()
        loadPhoto()
    }

    private fun loadPhoto() {
        lifecycleScope.launch {
            try {
                val photos = PhotoApiClient.apiService.getPhotos()
                val adapter = PhotoAdapter(photos)
                binding.rvGallery.adapter = adapter

                /** List Tampil Vertical*/
                binding.rvGallery.layoutManager = LinearLayoutManager(requireContext())

                /** List Tampil Horizontal */
                //binding.rvGallery.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

                /** List Tampil Grid */
                //binding.rvGallery.layoutManager = GridLayoutManager(requireContext(),2)

            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Gagal memuat gambar", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadCatFact() {
        lifecycleScope.launch {
            try {
                val response = CatFactApiClient.apiService.getCatFact()
                binding.tvCatFact.text = "\"${response.fact}\""
            } catch (e: Exception) {
                binding.tvCatFact.text = "Gagal mengambil fakta kucing."
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
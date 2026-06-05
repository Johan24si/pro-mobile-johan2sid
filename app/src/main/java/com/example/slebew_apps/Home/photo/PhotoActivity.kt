package com.example.slebew_apps.Home.photo

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.slebew_apps.data.api.PhotoApiClient
import com.example.slebew_apps.databinding.ActivityPhotoBinding
import kotlinx.coroutines.launch

class PhotoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPhotoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhotoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }

        binding.rvPhotos.layoutManager = LinearLayoutManager(this)

        fetchPhotos()
    }

    private fun fetchPhotos() {
        binding.progressBar.visibility = View.VISIBLE
        lifecycleScope.launch {
            try {
                val photos = PhotoApiClient.apiService.getPhotos()
                binding.rvPhotos.adapter = PhotoAdapter(photos)
                binding.progressBar.visibility = View.GONE
            } catch (e: Exception) {
                binding.progressBar.visibility = View.GONE
                Toast.makeText(this@PhotoActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
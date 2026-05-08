package com.example.slebew_apps.Home.pertemuan_5

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.example.slebew_apps.R
import com.example.slebew_apps.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Inisialisasi Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Web Merdeka"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        // Listener untuk tombol back di Toolbar (Panah Kiri)
        binding.toolbar.setNavigationOnClickListener {
            handleBackNavigation()
        }

        // 2. Setup WebView
        binding.webView.apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            // Improvisasi tambahan: aktifkan zoom agar nyaman saat mencari di Google
            settings.setSupportZoom(true)
            settings.builtInZoomControls = true
            settings.displayZoomControls = false

            loadUrl("https://merdeka.com")
        }

        // 3. Handle Tombol Back Fisik/Gesture HP
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                handleBackNavigation()
            }
        })
    }

    // 4. IMPROVISASI: Logika Pencarian
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.queryHint = "Cari atau masukkan URL..."

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty()) {
                    // Logika: Jika mengandung titik atau diawali http, anggap URL. Jika tidak, anggap keyword Google.
                    val targetUrl = if (query.startsWith("http")) {
                        query
                    } else if (query.contains(".")) {
                        "https://$query"
                    } else {
                        "https://www.google.com/search?q=$query"
                    }

                    binding.webView.loadUrl(targetUrl)
                    searchItem.collapseActionView() // Tutup keyboard & bar pencarian
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    // Fungsi pusat navigasi back
    private fun handleBackNavigation() {
        if (binding.webView.canGoBack()) {
            binding.webView.goBack()
        } else {
            val intent = Intent(this, FifthActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        handleBackNavigation()
        return true
    }
}
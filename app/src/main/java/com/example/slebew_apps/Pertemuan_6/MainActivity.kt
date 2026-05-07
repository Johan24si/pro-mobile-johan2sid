package com.example.slebew_apps.Pertemuan_6

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.slebew_apps.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_main2)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnLogout = findViewById<Button>(R.id.btnLogout)

        btnLogout.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Konfirmasi Logout")
            builder.setMessage("Apakah Anda yakin ingin keluar?")

            builder.setPositiveButton("Ya") { _, _ ->
                // 1. Update SharedPreferences menjadi false
                val sharedPref = getSharedPreferences("SlebewPrefs", Context.MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.putBoolean("isLogin", false)
                editor.apply()

                // 2. Arahkan kembali ke AuthActivity agar user bisa login lagi
                val intent = Intent(this, AuthActivity::class.java)
                startActivity(intent)

                // 3. Tutup MainActivity
                finish()
            }

            builder.setNegativeButton("Tidak") { dialog, _ ->
                dialog.dismiss()
            }

            builder.show()
        }
    }
}
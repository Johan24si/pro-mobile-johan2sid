package com.example.slebew_apps.Pertemuan_6

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.slebew_apps.R

class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Inisialisasi SharedPreferences dengan nama file "SlebewPrefs"
        val sharedPref = getSharedPreferences("SlebewPrefs", Context.MODE_PRIVATE)

        // 2. CEK STATUS: Jika isLogin bernilai true, langsung arahkan ke MainActivity
        val isLogin = sharedPref.getBoolean("isLogin", false)
        if (isLogin) {
            startActivity(Intent(this, MainActivity::class.java))
            finish() // Mengakhiri AuthActivity agar tidak bisa di-back
            return
        }

        setContentView(R.layout.activity_auth)

        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val user = etUsername.text.toString()
            val pass = etPassword.text.toString()

            // Logika: Jika Username sama dengan Password
            if (user == pass && user.isNotEmpty()) {

                // 3. TERAPKAN SharedPreferences: Simpan status login dengan key "isLogin"
                val editor = sharedPref.edit()
                editor.putBoolean("isLogin", true)
                editor.apply() // Menyimpan secara asinkron

                // Pindah ke MainActivity
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                // Tampilkan AlertDialog jika login gagal
                AlertDialog.Builder(this)
                    .setTitle("Login Gagal")
                    .setMessage("Silahkan coba lagi")
                    .setPositiveButton("OK", null)
                    .show()
            }
        }
    }
}
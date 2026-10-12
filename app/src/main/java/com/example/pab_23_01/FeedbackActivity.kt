package com.example.pab_23_01

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView
import java.util.Locale

class FeedbackActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toolbar: MaterialToolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)

        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.navigationView)
        toolbar = findViewById(R.id.toolbar)

        // Setup ActionBarDrawerToggle for strip 3 / hamburger topbar button
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.app_name,
            R.string.app_name,
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navigationView.setCheckedItem(R.id.nav_feedback)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_form -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.nav_riwayat -> {
                    val intent = Intent(this, RiwayatActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.nav_feedback -> {
                    drawerLayout.closeDrawers()
                }
            }
            drawerLayout.closeDrawers()
            true
        }

        val rbKemudahan = findViewById<RatingBar>(R.id.rbKemudahan)
        val rbKejelasan = findViewById<RatingBar>(R.id.rbKejelasan)
        val rbKelengkapan = findViewById<RatingBar>(R.id.rbKelengkapan)
        val rbKecepatan = findViewById<RatingBar>(R.id.rbKecepatan)
        val rbDesain = findViewById<RatingBar>(R.id.rbDesain)
        val edtSaran = findViewById<EditText>(R.id.edtSaran)
        val btnKirimFeedback = findViewById<Button>(R.id.btnKirimFeedback)

        btnKirimFeedback.setOnClickListener {
            val rating1 = rbKemudahan.rating
            val rating2 = rbKejelasan.rating
            val rating3 = rbKelengkapan.rating
            val rating4 = rbKecepatan.rating
            val rating5 = rbDesain.rating
            val saran = edtSaran.text.toString().trim()

            val avg = (rating1 + rating2 + rating3 + rating4 + rating5) / 5.0
            val formattedAvg = String.format(Locale.getDefault(), "%.1f", avg)

            val pesan = "Terima kasih atas ulasan Anda!\n" +
                    "Rata-rata Rating: $formattedAvg / 5.0" +
                    if (saran.isNotEmpty()) "\nSaran: $saran" else ""

            Toast.makeText(this, pesan, Toast.LENGTH_LONG).show()

            // Navigate to Riwayat or Home
            val intent = Intent(this, RiwayatActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}

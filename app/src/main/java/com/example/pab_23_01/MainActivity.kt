package com.example.pab_23_01

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toolbar: MaterialToolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.navigationView)
        toolbar = findViewById(R.id.toolbar)

        // Setup ActionBarDrawerToggle for strip 3 / hamburger topbar button
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.app_name,
            R.string.app_name
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navigationView.setCheckedItem(R.id.nav_form)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_form -> {
                    drawerLayout.closeDrawers()
                }
                R.id.nav_riwayat -> {
                    val intent = Intent(this, RiwayatActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_feedback -> {
                    val intent = Intent(this, FeedbackActivity::class.java)
                    startActivity(intent)
                }
            }
            drawerLayout.closeDrawers()
            true
        }

        val edtNama = findViewById<EditText>(R.id.edtNama)
        val rgJk = findViewById<RadioGroup>(R.id.rgJk)
        val spKota = findViewById<Spinner>(R.id.spKota)
        val swMenyetujui = findViewById<Switch>(R.id.swMenyetujui)
        val btnSimpan = findViewById<Button>(R.id.btn_simpan)

        val kota = arrayOf("Pilih Kota", "Jakarta", "Bandung", "Surabaya", "Padang")
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item, kota
        )
        spKota.adapter = adapter

        btnSimpan.setOnClickListener {
            val nama = edtNama.text.toString().trim()
            if (nama.isEmpty()) {
                Toast.makeText(
                    this, "Nama Tidak Boleh Kosong!!!",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            if (!swMenyetujui.isChecked) {
                Toast.makeText(
                    this, "Anda Harus Menyetujui S&K",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            val selectedKota = spKota.selectedItem.toString()
            if (selectedKota == "Pilih Kota") {
                Toast.makeText(
                    this, "Silakan pilih Kota Asal Anda",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            val jk = when (rgJk.checkedRadioButtonId) {
                R.id.rbLaki -> "Laki-Laki"
                R.id.rbPerempuan -> "Perempuan"
                else -> "Belum Dipilih"
            }

            // Save entry into RegistrationRepository
            RegistrationRepository.addRegistration(nama, jk, selectedKota)

            val data = "Nama : $nama" +
                    "\nJenis Kelamin : $jk" +
                    "\nKota : $selectedKota"

            val intent = Intent(this, DuaActivity::class.java)
            intent.putExtra("DATA_HASIL", data)
            startActivity(intent)
        }
    }
}
